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

package `in`.arunkumarsampath.transitionx.util

import android.animation.Animator
import android.animation.AnimatorSet

/**
 * Collection of utility methods for working with transitions.
 */
object TransitionUtils {

    /**
     * Merges non null animators among [animator1] and [animator2] and plays them together when started.
     *
     * @return Merged animator instance.
     */
    fun mergeAnimators(animator1: Animator?, animator2: Animator?): Animator? {
        return when {
            animator1 == null -> animator2
            animator2 == null -> animator1
            else -> {
                AnimatorSet().apply {
                    playTogether(animator1, animator2)
                }
            }
        }
    }
}