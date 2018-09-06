package `in`.arunkumarsampath.transitionx.sample.home.adapter

import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.util.extensions.inflate
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_sample_item_name_template.*

class SamplesAdapter : RecyclerView.Adapter<SamplesAdapter.SamplesViewHolder>() {

    data class SampleTransition(@param:IdRes val navigationId: Int, @param:StringRes val name: Int)

    private val sampleTransitions = listOf(
            sample(R.id.snackBarFragment, R.string.sample_snackbar_fab_transition),
            sample(R.id.cascadeTransitionFragment, R.string.sample_cascade_transition),
            sample(R.id.customTransitionFragment, R.string.sample_custom_transition),
            sample(R.id.scaleRotateFragment, R.string.scale_rotate_transition),
            sample(R.id.arcMotionFragment, R.string.arc_motion_transition),
            sample(R.id.explodeTransitionFragment, R.string.explode_transition)
    )

    override fun onCreateViewHolder(
            parent: ViewGroup,
            itemType: Int
    ) = SamplesViewHolder.create(parent)

    override fun getItemCount() = sampleTransitions.count()

    override fun onBindViewHolder(holder: SamplesViewHolder, position: Int) =
            holder.bindSample(sampleTransitions[position])


    class SamplesViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindSample(sampleTransition: SampleTransition) {
            sampleName.setText(sampleTransition.name)
            containerView.setOnClickListener(Navigation.createNavigateOnClickListener(sampleTransition.navigationId))
        }

        companion object {
            fun create(viewGroup: ViewGroup) = SamplesViewHolder(viewGroup.inflate(R.layout.layout_sample_item_name_template))
        }
    }

    companion object {

        fun sample(@IdRes navigationId: Int, @StringRes name: Int) = SampleTransition(navigationId, name)
    }
}