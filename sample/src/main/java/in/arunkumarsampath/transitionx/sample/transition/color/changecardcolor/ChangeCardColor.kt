/*
 *
 * Copyright 2019 Arunkumar
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
 *
 */

package `in`.arunkumarsampath.transitionx.sample.transition.color.changecardcolor

import `in`.arunkumarsampath.transitionx.evaluator.ArgbEvaluator
import android.animation.Animator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.support.design.card.MaterialCardView
import android.support.transition.Transition
import android.support.transition.TransitionValues
import android.view.ViewGroup


/**
 * Implementation copied from AOSP Samples.
 *
 * https://github.com/googlesamples/android-CustomTransition
 */
class ChangeCardColor : Transition() {

    private fun captureValues(values: TransitionValues) {
        val materialCardView = values.view as? MaterialCardView
                ?: throw IllegalArgumentException("This transition can be only applied on MaterialCardView")
        values.values[PROPNAME_CARD_VIEW_BACKGROUND] = materialCardView.cardBackgroundColor
    }

    override fun captureStartValues(transitionValues: TransitionValues) = captureValues(transitionValues)

    override fun captureEndValues(transitionValues: TransitionValues) = captureValues(transitionValues)

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
        if (null == startValues || null == endValues) {
            return null
        }
        val materialCardView = endValues.view as? MaterialCardView
                ?: throw IllegalArgumentException("This transition can be only applied on MaterialCardView")

        val startColor = (startValues.values[PROPNAME_CARD_VIEW_BACKGROUND] as ColorStateList).defaultColor
        val endColor = (endValues.values[PROPNAME_CARD_VIEW_BACKGROUND] as ColorStateList).defaultColor
        if (startColor != endColor) {
            return ValueAnimator.ofObject(ArgbEvaluator, startColor, endColor).apply {
                addUpdateListener { animation ->
                    val value = animation.animatedValue
                    if (null != value) {
                        materialCardView.setCardBackgroundColor(value as Int)
                    }
                }
            }
        }
        return null
    }

    companion object {
        private const val PROPNAME_CARD_VIEW_BACKGROUND = "transition:change_card_color:background"
    }
}