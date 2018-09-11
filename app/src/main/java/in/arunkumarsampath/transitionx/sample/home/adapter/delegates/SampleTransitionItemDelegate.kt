package `in`.arunkumarsampath.transitionx.sample.home.adapter.delegates

import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.home.adapter.SampleItem
import `in`.arunkumarsampath.transitionx.sample.util.extensions.inflate
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_sample_item_name_template.*

class SampleTransitionItemDelegate
    : AbsListItemAdapterDelegate<SampleItem.SampleTransitionItem, SampleItem, SampleTransitionItemDelegate.SamplesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup) = SampleTransitionItemDelegate.SamplesViewHolder.create(parent)

    override fun isForViewType(
            item: SampleItem,
            items: MutableList<SampleItem>,
            position: Int
    ) = item is SampleItem.SampleTransitionItem

    override fun onBindViewHolder(
            item: SampleItem.SampleTransitionItem,
            viewHolder: SampleTransitionItemDelegate.SamplesViewHolder,
            payloads: MutableList<Any>
    ) = viewHolder.bindSample(item)

    class SamplesViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindSample(sampleTransitionItem: SampleItem.SampleTransitionItem) {
            sampleName.setText(sampleTransitionItem.name)
            containerView.setOnClickListener(Navigation.createNavigateOnClickListener(sampleTransitionItem.navigationId))
        }

        companion object {
            fun create(viewGroup: ViewGroup) = SamplesViewHolder(viewGroup.inflate(R.layout.layout_sample_item_name_template))
        }
    }
}
