package `in`.arunkumarsampath.transitionx.sample.transition.color.changecolor

import `in`.arunkumarsampath.transitionx.sample.transition.ArgbEvaluator
import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.transition.Transition
import android.support.transition.TransitionValues
import android.view.ViewGroup


/**
 * Implementation copied from AOSP Samples.
 *
 * https://github.com/googlesamples/android-CustomTransition
 */
class ChangeColor : Transition() {

    private fun captureValues(values: TransitionValues) {
        // Capture the property values of views for later use
        values.values[PROPNAME_BACKGROUND] = values.view.background
    }

    override fun captureStartValues(transitionValues: TransitionValues) = captureValues(transitionValues)

    override fun captureEndValues(transitionValues: TransitionValues) = captureValues(transitionValues)

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
        // This transition can only be applied to views that are on both starting and ending scenes.
        if (null == startValues || null == endValues) {
            return null
        }
        // Store a convenient reference to the target. Both the starting and ending layout have the
        // same target.
        val view = endValues.view
        // Store the object containing the background property for both the starting and ending
        // layouts.
        val startBackground = startValues.values[PROPNAME_BACKGROUND] as Drawable
        val endBackground = endValues.values[PROPNAME_BACKGROUND] as Drawable
        // This transition changes background colors for a target. It doesn't animate any other
        // background changes. If the property isn't a ColorDrawable, ignore the target.
        if (startBackground is ColorDrawable && endBackground is ColorDrawable) {
            // If the background color for the target in the starting and ending layouts is
            // different, create an animation.
            if (startBackground.color != endBackground.color) {
                // Create a new Animator object to apply to the targets as the transitions framework
                // changes from the starting to the ending layout. Use the class ValueAnimator,
                // which provides a timing pulse to change property values provided to it. The
                // animation runs on the UI thread. The Evaluator controls what type of
                // interpolation is done. In this case, an ArgbEvaluator interpolates between two
                // #argb values, which are specified as the 2nd and 3rd input arguments.
                val animator = ValueAnimator.ofObject(ArgbEvaluator.instance, startBackground.color, endBackground.color)
                // Add an update listener to the Animator object.
                animator.addUpdateListener { animation ->
                    val value = animation.animatedValue
                    // Each time the ValueAnimator produces a new frame in the animation, change
                    // the background color of the target. Ensure that the value isn't null.
                    if (null != value) {
                        view.setBackgroundColor(value as Int)
                    }
                }
                // Return the Animator object to the transitions framework. As the framework changes
                // between the starting and ending layouts, it applies the animation you've created.
                return animator
            }
        }
        // For non-ColorDrawable backgrounds, we just return null, and no animation will take place.
        return null
    }

    companion object {
        private const val PROPNAME_BACKGROUND = "transition:change_color:background"
    }
}