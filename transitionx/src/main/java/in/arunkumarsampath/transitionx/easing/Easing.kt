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

package `in`.arunkumarsampath.transitionx.easing

import `in`.arunkumarsampath.transitionx.scope.TransitionBuilderMarker
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator

/**
 * Proxy object to scope adding inbuilt interpolators provided with the support library.
 */
@TransitionBuilderMarker
object Easing {
    /**
     * Material Design standard's Standard Easing([FastOutSlowInInterpolator])
     *
     * This easing is recommended for Views that **move within visible area of the layout.**
     *
     * More details: [Easing on Material.io](https://material.io/design/motion/speed.html#easing)
     */
    val standardEasing get() = FastOutSlowInInterpolator()

    /**
     * Material Design standard's Decelerate Easing([LinearOutSlowInInterpolator])
     *
     * This easing is recommended for Views that **appear/enter outside visible bounds of the layout.**
     * Example: *Snackbar message from bottom of the screen*
     *
     * More details: [Easing on Material.io](https://material.io/design/motion/speed.html#easing)
     */
    val decelerateEasing get() = LinearOutSlowInInterpolator()

    /**
     * Material Design standard's Accelerate Easing([FastOutLinearInInterpolator])
     *
     * This easing is recommended for Views that **exit visible bounds of the layout.**
     * Example: *Disappear bottom sheet*
     *
     * More details: [Easing on Material.io](https://material.io/design/motion/speed.html#easing)
     */
    val accelerateEasing get() = FastOutLinearInInterpolator()
}