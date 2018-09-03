package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples


import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.transition.changecolor.ChangeColor
import `in`.arunkumarsampath.transitionx.transition
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_custom_transition.*


class CustomTransitionFragment : Fragment() {

    private val colors = listOf(
            Color.BLACK,
            Color.DKGRAY,
            Color.GRAY,
            Color.LTGRAY,
            Color.WHITE,
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.CYAN,
            Color.MAGENTA
    )


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_custom_transition, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {

            val color = colors.shuffled().first()
            constraintLayout.transition {
                customTransition<ChangeColor>()
                +colorChangeTextView
                duration = 1000
                onEnd { _ ->
                    colorChangeTextView.setTextColor(calcForegroundWhiteOrBlack(color))
                }
            }
            colorChangeTextView.background = ColorDrawable(color)
        }
    }

    companion object {
        private const val CONTRAST_LIGHT_ITEM_THRESHOLD = 3f

        /**
         * Calculates the contrast between the given color and white, using the algorithm provided by
         * the WCAG v2 in http://www.w3.org/TR/WCAG20/#contrast-ratiodef.
         *
         *
         * {@see https://chromium.googlesource.com/chromium/src/+/66.0.3335.4/chrome/android/java/src/org/chromium/chrome/browser/util/ColorUtils.java}
         */
        private fun getContrastForColor(color: Int): Float {
            var bgR = Color.red(color) / 255f
            var bgG = Color.green(color) / 255f
            var bgB = Color.blue(color) / 255f
            bgR = if (bgR < 0.03928f) bgR / 12.92f else Math.pow(((bgR + 0.055f) / 1.055f).toDouble(), 2.4).toFloat()
            bgG = if (bgG < 0.03928f) bgG / 12.92f else Math.pow(((bgG + 0.055f) / 1.055f).toDouble(), 2.4).toFloat()
            bgB = if (bgB < 0.03928f) bgB / 12.92f else Math.pow(((bgB + 0.055f) / 1.055f).toDouble(), 2.4).toFloat()
            val bgL = 0.2126f * bgR + 0.7152f * bgG + 0.0722f * bgB
            return Math.abs(1.05f / (bgL + 0.05f))
        }

        /**
         * Check whether lighter or darker foreground elements (i.e. text, drawables etc.)
         * should be used depending on the given background color.
         *
         * @param backgroundColor The background color value which is being queried.
         * @return Whether light colored elements should be used.
         */
        private fun shouldUseLightForegroundOnBackground(backgroundColor: Int): Boolean {
            return getContrastForColor(backgroundColor) >= CONTRAST_LIGHT_ITEM_THRESHOLD
        }

        /**
         * Returns white or black based on color luminance
         *
         * @param backgroundColor the color to get foreground for
         * @return White for darker colors and black for ligher colors
         */
        @ColorInt
        fun calcForegroundWhiteOrBlack(@ColorInt backgroundColor: Int): Int {
            return if (shouldUseLightForegroundOnBackground(backgroundColor)) {
                Color.WHITE
            } else
                Color.BLACK
        }
    }
}
