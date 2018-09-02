package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples


import `in`.arunkumarsampath.transitionx.sample.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CascadeTransitionFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_cascade_transition, container, false)
}
