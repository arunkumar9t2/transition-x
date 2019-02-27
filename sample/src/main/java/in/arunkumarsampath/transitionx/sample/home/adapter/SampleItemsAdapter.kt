/*
 *
 * Copyright 2019 Arunkumar
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
 *
 */

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
            sample(R.id.changeImageTransformFragment, R.string.change_image_transition),
            sample(R.id.changeColorFragment, R.string.change_color_transition),
            sample(R.id.changeTextTransitionFragment, R.string.change_text_transition),
            header(R.string.advanced_transitions),
            sample(R.id.materialCardTransformationFragment, R.string.material_card_transformation),
            sample(R.id.animatedBottomNavigationFragment, R.string.animated_bottom_navigation_fragment),
            header(R.string.shared_element_transition),
            sample(R.id.cartListFragment, R.string.cart_list)
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