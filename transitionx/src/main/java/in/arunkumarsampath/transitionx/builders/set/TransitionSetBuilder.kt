@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.builders.set

import `in`.arunkumarsampath.transitionx.builders.TransitionBuilder
import `in`.arunkumarsampath.transitionx.builders.changetext.ChangeTextBuilder
import `in`.arunkumarsampath.transitionx.builders.common.*
import `in`.arunkumarsampath.transitionx.builders.fade.FadeBuilder
import `in`.arunkumarsampath.transitionx.builders.fade.FadeMode
import `in`.arunkumarsampath.transitionx.builders.slide.SlideBuilder
import android.support.transition.AutoTransition
import android.support.transition.Fade
import android.support.transition.Transition
import android.support.transition.TransitionSet

open class TransitionSetBuilder<T : TransitionSet>(transitionSet: T) : TransitionBuilder<TransitionSet>(transitionSet) {

    var ordering: Int
        @Ordering get() = transition.ordering
        set(@Ordering value) {
            transition.ordering = value
        }

    inline operator fun Transition.unaryPlus() {
        transition.addTransition(this)
    }

    inline operator fun Transition.unaryMinus() {
        transition.removeTransition(this)
    }

    inline operator fun get(index: Int): Transition = transition.getTransitionAt(index)

    inline fun auto(autoBuilder: AutoTransitionBuilder.() -> Unit = {}) {
        +AutoTransitionBuilder().apply(autoBuilder).transition
    }

    inline fun slide(slideBuilder: SlideBuilder.() -> Unit = {}) {
        +SlideBuilder().apply(slideBuilder).transition
    }

    inline fun fade(@FadeMode fadeMode: Int = Fade.IN or Fade.OUT, fadeBuilder: FadeBuilder.() -> Unit = {}) {
        +FadeBuilder(fadeMode).apply(fadeBuilder).transition
    }

    inline fun scaleRotate(scaleRotateBuilder: ScaleRotateBuilder.() -> Unit = {}) {
        +ScaleRotateBuilder().apply(scaleRotateBuilder).transition
    }

    inline fun changeClipBounds(changeClipBoundsBuilder: ChangeClipBoundsBuilder.() -> Unit = {}) {
        +ChangeClipBoundsBuilder().apply(changeClipBoundsBuilder).transition
    }

    inline fun moveResize(changeBoundsBuilder: ChangeBoundsBuilder.() -> Unit = {}) {
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

    inline fun changeColor(changeColorBuilder: ChangeColorBuilder.() -> Unit = {}) {
        +ChangeColorBuilder().apply(changeColorBuilder).transition
    }

    inline fun changeText(changeTextBuilder: ChangeTextBuilder.() -> Unit = {}) {
        +ChangeTextBuilder().apply(changeTextBuilder).transition
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

    inline fun <T : Transition> customTransition(
            transition: T,
            transitionBuilder: TransitionBuilder<T>.() -> Unit = {}
    ) = +TransitionBuilder(transition).apply(transitionBuilder).transition
}

class DefaultTransitionSetBuilder : TransitionSetBuilder<TransitionSet>(TransitionSet())

class AutoTransitionBuilder : TransitionSetBuilder<TransitionSet>(AutoTransition())