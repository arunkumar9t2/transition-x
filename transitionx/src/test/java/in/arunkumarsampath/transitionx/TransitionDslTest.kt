package `in`.arunkumarsampath.transitionx

import `in`.arunkumarsampath.transitionx.builders.TransitionSetBuilder
import android.support.transition.*
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [19, 21, 24])
class TransitionDslTest {

    // To test DSL, it is sufficient to construct a transition set with given DSL syntax and test
    // if generated transition test contains correct transitions as per DSL.
    @Test
    fun testTransitionSetDsl() {
        val transition = TransitionSetBuilder().apply {
            slide {
                duration = 200
            }
            fade {
                interpolator = LinearOutSlowInInterpolator()
            }
            changeTransform {
                startDelay = 200
            }
            changeClipBounds()
            changeBounds()
            changeImage()
            changeScroll()
            explode()
        }.transition

        with(transition) {
            assert(getTransitionAt(0) is Slide)
            assert(getTransitionAt(0).duration == 200L)
            assert(getTransitionAt(1) is Fade)
            assert(getTransitionAt(1).interpolator is LinearOutSlowInInterpolator)
            assert(getTransitionAt(2) is ChangeTransform)
            assert(getTransitionAt(2).startDelay == 200L)
            assert(getTransitionAt(3) is ChangeClipBounds)
            assert(getTransitionAt(4) is ChangeBounds)
            assert(getTransitionAt(5) is ChangeImageTransform)
            assert(getTransitionAt(6) is ChangeScroll)
            assert(getTransitionAt(7) is Explode)
        }
    }
}