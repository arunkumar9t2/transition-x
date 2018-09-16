package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples


import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.prepareTransition
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_scale_rotate_content.*


class ScaleRotateFragment : Fragment() {

    private var toggle = true

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_scale_rotate, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener {

            constraintLayout.prepareTransition {
                scaleRotate {
                    +arrowIconView
                    interpolator = FastOutLinearInInterpolator()
                }
            }

            with(arrowIconView) {
                if (toggle) {
                    scaleY = 2F
                    scaleX = 2F
                } else {
                    scaleY = 1F
                    scaleX = 1F
                }
                rotation += 90F
                toggle = !toggle
            }
        }
    }
}
