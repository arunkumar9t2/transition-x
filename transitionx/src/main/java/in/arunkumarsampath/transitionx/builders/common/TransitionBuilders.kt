package `in`.arunkumarsampath.transitionx.builders.common

import `in`.arunkumarsampath.transitionx.builders.TransitionBuilder
import `in`.arunkumarsampath.transitionx.transition.ChangeColor
import android.support.transition.*

class ScaleRotateBuilder : TransitionBuilder<ChangeTransform>(ChangeTransform())

class ChangeClipBoundsBuilder : TransitionBuilder<ChangeClipBounds>(ChangeClipBounds())

class ChangeBoundsBuilder : TransitionBuilder<ChangeBounds>(ChangeBounds())

class ChangeImageBuilder : TransitionBuilder<ChangeImageTransform>(ChangeImageTransform())

class ChangeScrollBuilder : TransitionBuilder<ChangeScroll>(ChangeScroll())

class ExplodeBuilder : TransitionBuilder<Explode>(Explode())

class ChangeColorBuilder : TransitionBuilder<ChangeColor>(ChangeColor())