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
import `in`.arunkumarsampath.transitionx.sample.extensions.dpToPx
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_change_image_content.*

class ChangeImageTransform : Fragment() {

    val dp112 by lazy { requireContext().dpToPx(112.0) }

    private var toggle = true

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_change_image_transition, container, false)

    @SuppressLint("RtlHardcoded")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMeow()
        initClickListeners()
    }

    private fun setupMeow() {
        frameLayout.post {
            Glide.with(userIconView)
                    .load(R.drawable.cute_cat)
                    .apply(RequestOptions().override(frameLayout.width))
                    .into(userIconView)
        }
    }

    private fun initClickListeners() {
        fab.setOnClickListener {

            frameLayout.prepareTransition {
                moveResize()
                changeImage()
                +userIconView
            }

            with(userIconView) {
                if (toggle) {
                    updateLayoutParams<FrameLayout.LayoutParams> {
                        height = frameLayout.height / 2
                        width = FrameLayout.LayoutParams.MATCH_PARENT
                    }
                    scaleType = ImageView.ScaleType.CENTER_CROP
                } else {
                    updateLayoutParams<FrameLayout.LayoutParams> {
                        height = dp112
                        width = dp112
                    }
                    scaleType = ImageView.ScaleType.CENTER
                }
                toggle = !toggle
            }
        }
    }
}
