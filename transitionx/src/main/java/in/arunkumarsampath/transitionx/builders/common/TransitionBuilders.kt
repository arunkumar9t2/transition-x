package `in`.arunkumarsampath.transitionx.builders.common

import `in`.arunkumarsampath.transitionx.builders.TransitionBuilder
import `in`.arunkumarsampath.transitionx.transition.ChangeColor
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