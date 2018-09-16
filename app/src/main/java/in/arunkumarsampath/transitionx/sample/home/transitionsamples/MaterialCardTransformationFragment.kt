package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples


import `in`.arunkumarsampath.transitionx.sample.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_material_card_transformation.*

class MaterialCardTransformationFragment : Fragment() {

    private val requestManager by lazy { Glide.with(this) }

    private var toggle = true

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
    }
}
