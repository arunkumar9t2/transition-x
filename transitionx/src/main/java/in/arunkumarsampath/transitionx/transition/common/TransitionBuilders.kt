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

package `in`.arunkumarsampath.transitionx.transition.common

import `in`.arunkumarsampath.transitionx.transition.TransitionBuilder
import `in`.arunkumarsampath.transitionx.transition.changecolor.ChangeColor
import android.support.transition.*

/**
 * Builder for [ChangeTransform]
 */
class ScaleRotateBuilder : TransitionBuilder<ChangeTransform>(ChangeTransform())

/**
 * Builder for [ChangeClipBounds]
 */
class ChangeClipBoundsBuilder : TransitionBuilder<ChangeClipBounds>(ChangeClipBounds())

/**
 * Builder for [ChangeBounds]
 */
class ChangeBoundsBuilder : TransitionBuilder<ChangeBounds>(ChangeBounds())

/**
 * Builder for [ChangeImageTransform]
 */
class ChangeImageBuilder : TransitionBuilder<ChangeImageTransform>(ChangeImageTransform())

/**
 * Builder for [ChangeScroll]
 */
class ChangeScrollBuilder : TransitionBuilder<ChangeScroll>(ChangeScroll())

/**
 * Builder for [Explode]
 */
class ExplodeBuilder : TransitionBuilder<Explode>(Explode())

/**
 * Builder for [ChangeColor]
 */
class ChangeColorBuilder : TransitionBuilder<ChangeColor>(ChangeColor())