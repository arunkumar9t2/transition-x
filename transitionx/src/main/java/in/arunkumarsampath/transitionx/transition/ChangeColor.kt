/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package `in`.arunkumarsampath.transitionx.transition

import `in`.arunkumarsampath.transitionx.transition.evalutor.ArgbEvaluator
import `in`.arunkumarsampath.transitionx.util.TransitionUtils
import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.transition.Transition
import android.support.transition.TransitionValues
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * This transition tracks changes during scene changes to the
 * [background][View.setBackground]
 * property of its target views (when the background is a
 * [ColorDrawable], as well as the [TextView.setTextColor] of the text for target TextViews.
 * If the color changes between scenes, the color change is animated.
 */
class ChangeColor : Transition() {

    private fun captureValues(transitionValues: TransitionValues) {
        transitionValues.values[PROPNAME_BACKGROUND] = transitionValues.view.background
        if (transitionValues.view is TextView) {
            transitionValues.values[PROPNAME_TEXT_COLOR] = (transitionValues.view as TextView).currentTextColor
        }
    }

    override fun captureStartValues(transitionValues: TransitionValues) =
            captureValues(transitionValues)

    override fun captureEndValues(transitionValues: TransitionValues) =
            captureValues(transitionValues)

    @SuppressLint("ObjectAnimatorBinding")
    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?,
                                endValues: TransitionValues?): Animator? {
        if (startValues == null || endValues == null) {
            return null
        }
        val view = endValues.view
        val startBackground = startValues.values[PROPNAME_BACKGROUND] as Drawable?
        val endBackground = endValues.values[PROPNAME_BACKGROUND] as Drawable?
        var backgroundAnimator: ValueAnimator? = null
        if (startBackground is ColorDrawable && endBackground is ColorDrawable) {
            if (startBackground.color != endBackground.color) {
                val finalColor = endBackground.color
                endBackground.color = startBackground.color
                backgroundAnimator = ValueAnimator.ofObject(
                        ArgbEvaluator,
                        startBackground.color,
                        finalColor
                ).apply {
                    addUpdateListener { animation ->
                        animation.animatedValue?.let { color ->
                            endBackground.color = color as Int
                        }
                    }
                }
            }
        }
        var textColorAnimator: ValueAnimator? = null
        if (view is TextView) {
            val start = (startValues.values[PROPNAME_TEXT_COLOR] as Int?)!!
            val end = (endValues.values[PROPNAME_TEXT_COLOR] as Int?)!!
            if (start != end) {
                view.setTextColor(end)
                textColorAnimator = ValueAnimator.ofObject(
                        ArgbEvaluator,
                        start,
                        end
                ).apply {
                    addUpdateListener { animation ->
                        animation.animatedValue?.let { color ->
                            view.setTextColor(color as Int)
                        }
                    }
                }
            }
        }
        return TransitionUtils.mergeAnimators(backgroundAnimator, textColorAnimator)
    }

    companion object {
        private const val PROPNAME_BACKGROUND = "android:recolor:background"
        private const val PROPNAME_TEXT_COLOR = "android:recolor:textColor"
    }
}
