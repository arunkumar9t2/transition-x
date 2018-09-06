package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples

import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.extensions.dpToPx
import `in`.arunkumarsampath.transitionx.transition
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isGone
import androidx.core.view.updateLayoutParams
import kotlinx.android.synthetic.main.layout_explode_transition_content.*


class ExplodeTransitionFragment : Fragment() {

    private var toggle = true

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_explode_transition, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewListeners()
    }

    private fun initViewListeners() {
        fab.setOnClickListener {
            frameLayout.transition {
                explode {
                    +accentBackground
                }
                changeBounds {
                    +userIconView
                }
            }

            if (toggle) {
                with(requireContext()) {
                    userIconView.updateLayoutParams<LinearLayout.LayoutParams> {
                        height = dpToPx(112.0).toInt()
                        width = dpToPx(112.0).toInt()
                    }
                }
                accentBackground.isGone = true
                helloText.isGone = false
            } else {
                with(requireContext()) {
                    userIconView.updateLayoutParams<LinearLayout.LayoutParams> {
                        height = dpToPx(56.0).toInt()
                        width = dpToPx(56.0).toInt()
                    }
                }
                accentBackground.isGone = false
                helloText.isGone = true
            }
            toggle = !toggle
        }
    }
}
