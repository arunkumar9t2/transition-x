package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples

import `in`.arunkumarsampath.transitionx.evaluator.ArgbEvaluator
import `in`.arunkumarsampath.transitionx.prepareTransition
import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.extensions.resolveColorRes
import `in`.arunkumarsampath.transitionx.transitionSet
import android.animation.Animator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.transition.Transition
import android.support.transition.TransitionValues
import android.support.v4.graphics.ColorUtils.setAlphaComponent
import android.support.v4.widget.ImageViewCompat
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isGone
import kotlinx.android.synthetic.main.layout_animated_bottom_navigation_content.*
import kotlin.LazyThreadSafetyMode.NONE

class AnimatedBottomNavigationFragment : BaseSampleFragment() {

    override val contentLayoutResource = R.layout.layout_animated_bottom_navigation_content
    override val titleRes = R.string.animated_bottom_navigation_fragment

    /**
     * Data structure for holding components in each bottom nav item.
     */
    private data class BottomNavigationItem(
            val icon: AppCompatImageView,
            val text: TextView,
            val bg: View,
            @param:ColorRes val colorRes: Int
    )

    /**
     * All [BottomNavigationItem]s
     */
    private val navItems by lazy(NONE) {
        sequenceOf(
                BottomNavigationItem(home, homeText, homeBg, R.color.bottom_nav_home),
                BottomNavigationItem(likes, likesText, likesBg, R.color.bottom_nav_likes),
                BottomNavigationItem(search, searchText, searchBg, R.color.bottom_nav_search),
                BottomNavigationItem(about, aboutText, aboutBg, R.color.bottom_nav_about)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListeners()
    }


    /**
     * A transition instance that animates movement, appear and disappear like [AutoTransition] does
     * but no sequentially. Instead it relies on custom [Transition.setStartDelay] to achieve desired
     * choreography.
     *
     * Additionally, it also animates changes to [AppCompatImageView.setImageTint] property.
     */
    private val bottomNavTransition by lazy {
        transitionSet {
            fadeOut()

            moveResize {
                startDelay = 50
                ease {
                    standardEasing
                }
            }

            fadeIn {
                startDelay = 50
            }

            changeColor {
                navItems.map { it.text }.forEach { text -> add(text) }
                +constraintLayout
            }

            customTransition<ChangeImageTint> {
                navItems.map { it.icon }.forEach { icon -> add(icon) }
            }
        }
    }

    private fun bindListeners() {
        navItems.forEach { navItem ->
            navItem.icon.setOnClickListener {
                // Prepare transition
                constraintLayout.prepareTransition(cancel = true, transition = bottomNavTransition)

                // Reset all other items
                navItems.filter { it != navItem }
                        .forEach { otherNavItem ->
                            otherNavItem.run {
                                icon.setImageTint(android.R.color.black)
                                text.run {
                                    setTextColor(context.resolveColorRes(android.R.color.black))
                                    isGone = true
                                }
                                bg.isGone = true
                            }
                        }

                // Apply changes to current item
                navItem.run {
                    val selectedColor = requireContext().resolveColorRes(colorRes)

                    icon.setImageTint(colorRes)
                    text.run {
                        setTextColor(selectedColor)
                        isGone = false
                    }
                    bg.run {
                        isGone = false
                        (background as GradientDrawable).setColor(setAlphaComponent(selectedColor, (0.3 * 255).toInt()))
                    }

                    constraintLayout.background = ColorDrawable(selectedColor)
                }
            }
        }
    }

    /**
     * Animates changes to [AppCompatImageView.setImageTint]
     */
    private class ChangeImageTint : Transition() {

        private fun captureValues(transitionValues: TransitionValues) {
            val imageView = transitionValues.view as? AppCompatImageView
            imageView?.let {
                val color = ImageViewCompat.getImageTintList(imageView)?.defaultColor ?: -1
                transitionValues.values[PROPNAME_IMAGE_TINT] = color
            }
        }

        override fun captureStartValues(transitionValues: TransitionValues) =
                captureValues(transitionValues)

        override fun captureEndValues(transitionValues: TransitionValues) =
                captureValues(transitionValues)

        override fun createAnimator(
                sceneRoot: ViewGroup,
                startValues: TransitionValues?,
                endValues: TransitionValues?
        ): Animator? {

            if (startValues == null || endValues == null) {
                return null
            }

            val imageView = endValues.view as? AppCompatImageView
            val startTint = startValues.values[PROPNAME_IMAGE_TINT] as Int
            val endTint = endValues.values[PROPNAME_IMAGE_TINT] as Int

            if (startTint != -1 && endTint != -1 && imageView != null) {
                return ValueAnimator.ofObject(
                        ArgbEvaluator,
                        startTint,
                        endTint
                ).apply {
                    addUpdateListener { animation ->
                        animation.animatedValue?.let { color ->
                            imageView.setImageTintColor(color as Int)
                        }
                    }
                }
            }

            return super.createAnimator(sceneRoot, startValues, endValues)
        }

        companion object {
            private const val PROPNAME_IMAGE_TINT = "android:imageview:tint"
        }
    }

    companion object {

        private fun AppCompatImageView.setImageTintColor(@ColorInt colorInt: Int) {
            ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(colorInt))
        }

        private fun AppCompatImageView.setImageTint(@ColorRes colorRes: Int) {
            ImageViewCompat.setImageTintList(
                    this,
                    ColorStateList.valueOf(context.resolveColorRes(colorRes))
            )
        }
    }
}