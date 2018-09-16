@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.builders

import `in`.arunkumarsampath.transitionx.scope.TransitionBuilderMarker
import android.animation.TimeInterpolator
import android.support.annotation.IdRes
import android.support.transition.PathMotion
import android.support.transition.Transition
import android.support.transition.TransitionPropagation
import android.view.View

@TransitionBuilderMarker
open class TransitionBuilder<T : Transition>(val transition: T) {

    var duration: Long
        get() = transition.duration
        set(value) {
            transition.duration = value
        }

    var startDelay: Long
        get() = transition.startDelay
        set(value) {
            transition.startDelay = value
        }

    var interpolator: TimeInterpolator?
        get() = transition.interpolator
        set(value) {
            transition.interpolator = value
        }

    var pathMotion: PathMotion?
        get() = transition.pathMotion
        set(value) {
            transition.setPathMotion(value)
        }

    var transitionPropagation: TransitionPropagation?
        get() = transition.propagation
        set(value) {
            transition.propagation = value
        }

    inline fun customProperties(action: Transition.() -> Unit) {
        transition.apply(action)
    }

    inline operator fun String.unaryPlus() = transition.addTarget(this)

    inline operator fun String.unaryMinus() = transition.removeTarget(this)

    inline operator fun <reified Type> Type.unaryPlus() = transition.addTarget(Type::class.java)

    inline operator fun <reified Type> Type.unaryMinus() = transition.removeTarget(Type::class.java)

    inline operator fun View.unaryPlus() = transition.addTarget(this)

    inline operator fun View.unaryMinus() = transition.removeTarget(this)

    inline fun exclude(vararg views: View) {
        views.forEach { transition.excludeTarget(it, true) }
    }

    inline fun <reified Type : View> exclude() {
        transition.excludeTarget(Type::class.java, true)
    }

    inline fun exclude(@IdRes vararg ids: Int) {
        ids.forEach { transition.excludeTarget(it, true) }
    }

    inline fun excludeChildren(vararg views: View) {
        views.forEach { transition.excludeChildren(it, true) }
    }

    inline fun <reified Type : View> excludeChildren() {
        transition.excludeChildren(Type::class.java, true)
    }

    inline fun excludeChildren(@IdRes vararg ids: Int) {
        ids.forEach { transition.excludeChildren(it, true) }
    }

    inline fun onEnd(noinline onEnd: (transition: Transition) -> Unit) = transition.addListener(onEnd = onEnd)

    inline fun onStart(noinline onStart: (transition: Transition) -> Unit) = transition.addListener(onStart = onStart)

    inline fun onCancel(noinline onCancel: (transition: Transition) -> Unit) = transition.addListener(onCancel = onCancel)

    inline fun onResume(noinline onResume: (transition: Transition) -> Unit) = transition.addListener(onResume = onResume)

    inline fun onPause(noinline onPause: (transition: Transition) -> Unit) = transition.addListener(onEnd = onPause)

    fun Transition.addListener(
            onEnd: ((transition: Transition) -> Unit)? = null,
            onStart: ((transition: Transition) -> Unit)? = null,
            onCancel: ((transition: Transition) -> Unit)? = null,
            onResume: ((transition: Transition) -> Unit)? = null,
            onPause: ((transition: Transition) -> Unit)? = null
    ) {
        addListener(object : Transition.TransitionListener {
            override fun onTransitionEnd(transition: Transition) {
                onEnd?.invoke(transition)
            }

            override fun onTransitionResume(transition: Transition) {
                onResume?.invoke(transition)
            }

            override fun onTransitionPause(transition: Transition) {
                onPause?.invoke(transition)
            }

            override fun onTransitionCancel(transition: Transition) {
                onCancel?.invoke(transition)
            }

            override fun onTransitionStart(transition: Transition) {
                onStart?.invoke(transition)
            }
        })
    }
}
