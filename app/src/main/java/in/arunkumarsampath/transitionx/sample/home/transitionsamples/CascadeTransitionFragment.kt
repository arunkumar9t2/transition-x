package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples


import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.transition
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_cascade_transition.*

class CascadeTransitionFragment : Fragment() {

    private var defaultState = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_cascade_transition, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransitionListeners()
    }

    private fun initTransitionListeners() {
        fab.setOnClickListener {
            val constraint1 = ConstraintSet().apply {
                clone(requireContext(), R.layout.fragment_cascade_transition)
            }
            val constraint2 = ConstraintSet().apply {
                clone(requireContext(), R.layout.fragment_cascade_transition_alt)
            }
            constraintLayout.transition {
                changeBounds {
                    +textView
                    startDelay = 100
                }
                changeBounds {
                    +textView2
                    startDelay = 200
                }
                changeBounds {
                    +textView3
                    startDelay = 300
                }
                changeBounds {
                    +textView4
                    startDelay = 400
                }
                changeBounds { +fab }
                interpolator = LinearOutSlowInInterpolator()
            }
            (if (defaultState) constraint1 else constraint2).applyTo(constraintLayout)
            defaultState = !defaultState
        }
    }
}
