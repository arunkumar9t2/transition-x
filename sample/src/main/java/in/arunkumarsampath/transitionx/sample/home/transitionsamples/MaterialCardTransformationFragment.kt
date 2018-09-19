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
import `in`.arunkumarsampath.transitionx.sample.extensions.removeCallbacks
import `in`.arunkumarsampath.transitionx.transition.changetext.ChangeText
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_material_card_transformation.*

class MaterialCardTransformationFragment : Fragment() {

    private val requestManager by lazy { Glide.with(this) }

    private var expand = true

    private val collapseConstraint
        get() = ConstraintSet().apply {
            clone(requireContext(), R.layout.layout_material_card_transformation)
        }
    private val expandConstraint
        get() = ConstraintSet().apply {
            clone(requireContext(), R.layout.layout_material_card_transformation_expanded)
        }

    private val expandRunnable = { expandTransition() }
    private val collapseRunnable = { collapseTransition() }

    private val imageViews = arrayOf(image1, image2, image3, image4, image5)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_material_card_transformation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        requestManager
                .load(R.drawable.ic_girl)
                .transition(withCrossFade())
                .apply(RequestOptions().circleCrop())
                .into(avatar)
        listOf(R.drawable.ic_artic_1 to image1,
                R.drawable.ic_artic_2 to image2,
                R.drawable.ic_artic_3 to image3,
                R.drawable.ic_artic_4 to image4,
                R.drawable.ic_artic_5 to image5
        ).forEach { (drawable, image) ->
            requestManager.load(drawable)
                    .transition(withCrossFade())
                    .apply(RequestOptions().centerCrop())
                    .into(image)
        }
        with(collapseButton) {
            setOnClickListener {
                removeCallbacks(expandRunnable, collapseRunnable)
                val runnable = if (expand) expandRunnable else collapseRunnable
                postDelayed(runnable, 100) // Add delay to allow ripple animation to run
                expand = !expand
            }
        }
    }

    private fun collapseTransition() {
        constraintLayout.prepareTransition {
            auto {
                standardEasing()
                exclude(metamorphosisDesc2)
            }
            transitionSet {
                fade()
                slide()
                accelerateEasing()
                +metamorphosisDesc2
            }
            changeImage { add(*imageViews) }
            onEnd {
                constraintLayout.prepareTransition {
                    moveResize()
                    changeText {
                        +collapseButton
                        changeTextBehavior = ChangeText.CHANGE_BEHAVIOR_OUT_IN
                    }
                }
                collapseButton.setText(R.string.expand)
            }
            duration = 300
        }
        collapseConstraint.applyTo(constraintLayout)
        metamorphosisDesc2.isGone = true
        metamorphosisDesc.isGone = false
    }

    private fun expandTransition() {
        constraintLayout.prepareTransition {
            auto {
                standardEasing()
                exclude(metamorphosisDesc2)
            }
            transitionSet {
                fade()
                slide()
                accelerateEasing()
                +metamorphosisDesc2
            }
            changeImage { add(*imageViews) }
            onEnd {
                constraintLayout.prepareTransition {
                    moveResize()
                    changeText {
                        +collapseButton
                        changeTextBehavior = ChangeText.CHANGE_BEHAVIOR_OUT_IN
                    }
                }
                collapseButton.setText(R.string.collapse)
            }
            duration = 300
        }
        expandConstraint.applyTo(constraintLayout)
        metamorphosisDesc2.isGone = false
        metamorphosisDesc.isGone = true
    }
}
