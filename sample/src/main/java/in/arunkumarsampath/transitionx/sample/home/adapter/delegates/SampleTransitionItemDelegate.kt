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

package `in`.arunkumarsampath.transitionx.sample.home.adapter.delegates

import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.extensions.inflate
import `in`.arunkumarsampath.transitionx.sample.home.adapter.SampleItem
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.navOptions
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
            with(containerView) {
                setOnClickListener {
                    findNavController().navigate(
                            sampleTransitionItem.navigationId,
                            null,
                            DEFAULT_NAV_OPTIONS
                    )
                }
            }
        }

        companion object {
            val DEFAULT_NAV_OPTIONS = navOptions {
                anim {
                    enter = R.anim.nav_default_enter_anim
                    exit = R.anim.nav_default_exit_anim
                    popEnter = R.anim.nav_default_pop_enter_anim
                    popExit = R.anim.nav_default_pop_exit_anim
                }
            }

            fun create(viewGroup: ViewGroup) = SamplesViewHolder(viewGroup.inflate(R.layout.layout_sample_item_name_template))
        }
    }
}
