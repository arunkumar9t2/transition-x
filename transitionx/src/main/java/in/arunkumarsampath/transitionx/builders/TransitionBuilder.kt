@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.builders

import `in`.arunkumarsampath.transitionx.scope.TransitionBuilderMarker
import android.animation.TimeInterpolator
import android.support.transition.PathMotion
import android.support.transition.Transition
import android.view.View

@TransitionBuilderMarker
open class TransitionBuilder<T : Transition>(val transition: T) {
    var duration: Long
        set(value) {
            transition.duration = value
        }
        get() = transition.duration

    var startDelay: Long
        set(value) {
            transition.startDelay = value
        }
        get() = transition.startDelay

    var interpolator: TimeInterpolator?
        set(value) {
            transition.interpolator = value
        }
        get() = transition.interpolator


    var pathMotion: PathMotion?
        set(value) {
            transition.setPathMotion(value)
        }
        get() = transition.pathMotion

    inline operator fun String.unaryPlus() = transition.addTarget(this)

    inline operator fun <reified Type> Type.unaryPlus() = transition.addTarget(Type::class.java)

    inline operator fun View.unaryPlus() = transition.addTarget(this)
}
