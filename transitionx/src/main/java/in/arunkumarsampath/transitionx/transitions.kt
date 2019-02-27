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

package `in`.arunkumarsampath.transitionx

import `in`.arunkumarsampath.transitionx.transition.set.AutoTransitionBuilder
import `in`.arunkumarsampath.transitionx.transition.set.DefaultTransitionSetBuilder
import android.support.transition.*
import android.view.ViewGroup

/**
 * Cancels all animations running on this `ViewGroup`. Internally calls [TransitionManager.endTransitions].
 *
 * @see TransitionManager.endTransitions
 */
@Suppress("NOTHING_TO_INLINE")
inline fun ViewGroup.cancelTransitions() {
    TransitionManager.endTransitions(this)
}

/**
 * Schedules a call to [TransitionManager.beginDelayedTransition] with receiver [ViewGroup] as
 * the scene root. [transitionsBuilder] uses a [TransitionSet] instance internally and its
 * properties can be modified by using available methods from [DefaultTransitionSetBuilder]
 *
 * Usage:
 * ```
 *  constraintLayout.prepareTransition {
 *      changeText {
 *          changeTextBehavior = ChangeText.CHANGE_BEHAVIOR_OUT_IN
 *      }
 *      +textView
 *  }
 *  textView.setText("Hello")
 * ```
 *
 * @param cancel if true, ends all previous transitions before starting this transition.
 * @param transitionsBuilder Builder to construct transition instance for use with [TransitionManager]
 */
inline fun ViewGroup.prepareTransition(
        cancel: Boolean = false,
        transitionsBuilder: DefaultTransitionSetBuilder.() -> Unit = {}
) {
    val transition = DefaultTransitionSetBuilder().apply(transitionsBuilder).transition
    prepareTransition(cancel = cancel, transition = transition)
}

/**
 * Calls [TransitionManager.beginDelayedTransition] with this [ViewGroup] and [transition] as the
 * parameter.
 *
 * Usage:
 * ```
 *   constraintLayout.prepareTransition(transition)
 *
 *   someView.hide()
 * ```
 *
 * @param cancel if true, ends all previous transitions before starting the transition
 * @param transition the transition instance that should be used
 */
@Suppress("NOTHING_TO_INLINE")
inline fun ViewGroup.prepareTransition(cancel: Boolean = false, transition: Transition) {
    if (cancel) {
        cancelTransitions()
    }
    TransitionManager.beginDelayedTransition(this, transition)
}

/**
 * Schedules a call to [TransitionManager.beginDelayedTransition] with receiver [ViewGroup] as
 * the scene root. [autoTransitionBuilder] uses a [AutoTransition] instance internally and its
 * properties can be modified by using available methods from [AutoTransitionBuilder].
 *
 * Since [AutoTransition] is used, View bounds changes are animated using [ChangeBounds] and
 * disappear/appear are animated using [Fade]
 * @see [AutoTransition]
 *
 * Usage:
 * ```
 *  constraintLayout.prepareAutoTransition {
 *      duration = 1000
 *  }
 *  // Layout modifications
 * ```
 *
 * @param cancel if true, ends all previous transitions before starting this transition.
 * @param autoTransitionBuilder Builder to construct transition instance for use with [TransitionManager]
 */
inline fun ViewGroup.prepareAutoTransition(
        cancel: Boolean = false,
        autoTransitionBuilder: AutoTransitionBuilder.() -> Unit = {}
) {
    if (cancel) {
        cancelTransitions()
    }
    val transition = AutoTransitionBuilder().apply(autoTransitionBuilder).transition
    prepareTransition(cancel = cancel, transition = transition)
}

/**
 * Creates a [Transition] instance by configuring it properties provided by [DefaultTransitionSetBuilder]
 *
 * Usage:
 * ```
 *  val moveTransition = transitionSet {
 *      moveResize()
 *      scaleRotate()
 *  }
 * ```
 */
inline fun transitionSet(transitionsBuilder: DefaultTransitionSetBuilder.() -> Unit = {}): Transition {
    return DefaultTransitionSetBuilder().apply(transitionsBuilder).transition
}