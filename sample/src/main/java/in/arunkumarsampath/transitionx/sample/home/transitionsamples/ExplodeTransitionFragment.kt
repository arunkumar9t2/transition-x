/*
 *
 * Copyright 2019 Arunkumar
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
 *
 */

package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples

import `in`.arunkumarsampath.transitionx.prepareTransition
import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.extensions.dpToPx
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
            frameLayout.prepareTransition {
                explode {
                    +accentBackground
                }
                moveResize {
                    +userIconView
                }
            }

            if (toggle) {
                with(requireContext()) {
                    userIconView.updateLayoutParams<LinearLayout.LayoutParams> {
                        height = dpToPx(112.0)
                        width = dpToPx(112.0)
                    }
                }
                accentBackground.isGone = true
                helloText.isGone = false
            } else {
                with(requireContext()) {
                    userIconView.updateLayoutParams<LinearLayout.LayoutParams> {
                        height = dpToPx(56.0)
                        width = dpToPx(56.0)
                    }
                }
                accentBackground.isGone = false
                helloText.isGone = true
            }
            toggle = !toggle
        }
    }
}
