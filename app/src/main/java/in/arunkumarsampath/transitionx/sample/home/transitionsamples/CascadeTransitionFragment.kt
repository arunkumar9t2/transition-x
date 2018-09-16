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
                listOf(textView,
                        textView2,
                        textView3,
                        textView4
                ).forEachIndexed { position, view ->
                    moveResize {
                        +view
                        startDelay = ((position + 1) * 150).toLong()
                    }
                }
                moveResize { +fab }
                interpolator = LinearOutSlowInInterpolator()
            }
            (if (defaultState) constraint1 else constraint2).applyTo(constraintLayout)
            defaultState = !defaultState
        }
    }
}
