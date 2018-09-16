package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples

import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.extensions.toggleGone
import `in`.arunkumarsampath.transitionx.transition
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_snackbar_content.*


class SnackBarFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_snack_bar_fragmennt, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListeners()
    }

    private fun bindListeners() {
        fab.setOnClickListener {
            snackbarConstraintLayout.transition {
                moveResize {
                    +fab
                }
                slide {
                    +snackbarMessage
                }
                interpolator = LinearOutSlowInInterpolator()
            }
            snackbarMessage.toggleGone()
        }
    }
}
