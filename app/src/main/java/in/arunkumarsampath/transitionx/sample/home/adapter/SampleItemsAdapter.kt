package `in`.arunkumarsampath.transitionx.sample.home.adapter

import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.home.adapter.delegates.HeaderItemDelegate
import `in`.arunkumarsampath.transitionx.sample.home.adapter.delegates.SampleTransitionItemDelegate
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager

class SampleItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegatesManager = AdapterDelegatesManager<List<SampleItem>>().apply {
        addDelegate(SampleTransitionItemDelegate())
        addDelegate(HeaderItemDelegate())
    }

    private val sampleItems = listOf(
            header(R.string.simple_transitions),
            sample(R.id.snackBarFragment, R.string.sample_snackbar_fab_transition),
            sample(R.id.cascadeTransitionFragment, R.string.sample_cascade_transition),
            sample(R.id.customTransitionFragment, R.string.sample_custom_transition),
            sample(R.id.scaleRotateFragment, R.string.scale_rotate_transition),
            sample(R.id.arcMotionFragment, R.string.arc_motion_transition),
            sample(R.id.changeImageTransform, R.string.change_image_transition)
    )

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(sampleItems, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, itemType)
    }

    override fun getItemCount() = sampleItems.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(sampleItems, position, holder)
    }

    companion object {
        fun sample(
                @IdRes navigationId: Int,
                @StringRes name: Int
        ) = SampleItem.SampleTransitionItem(navigationId, name)

        fun header(@StringRes headerName: Int) = SampleItem.HeaderItem(headerName)
    }
}