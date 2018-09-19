/*
 * Copyright 2018 Arunkumar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples


import `in`.arunkumarsampath.transitionx.prepareTransition
import `in`.arunkumarsampath.transitionx.sample.R
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
            constraintLayout.prepareTransition {
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
