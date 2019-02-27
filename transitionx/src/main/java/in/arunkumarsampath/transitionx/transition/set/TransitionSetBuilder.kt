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

@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.transition.set

import `in`.arunkumarsampath.transitionx.transition.TransitionBuilder
import `in`.arunkumarsampath.transitionx.transition.changecolor.ChangeColor
import `in`.arunkumarsampath.transitionx.transition.changetext.ChangeText
import `in`.arunkumarsampath.transitionx.transition.changetext.ChangeTextBuilder
import `in`.arunkumarsampath.transitionx.transition.common.*
import `in`.arunkumarsampath.transitionx.transition.fade.FadeBuilder
import `in`.arunkumarsampath.transitionx.transition.fade.FadeMode
import `in`.arunkumarsampath.transitionx.transition.slide.SlideBuilder
import android.support.transition.*
import android.support.transition.TransitionSet.ORDERING_SEQUENTIAL
import android.support.transition.TransitionSet.ORDERING_TOGETHER
import android.view.View
import android.widget.ImageView

/**
 * [TransitionBuilder] implementation for constructing [TransitionSet] instances.
 *
 * @see [TransitionSet]
 */
open class TransitionSetBuilder<T : TransitionSet>(transitionSet: T) : TransitionBuilder<TransitionSet>(transitionSet) {
    /**
     * Runs all [Transition]s contained in this [TransitionSet] sequentially
     *
     * @see [TransitionSet.ORDERING_SEQUENTIAL]
     */
    inline fun sequentially() {
        transition.ordering = ORDERING_SEQUENTIAL
    }

    /**
     * Runs all [Transition]s contained in this [TransitionSet] together
     *
     * @see [TransitionSet.ORDERING_TOGETHER]
     */
    inline fun together() {
        transition.ordering = ORDERING_TOGETHER
    }

    /**
     * Add a [transition] to this transition set.
     *
     * @see [TransitionSet.addTransition]
     */
    inline operator fun Transition.unaryPlus() {
        transition.addTransition(this)
    }

    /**
     * Remove a [transition] from this transition set.
     *
     * @see [TransitionSet.addTransition]
     */
    inline operator fun Transition.unaryMinus() {
        transition.removeTransition(this)
    }

    /**
     * Returns the [Transition] at [index]
     *
     * @see [TransitionSet.getTransitionAt]
     */
    inline operator fun get(index: Int): Transition = transition.getTransitionAt(index)

    /**
     * Create another [TransitionSet] and add it to this transition set. The block [setBuilder]
     * can contain code to configure the created [TransitionSet]
     *
     * Example :
     * ```
     *      constraintLayout.prepareTransition {
     *          auto {
     *              standardEasing()
     *          }
     *          transitionSet {
     *              fade()
     *              slide()
     *          }
     *      }
     * ```
     */
    inline fun transitionSet(setBuilder: DefaultTransitionSetBuilder.() -> Unit = {}) {
        +DefaultTransitionSetBuilder().apply(setBuilder).transition
    }

    /**
     * Adds an [AutoTransition] to this transition
     *
     * @see [AutoTransition]
     */
    inline fun auto(autoBuilder: AutoTransitionBuilder.() -> Unit = {}) {
        +AutoTransitionBuilder().apply(autoBuilder).transition
    }

    /**
     * Adds a [Slide] transition to this transition. [Slide] triggers on visibility changes.
     *
     * @see [Visibility]
     */
    inline fun slide(slideBuilder: SlideBuilder.() -> Unit = {}) {
        +SlideBuilder().apply(slideBuilder).transition
    }

    /**
     * Adds a [Fade] transition to this transition. [Fade] triggers on visibility changes.
     *
     * [fadeMode] can be used to configure whether this fade transition triggers on [Visibility.onAppear]
     * or [Visibility.onDisappear] or both.
     *
     * @see [Visibility]
     * @see [Fade]
     * @see [FadeMode]
     */
    inline fun fade(@FadeMode fadeMode: Int = Fade.IN or Fade.OUT, fadeBuilder: FadeBuilder.() -> Unit = {}) {
        +FadeBuilder(fadeMode).apply(fadeBuilder).transition
    }

    /**
     * Adds a [Fade] transition that triggers only when the views disappear as per condition described
     * by [Visibility.isVisible]
     */
    inline fun fadeOut(fadeBuilder: FadeBuilder.() -> Unit = {}) = fade(Fade.OUT, fadeBuilder)

    /**
     * Adds a [Fade] transition that triggers only when the views appear as per condition described
     * by [Visibility.isVisible]
     */
    inline fun fadeIn(fadeBuilder: FadeBuilder.() -> Unit = {}) = fade(Fade.IN, fadeBuilder)

    /**
     * Adds a [ChangeTransform] to this transition. This transition animates [View]'s scale or rotate
     * properties change.
     *
     * @see [ChangeTransform]
     */
    inline fun scaleRotate(scaleRotateBuilder: ScaleRotateBuilder.() -> Unit = {}) {
        +ScaleRotateBuilder().apply(scaleRotateBuilder).transition
    }

    /**
     * Adds a [ChangeClipBounds] transition to this transition that animates changes to [ChangeClipBounds]
     */
    inline fun changeClipBounds(changeClipBoundsBuilder: ChangeClipBoundsBuilder.() -> Unit = {}) {
        +ChangeClipBoundsBuilder().apply(changeClipBoundsBuilder).transition
    }

    /**
     * Adds a [ChangeBounds] transition to this transition that animates when [View]s move or change
     * their bounds.
     */
    inline fun moveResize(changeBoundsBuilder: ChangeBoundsBuilder.() -> Unit = {}) {
        +ChangeBoundsBuilder().apply(changeBoundsBuilder).transition
    }

    /**
     * Adds a [ChangeImageTransform] transition to this transtion that animates changes to [ImageView]'s
     * image matrix and [ImageView.ScaleType]
     */
    inline fun changeImage(changeImageBuilder: ChangeImageBuilder.() -> Unit = {}) {
        +ChangeImageBuilder().apply(changeImageBuilder).transition
    }

    /**
     * Adds [ChangeScroll] transition to this transition that animates changes to [View]'s scroll
     * properties
     */
    inline fun changeScroll(changeScrollBuilder: ChangeScrollBuilder.() -> Unit = {}) {
        +ChangeScrollBuilder().apply(changeScrollBuilder).transition
    }

    /**
     * Adds an [Explode] transition to this transition that does explode transition when [View]'s
     * visibility changes as described by [Visibility]
     */
    inline fun explode(explodeBuilder: ExplodeBuilder.() -> Unit = {}) {
        +ExplodeBuilder().apply(explodeBuilder).transition
    }

    /**
     * Adds [ChangeColor] transition to this transition.
     * @see [ChangeColor]
     */
    inline fun changeColor(changeColorBuilder: ChangeColorBuilder.() -> Unit = {}) {
        +ChangeColorBuilder().apply(changeColorBuilder).transition
    }

    /**
     * Adds [ChangeText] transition to this transition.
     *
     * @see [ChangeText]
     */
    inline fun changeText(changeTextBuilder: ChangeTextBuilder.() -> Unit = {}) {
        +ChangeTextBuilder().apply(changeTextBuilder).transition
    }

    /**
     * Instantiates [Transition] of type [T] and adds to this transition. The [T] type must have a public
     * no arg constructor for the instantiation to work.
     *
     * If the custom transition cannot have a no arg public constructor then, you can instantiate the
     * transition yourself and call [customTransition]
     *
     * @see customTransition(transition: T, transitionBuilder: TransitionBuilder<T>.() -> Unit)
     * @throws IllegalArgumentException if the type [T] does not have a public no arg constructor.
     */
    inline fun <reified T : Transition> customTransition(transitionBuilder: TransitionBuilder<T>.() -> Unit = {}) {
        val transitionInstance: T = try {
            T::class.java.newInstance()
        } catch (e: Exception) {
            throw  IllegalArgumentException("Could not instantiate type ${T::class.java.simpleName}. " +
                    "If it does not have a public no-arg constructor, " +
                    "instantiate ${T::class.java.simpleName} and use customTransition(transition, builder) instead")
        }
        customTransition(transitionInstance, transitionBuilder)
    }

    /**
     * Alternative to [customTransition] that takes an already instantiated [transition] instance and
     * allows additional configuration via the [transitionBuilder] block.
     *
     * This method is preferred for cases where [T] transition does not have a public no-arg constructor
     */
    inline fun <T : Transition> customTransition(
            transition: T,
            transitionBuilder: TransitionBuilder<T>.() -> Unit = {}
    ) = +TransitionBuilder(transition).apply(transitionBuilder).transition
}