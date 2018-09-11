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

class SampleItemDelegate : AbsListItemAdapterDelegate<SampleItem.SampleTransition, SampleItem, SampleItemDelegate.SamplesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup) = SampleItemDelegate.SamplesViewHolder.create(parent)

    override fun isForViewType(
            item: SampleItem,
            items: MutableList<SampleItem>,
            position: Int
    ) = item is SampleItem.SampleTransition

    override fun onBindViewHolder(
            item: SampleItem.SampleTransition,
            viewHolder: SampleItemDelegate.SamplesViewHolder,
            payloads: MutableList<Any>
    ) = viewHolder.bindSample(item)

    class SamplesViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindSample(sampleTransition: SampleItem.SampleTransition) {
            sampleName.setText(sampleTransition.name)
            containerView.setOnClickListener(Navigation.createNavigateOnClickListener(sampleTransition.navigationId))
        }

        companion object {
            fun create(viewGroup: ViewGroup) = SamplesViewHolder(viewGroup.inflate(R.layout.layout_sample_item_name_template))
        }
    }
}
