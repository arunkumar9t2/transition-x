package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples


import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.extensions.dpToPx
import `in`.arunkumarsampath.transitionx.transition
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.transition.ArcMotion
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_arc_motion_content.*

class ArcMotionFragment : Fragment() {

    private var toggle = true

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_arc_motion, container, false)

    @SuppressLint("RtlHardcoded")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMeow()
        initClickListeners()
    }

    private fun setupMeow() {
        Glide.with(userIconView)
                .load(R.drawable.cute_cat)
                .apply(RequestOptions().circleCrop())
                .into(userIconView)
    }

    private fun initClickListeners() {
        fab.setOnClickListener {

            frameLayout.transition {
                moveResize {
                    pathMotion = ArcMotion()
                    +userIconView
                }
            }

            with(userIconView) {
                if (toggle) {
                    updateLayoutParams<FrameLayout.LayoutParams> {
                        height = context.dpToPx(112.0)
                        width = context.dpToPx(112.0)
                        gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
                    }
                } else {
                    updateLayoutParams<FrameLayout.LayoutParams> {
                        height = context.dpToPx(56.0)
                        width = context.dpToPx(56.0)
                        gravity = Gravity.START or Gravity.LEFT or Gravity.CENTER_VERTICAL
                    }
                }
                toggle = !toggle
            }
        }
    }
}
