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

@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.transition

import `in`.arunkumarsampath.transitionx.easing.Easing
import `in`.arunkumarsampath.transitionx.scope.TransitionBuilderMarker
import android.animation.TimeInterpolator
import android.support.annotation.IdRes
import android.support.transition.*
import android.view.View
import android.view.animation.Interpolator

/**
 * Builder for constructing a [Transition] instance.
 */
@TransitionBuilderMarker
open class TransitionBuilder<T : Transition>(val transition: T) {
    /**
     * Sets the duration for this transition
     *
     * @see [Transition.setDuration]
     */
    var duration: Long
        get() = transition.duration
        set(value) {
            transition.duration = value
        }

    /**
     * Set a start delay in milliseconds to this transition
     *
     * @see [Transition.setStartDelay]
     */
    var startDelay: Long
        get() = transition.startDelay
        set(value) {
            transition.startDelay = value
        }

    /**
     * [TimeInterpolator] for this transition.
     *
     * @see [Transition.setInterpolator]
     */
    var interpolator: TimeInterpolator?
        get() = transition.interpolator
        set(value) {
            transition.interpolator = value
        }

    /**
     * Builder to add one of the inbuilt easing as per Material Design guidelines. Calling this
     * overwrites any previously set transitions.
     *
     * Usage:
     * ```
     *      constraintLayout.prepareTransition {
     *           changeImage()
     *           ease {
     *              standardEasing
     *           }
     *      }
     * ```
     */
    inline fun ease(easeBuilder: Easing.() -> Interpolator) {
        interpolator = easeBuilder.invoke(Easing)
    }

    /**
     * [PathMotion] for this transition.
     *
     * @see [Transition.setPathMotion]
     */
    var pathMotion: PathMotion?
        get() = transition.pathMotion
        set(value) {
            transition.setPathMotion(value)
        }

    /**
     * [TransitionPropagation] for this transition. Propagations are use to control start delays for
     * transition. For example, [CircularPropagation] has increased start delays from the focal point
     * and [Explode] defaults to using that.
     *
     * @see [TransitionPropagation], [Transition.setPropagation]
     */
    var transitionPropagation: TransitionPropagation?
        get() = transition.propagation
        set(value) {
            transition.propagation = value
        }

    /**
     * Builder to set custom properties to be built [Transition] object. In the [action] block, [transition]
     * is received as the lambda receiver and it is possible to directly set any custom properties on
     * the transition.
     *
     * This is useful when you want access a custom transition's properties not provided by this
     * builder.
     *
     * ```
     *      constraintLayout.prepareTransition {
     *          customTransition<MyCustomTransition> {
     *              customProperties {
     *                  myCustomProperty = 100
     *              }
     *          }
     *      }
     *      // Layout changes
     * ```
     */
    inline fun customProperties(action: Transition.() -> Unit) {
        transition.apply(action)
    }

    /**
     * Add a target identified by `targetName` to this transition.
     *
     * @see [Transition.addTarget]
     */
    inline operator fun String.unaryPlus() {
        transition.addTarget(this)
    }

    /**
     * Remove a target identified by `targetName` from this transition.
     *
     * @see [Transition.removeTarget]
     */
    inline operator fun String.unaryMinus() {
        transition.removeTarget(this)
    }

    /**
     * Add targets of type [Type] to this transition
     *
     * @see [Transition.addTarget]
     */
    inline operator fun <reified Type> Type.unaryPlus() {
        transition.addTarget(Type::class.java)
    }

    /**
     * Remove targets of type [Type] from this transition
     *
     * @see [Transition.removeTarget]
     */
    inline operator fun <reified Type> Type.unaryMinus() {
        transition.removeTarget(Type::class.java)
    }

    /**
     * Add a target [View] to this transition.
     *
     * @see [Transition.addTarget]
     */
    inline operator fun View.unaryPlus() {
        transition.addTarget(this)
    }

    /**
     * Remove a target [View] from this transition.
     *
     * @see [Transition.removeTarget]
     */
    inline operator fun View.unaryMinus() {
        transition.removeTarget(this)
    }

    /**
     * Adds all [views] as targets to this transition.
     *
     * @see [Transition.addTarget]
     */
    inline fun add(vararg views: View) {
        views.forEach { +it }
    }

    /**
     * Adds all view [ids] as targets to this transition.
     *
     * @see [Transition.addTarget]
     */
    inline fun add(@IdRes vararg ids: Int) {
        ids.forEach { transition.addTarget(it) }
    }

    /**
     * Adds all [transitionTargetNames] as targets to this transition
     *
     * @see [Transition.addTarget]
     */
    inline fun add(vararg transitionTargetNames: String) {
        transitionTargetNames.forEach { +it }
    }

    /**
     * Remove all [views] as targets from this transition.
     *
     * @see [Transition.removeTarget]
     */
    inline fun remove(vararg views: View) {
        views.forEach { -it }
    }

    /**
     * Remove all view [ids] as targets from this transition.
     *
     * @see [Transition.removeTarget]
     */
    inline fun remove(@IdRes vararg ids: Int) {
        ids.forEach { transition.removeTarget(it) }
    }

    /**
     * Exclude givens [views] from this transition. The transition will not affect these views during
     * execution.
     *
     * @see [Transition.excludeTarget]
     */
    inline fun exclude(vararg views: View) {
        views.forEach { transition.excludeTarget(it, true) }
    }

    /**
     * Exclude givens [View][Type] from this transition. The transition will not affect views of [Type]
     * during execution.
     *
     * @see [Transition.excludeTarget]
     */
    inline fun <reified Type : View> exclude() {
        transition.excludeTarget(Type::class.java, true)
    }

    /**
     * Exclude givens View [ids] from this transition. The transition will not affect views with
     * this [ids]
     *
     * @see [Transition.excludeTarget]
     */
    inline fun exclude(@IdRes vararg ids: Int) {
        ids.forEach { transition.excludeTarget(it, true) }
    }

    /**
     * Exclude children of given [views] from participating in this transition.
     *
     * @see [Transition.excludeTarget]
     */
    inline fun excludeChildren(vararg views: View) {
        views.forEach { transition.excludeChildren(it, true) }
    }

    /**
     * Exclude children of given [Type] from participating in this transition.
     *
     * @see [Transition.excludeTarget]
     */
    inline fun <reified Type : View> excludeChildren() {
        transition.excludeChildren(Type::class.java, true)
    }

    /**
     * Exclude children of given view [ids] from participating in this transition.
     *
     * @see [Transition.excludeTarget]
     */
    inline fun excludeChildren(@IdRes vararg ids: Int) {
        ids.forEach { transition.excludeChildren(it, true) }
    }

    /**
     * Executes [onEnd] block after [transition] completes.
     * @see [Transition.TransitionListener.onTransitionEnd]
     */
    inline fun onEnd(noinline onEnd: (transition: Transition) -> Unit) = transition.addListener(onEnd = onEnd)

    /**
     * Executes [onStart] during [Transition.TransitionListener.onTransitionStart]
     * @see [Transition.TransitionListener.onTransitionStart]
     */
    inline fun onStart(noinline onStart: (transition: Transition) -> Unit) = transition.addListener(onStart = onStart)

    /**
     * Executes [onCancel] during [Transition.TransitionListener.onTransitionCancel]
     * @see [Transition.TransitionListener.onTransitionCancel]
     */
    inline fun onCancel(noinline onCancel: (transition: Transition) -> Unit) = transition.addListener(onCancel = onCancel)

    /**
     * Executes [onResume] during [Transition.TransitionListener.onTransitionResume]
     * @see [Transition.TransitionListener.onTransitionResume]
     */
    inline fun onResume(noinline onResume: (transition: Transition) -> Unit) = transition.addListener(onResume = onResume)

    /**
     * Executes [onPause] during [Transition.TransitionListener.onTransitionPause]
     * @see [Transition.TransitionListener.onTransitionPause]
     */
    inline fun onPause(noinline onPause: (transition: Transition) -> Unit) = transition.addListener(onEnd = onPause)

    /**
     * Add actions at different stages of [Transition]. Repeated blocks does not override previously
     * added block but instead run one after another.
     *
     * @see [Transition.addListener]
     */
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
