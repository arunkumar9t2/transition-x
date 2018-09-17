package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples


import `in`.arunkumarsampath.transitionx.prepareAutoTransition
import `in`.arunkumarsampath.transitionx.prepareTransition
import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.extensions.dpToPx
import `in`.arunkumarsampath.transitionx.transition.changetext.ChangeText
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.updateLayoutParams
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_material_card_transformation.*

class MaterialCardTransformationFragment : Fragment() {

    private val requestManager by lazy { Glide.with(this) }

    private var expand = true

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
                R.drawable.ic_artic_4 to image4
        ).forEach { (drawable, image) ->
            requestManager.load(drawable)
                    .transition(withCrossFade())
                    .apply(RequestOptions().centerCrop())
                    .into(image)
        }
        collapseButton.setOnClickListener {
            toggleTransition()
        }
    }

    private fun toggleTransition() {
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
                constraintLayout.prepareAutoTransition {
                    changeText {
                        +collapseButton
                        changeTextBehavior = ChangeText.CHANGE_BEHAVIOR_OUT_IN
                    }
                    startDelay = 50
                }
                collapseButton.setText(if (!expand) R.string.collapse else R.string.expand)
            }
            duration = 500
        }
        if (expand) {
            sceneFlexbox.updateLayoutParams<ConstraintLayout.LayoutParams> {
                height = requireActivity().dpToPx(200.0)
            }
            metamorphosisDesc.isGone = true
            metamorphosisDesc2.isGone = false
        } else {
            sceneFlexbox.updateLayoutParams<ConstraintLayout.LayoutParams> {
                height = requireActivity().dpToPx(56.0)
            }
            metamorphosisDesc.isGone = false
            metamorphosisDesc2.isGone = true
        }
        expand = !expand
    }
}
