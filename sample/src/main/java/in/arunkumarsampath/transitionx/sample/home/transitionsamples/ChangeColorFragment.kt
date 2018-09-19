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
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import kotlinx.android.synthetic.main.layout_change_color_content.*

class ChangeColorFragment : Fragment() {

    private var toggle = true

    private val brownDrawable by lazy { ColorDrawable(ContextCompat.getColor(requireContext(), R.color.material_brown)) }
    private val whiteDrawable = ColorDrawable(Color.WHITE)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_change_color_transition, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHeaderImage()
        initClickListeners()
    }

    private fun setupHeaderImage() {
        Glide.with(imageView)
                .load(R.drawable.ic_dog_cute)
                .transition(withCrossFade())
                .into(imageView)
    }

    private fun initClickListeners() {
        fab.setOnClickListener {
            frameLayout.prepareTransition {
                +textView
                changeColor()
                duration = 1000
            }
            with(textView) {
                if (toggle) {
                    background = brownDrawable
                    setTextColor(Color.WHITE)
                } else {
                    background = whiteDrawable
                    setTextColor(Color.BLACK)
                }
            }
            toggle = !toggle
        }
    }
}
