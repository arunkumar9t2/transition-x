@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.builders

import `in`.arunkumarsampath.transitionx.builders.common.*
import `in`.arunkumarsampath.transitionx.builders.fade.FadeBuilder
import `in`.arunkumarsampath.transitionx.builders.fade.FadeMode
import `in`.arunkumarsampath.transitionx.builders.slide.SlideBuilder
import android.support.transition.Fade
import android.support.transition.Transition
import android.support.transition.TransitionSet

class TransitionSetBuilder : TransitionBuilder<TransitionSet>(TransitionSet()) {

    inline operator fun Transition.unaryPlus() {
        transition.addTransition(this)
    }

    inline operator fun Transition.unaryMinus() {
        transition.removeTransition(this)
    }

    inline operator fun get(index: Int): Transition = transition.getTransitionAt(index)

    inline fun slide(slideBuilder: SlideBuilder.() -> Unit = {}) {
        +SlideBuilder().apply(slideBuilder).transition
    }

    inline fun fade(@FadeMode fadeMode: Int = Fade.IN or Fade.OUT, fadeBuilder: FadeBuilder.() -> Unit = {}) {
        +FadeBuilder(fadeMode).apply(fadeBuilder).transition
    }

    inline fun changeTransform(changeTransformBuilder: ChangeTransformBuilder.() -> Unit = {}) {
        +ChangeTransformBuilder().apply(changeTransformBuilder).transition
    }

    inline fun changeClipBounds(changeClipBoundsBuilder: ChangeClipBoundsBuilder.() -> Unit = {}) {
        +ChangeClipBoundsBuilder().apply(changeClipBoundsBuilder).transition
    }

    inline fun changeBounds(changeBoundsBuilder: ChangeBoundsBuilder.() -> Unit = {}) {
        +ChangeBoundsBuilder().apply(changeBoundsBuilder).transition
    }

    inline fun changeImage(changeImageBuilder: ChangeImageBuilder.() -> Unit = {}) {
        +ChangeImageBuilder().apply(changeImageBuilder).transition
    }

    inline fun changeScroll(changeScrollBuilder: ChangeScrollBuilder.() -> Unit = {}) {
        +ChangeScrollBuilder().apply(changeScrollBuilder).transition
    }

    inline fun explode(explodeBuilder: ExplodeBuilder.() -> Unit = {}) {
        +ExplodeBuilder().apply(explodeBuilder).transition
    }

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

    inline fun <T : Transition> customTransition(transition: T, transitionBuilder: TransitionBuilder<T>.() -> Unit = {}) {
        +TransitionBuilder(transition).apply(transitionBuilder).transition
    }
}
