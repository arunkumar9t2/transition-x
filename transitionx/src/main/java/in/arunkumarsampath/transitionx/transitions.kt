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

package `in`.arunkumarsampath.transitionx

import `in`.arunkumarsampath.transitionx.builders.set.AutoTransitionBuilder
import `in`.arunkumarsampath.transitionx.builders.set.DefaultTransitionSetBuilder
import android.support.transition.TransitionManager
import android.view.ViewGroup

inline fun ViewGroup.prepareTransition(
        cancel: Boolean = false,
        transitionsBuilder: DefaultTransitionSetBuilder.() -> Unit = {}
) {
    if (cancel) {
        TransitionManager.endTransitions(this)
    }
    TransitionManager.beginDelayedTransition(
            this,
            DefaultTransitionSetBuilder().apply(transitionsBuilder).transition
    )
}

inline fun ViewGroup.prepareAutoTransition(
        cancel: Boolean = false,
        autoTransitionBuilder: AutoTransitionBuilder.() -> Unit = {}
) {
    if (cancel) {
        TransitionManager.endTransitions(this)
    }
    TransitionManager.beginDelayedTransition(
            this,
            AutoTransitionBuilder().apply(autoTransitionBuilder).transition
    )
}