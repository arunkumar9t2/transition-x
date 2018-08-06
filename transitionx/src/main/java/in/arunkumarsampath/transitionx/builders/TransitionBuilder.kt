package `in`.arunkumarsampath.transitionx.builders

import android.animation.TimeInterpolator
import android.support.transition.Transition
import android.view.View

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

    operator fun Int.unaryPlus() = transition.addTarget(this)

    operator fun String.unaryPlus() = transition.addTarget(this)

    inline operator fun <reified Type> Type.unaryPlus() = transition.addTarget(Type::class.java)

    operator fun View.unaryPlus() = transition.addTarget(this)
}
