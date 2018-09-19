package `in`.arunkumarsampath.transitionx.scope

import android.support.transition.Transition

/**
 * DSL marker to allow strict compiler checks when nested lamdas are used constructing [Transition]s
 */
@DslMarker
annotation class TransitionBuilderMarker