package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples


import `in`.arunkumarsampath.transitionx.prepareTransition
import `in`.arunkumarsampath.transitionx.sample.R
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
        collapseButton.setOnClickListener {
            if (expand) {
                expandTransition()
            } else {
                collapseTransition()
            }
            expand = !expand
        }
    }

    private fun collapseTransition() {
        constraintLayout.prepareTransition {
            auto {
                standardEasing()
                exclude(metamorphosisDesc2)
            }
            set {
                fade()
                slide()
                accelerateEasing()
                +metamorphosisDesc2
            }
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
            duration = 500
        }
        metamorphosisDesc.isGone = false
        metamorphosisDesc2.isGone = true
        collapseConstraint.applyTo(constraintLayout)
    }

    private fun expandTransition() {
        constraintLayout.prepareTransition {
            auto {
                standardEasing()
                exclude(metamorphosisDesc2)
            }
            set {
                fade()
                slide()
                accelerateEasing()
                +metamorphosisDesc2
            }
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
            duration = 500
        }
        metamorphosisDesc.isGone = true
        metamorphosisDesc2.isGone = false
        expandConstraint.applyTo(constraintLayout)
    }
}
