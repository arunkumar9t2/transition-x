@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.builders

import `in`.arunkumarsampath.transitionx.scope.TransitionBuilderMarker
import android.animation.TimeInterpolator
import android.support.annotation.IdRes
import android.support.transition.PathMotion
import android.support.transition.Transition
import android.support.transition.TransitionPropagation
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
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

    /**
     * Applies Material Design standard's Standard Easing([FastOutSlowInInterpolator]) to this transition.
     *
     * This easing is recommended for Views that move within visible area of the layout.
     *
     * @see [https://material.io/design/motion/speed.html#easing]
     *
     * Note: Calling this overrides any previously set [interpolator]
     */
    inline fun standardEasing() {
        interpolator = FastOutSlowInInterpolator()
    }

    /**
     * Applies Material Design standard's Decelerate Easing([LinearOutSlowInInterpolator]) to this
     * transition.
     *
     * This easing is recommended for Views that appear/enter outside visible bounds of the layout.
     * Example: Snackbar message from bottom of the screen
     *
     * @see [https://material.io/design/motion/speed.html#easing]
     *
     * Note: Calling this overrides any previously set [interpolator]
     */
    inline fun decelerateEasing() {
        interpolator = LinearOutSlowInInterpolator()
    }

    /**
     * Applies Material Design standard's Accelerate Easing([FastOutLinearInInterpolator]) to this
     * transition.
     *
     * This easing is recommended for Views that exit visible bounds of the layout.
     * Example: Disappear bottom sheet
     *
     * @see [https://material.io/design/motion/speed.html#easing]
     *
     * Note: Calling this overrides any previously set [interpolator]
     */
    inline fun accelerateEasing() {
        interpolator = FastOutLinearInInterpolator()
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

    inline operator fun String.unaryPlus() {
        transition.addTarget(this)
    }

    inline operator fun String.unaryMinus() {
        transition.removeTarget(this)
    }

    inline operator fun <reified Type> Type.unaryPlus() {
        transition.addTarget(Type::class.java)
    }

    inline operator fun <reified Type> Type.unaryMinus() {
        transition.removeTarget(Type::class.java)
    }

    inline operator fun View.unaryPlus() {
        transition.addTarget(this)
    }

    inline operator fun View.unaryMinus() {
        transition.removeTarget(this)
    }

    inline fun add(vararg views: View) {
        views.forEach { transition.addTarget(it) }
    }

    inline fun add(@IdRes vararg ids: Int) {
        ids.forEach { transition.addTarget(it) }
    }

    inline fun remove(vararg views: View) {
        views.forEach { transition.removeTarget(it) }
    }

    inline fun remove(@IdRes vararg ids: Int) {
        ids.forEach { transition.removeTarget(it) }
    }

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
