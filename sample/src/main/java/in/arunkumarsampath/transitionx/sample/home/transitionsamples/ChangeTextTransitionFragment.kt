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
import `in`.arunkumarsampath.transitionx.transition.changetext.ChangeText
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.layout_change_text_transition.*

class ChangeTextTransitionFragment : BaseSampleFragment() {

    override val contentLayoutResource = R.layout.layout_change_text_transition
    override val titleRes = R.string.change_text_transition

    private val mockStrings = listOf(
            R.string.lorem_ipsum,
            R.string.vivamus_bibendum,
            R.string.pellentesque_mattis,
            R.string.change_text_transition
    )

    private var counter: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTransitionListeners()
    }

    private fun initTransitionListeners() {
        fab.setOnClickListener {
            constraintLayout.prepareTransition {
                changeText {
                    changeTextBehavior = ChangeText.CHANGE_BEHAVIOR_OUT_IN
                }
                +textView
            }
            textView.setText(mockStrings[++counter % mockStrings.size])
        }
    }
}
