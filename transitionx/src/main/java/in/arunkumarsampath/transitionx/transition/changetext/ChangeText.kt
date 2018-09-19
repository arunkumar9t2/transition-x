/*
 * Copyright 2018 Arunkumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package `in`.arunkumarsampath.transitionx.transition.changetext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.graphics.Color
import android.support.transition.Transition
import android.support.transition.TransitionListenerAdapter
import android.support.transition.TransitionValues
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

/**
 * This transition tracks changes to the text in TextView targets. If the text
 * changes between the start and end scenes, the transition ensures that the
 * starting text stays until the transition ends, at which point it changes
 * to the end text.  This is useful in situations where you want to resize a
 * text view to its new size before displaying the text that goes there.
 */
class ChangeText : Transition() {
    /**
     * The type of fading animation to use when this
     * transition is run. One of
     * [CHANGE_BEHAVIOR_KEEP], [CHANGE_BEHAVIOR_OUT],
     * [CHANGE_BEHAVIOR_IN], and [CHANGE_BEHAVIOR_OUT_IN].
     */
    @Suppress("RedundantSetter")
    @get:ChangeTextBehavior
    var changeBehavior = CHANGE_BEHAVIOR_KEEP
        set(@ChangeTextBehavior value) {
            field = value
        }

    override fun getTransitionProperties() = TRANSITION_PROPERTIES

    private fun captureValues(transitionValues: TransitionValues) {
        if (transitionValues.view is TextView) {
            val textView = transitionValues.view as TextView
            transitionValues.values[PROPNAME_TEXT] = textView.text
            if (textView is EditText) {
                transitionValues.values[PROPNAME_TEXT_SELECTION_START] = textView.getSelectionStart()
                transitionValues.values[PROPNAME_TEXT_SELECTION_END] = textView.getSelectionEnd()
            }
            if (changeBehavior > CHANGE_BEHAVIOR_KEEP) {
                transitionValues.values[PROPNAME_TEXT_COLOR] = textView.currentTextColor
            }
        }
    }

    override fun captureStartValues(transitionValues: TransitionValues) =
            captureValues(transitionValues)

    override fun captureEndValues(transitionValues: TransitionValues) =
            captureValues(transitionValues)

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?,
                                endValues: TransitionValues?): Animator? {
        if (startValues == null || endValues == null ||
                startValues.view !is TextView || endValues.view !is TextView) {
            return null
        }
        val view = endValues.view as TextView
        val startVals = startValues.values
        val endVals = endValues.values
        val startText = when {
            startVals[PROPNAME_TEXT] != null -> startVals[PROPNAME_TEXT] as CharSequence?
            else -> ""
        }
        val endText = when {
            endVals[PROPNAME_TEXT] != null -> endVals[PROPNAME_TEXT] as CharSequence?
            else -> ""
        }
        val startSelectionStart: Int
        val startSelectionEnd: Int
        val endSelectionStart: Int
        val endSelectionEnd: Int
        if (view is EditText) {
            startSelectionStart = when {
                startVals[PROPNAME_TEXT_SELECTION_START] != null -> (startVals[PROPNAME_TEXT_SELECTION_START] as Int?)!!
                else -> -1
            }
            startSelectionEnd = when {
                startVals[PROPNAME_TEXT_SELECTION_END] != null -> (startVals[PROPNAME_TEXT_SELECTION_END] as Int?)!!
                else -> startSelectionStart
            }
            endSelectionStart = when {
                endVals[PROPNAME_TEXT_SELECTION_START] != null -> (endVals[PROPNAME_TEXT_SELECTION_START] as Int?)!!
                else -> -1
            }
            endSelectionEnd = when {
                endVals[PROPNAME_TEXT_SELECTION_END] != null -> (endVals[PROPNAME_TEXT_SELECTION_END] as Int?)!!
                else -> endSelectionStart
            }
        } else {
            endSelectionEnd = -1
            endSelectionStart = endSelectionEnd
            startSelectionEnd = endSelectionStart
            startSelectionStart = startSelectionEnd
        }
        if (startText != endText) {
            val startColor: Int
            val endColor: Int
            if (changeBehavior != CHANGE_BEHAVIOR_IN) {
                view.text = startText
                if (view is EditText) {
                    setSelection(view, startSelectionStart, startSelectionEnd)
                }
            }
            val anim: Animator?
            if (changeBehavior == CHANGE_BEHAVIOR_KEEP) {
                endColor = 0
                startColor = endColor
                anim = ValueAnimator.ofFloat(0F, 1F).apply {
                    addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            if (startText == view.text) {
                                // Only set if it hasn't been changed since anim started
                                view.text = endText
                                if (view is EditText) {
                                    setSelection(view, endSelectionStart, endSelectionEnd)
                                }
                            }
                        }
                    })
                }
            } else {
                startColor = (startVals[PROPNAME_TEXT_COLOR] as Int?)!!
                endColor = (endVals[PROPNAME_TEXT_COLOR] as Int?)!!
                // Fade out start text
                var outAnim: ValueAnimator? = null
                var inAnim: ValueAnimator? = null
                if (changeBehavior == CHANGE_BEHAVIOR_OUT_IN || changeBehavior == CHANGE_BEHAVIOR_OUT) {
                    outAnim = ValueAnimator.ofInt(Color.alpha(startColor), 0).apply {
                        addUpdateListener { animation ->
                            val currAlpha = animation.animatedValue as Int
                            view.setTextColor(currAlpha shl 24 or (startColor and 0xffffff))
                        }
                        addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                if (startText == view.text) {
                                    // Only set if it hasn't been changed since anim started
                                    view.text = endText
                                    if (view is EditText) {
                                        setSelection(view, endSelectionStart, endSelectionEnd)
                                    }
                                }
                                // restore opaque alpha and correct end color
                                view.setTextColor(endColor)
                            }
                        })
                    }
                }
                if (changeBehavior == CHANGE_BEHAVIOR_OUT_IN || changeBehavior == CHANGE_BEHAVIOR_IN) {
                    inAnim = ValueAnimator.ofInt(0, Color.alpha(endColor)).apply {
                        addUpdateListener { animation ->
                            val currAlpha = animation.animatedValue as Int
                            view.setTextColor(currAlpha shl 24 or (endColor and 0xffffff))
                        }
                        addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationCancel(animation: Animator) {
                                // restore opaque alpha and correct end color
                                view.setTextColor(endColor)
                            }
                        })
                    }
                }
                if (outAnim != null && inAnim != null) {
                    anim = AnimatorSet()
                    anim.playSequentially(outAnim, inAnim)
                } else if (outAnim != null) {
                    anim = outAnim
                } else {
                    // Must be an in-only animation
                    anim = inAnim
                }
            }
            val transitionListener = object : TransitionListenerAdapter() {
                var pausedColor = 0
                override fun onTransitionPause(transition: Transition) {
                    if (changeBehavior != CHANGE_BEHAVIOR_IN) {
                        view.text = endText
                        if (view is EditText) {
                            setSelection(view, endSelectionStart, endSelectionEnd)
                        }
                    }
                    if (changeBehavior > CHANGE_BEHAVIOR_KEEP) {
                        pausedColor = view.currentTextColor
                        view.setTextColor(endColor)
                    }
                }

                override fun onTransitionResume(transition: Transition) {
                    if (changeBehavior != CHANGE_BEHAVIOR_IN) {
                        view.text = startText
                        if (view is EditText) {
                            setSelection(view, startSelectionStart, startSelectionEnd)
                        }
                    }
                    if (changeBehavior > CHANGE_BEHAVIOR_KEEP) {
                        view.setTextColor(pausedColor)
                    }
                }

                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)
                }
            }
            addListener(transitionListener)
            return anim
        }
        return null
    }

    private fun setSelection(editText: EditText, start: Int, end: Int) {
        if (start >= 0 && end >= 0) {
            editText.setSelection(start, end)
        }
    }

    companion object {
        private const val PROPNAME_TEXT = "android:textchange:text"
        private const val PROPNAME_TEXT_SELECTION_START = "android:textchange:textSelectionStart"
        private const val PROPNAME_TEXT_SELECTION_END = "android:textchange:textSelectionEnd"
        private const val PROPNAME_TEXT_COLOR = "android:textchange:textColor"

        /**
         * Flag specifying that the text in affected/changing TextView targets will keep
         * their original text during the transition, setting it to the final text when
         * the transition ends. This is the default behavior.
         */
        const val CHANGE_BEHAVIOR_KEEP = 0
        /**
         * Flag specifying that the text changing animation should first fade
         * out the original text completely. The new text is set on the target
         * view at the end of the fade-out animation. This transition is typically
         * used with a later [.CHANGE_BEHAVIOR_IN] transition, allowing more
         * flexibility than the [.CHANGE_BEHAVIOR_OUT_IN] by allowing other
         * transitions to be run sequentially or in parallel with these fades.
         */
        const val CHANGE_BEHAVIOR_OUT = 1
        /**
         * Flag specifying that the text changing animation should fade in the
         * end text into the affected target view(s). This transition is typically
         * used in conjunction with an earlier [.CHANGE_BEHAVIOR_OUT]
         * transition, possibly with other transitions running as well, such as
         * a sequence to fade out, then resize the view, then fade in.
         */
        const val CHANGE_BEHAVIOR_IN = 2
        /**
         * Flag specifying that the text changing animation should first fade
         * out the original text completely and then fade in the
         * new text.
         */
        const val CHANGE_BEHAVIOR_OUT_IN = 3

        private val TRANSITION_PROPERTIES = arrayOf(
                PROPNAME_TEXT,
                PROPNAME_TEXT_SELECTION_START,
                PROPNAME_TEXT_SELECTION_END
        )
    }
}
