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

package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples.cart

import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.extensions.inflate
import `in`.arunkumarsampath.transitionx.sample.util.glide.GlideApp
import android.support.v4.view.ViewCompat.setTransitionName
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.NO_POSITION
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_cart_list_item_template.*
import java.util.*
import kotlin.collections.ArrayList

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartItemViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            itemType: Int
    ) = CartItemViewHolder.create(parent)

    override fun getItemCount() = CART_ITEMS.size

    override fun onBindViewHolder(
            holder: CartItemViewHolder,
            position: Int
    ) = holder.bindCartItem(CART_ITEMS[position])

    class CartItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            with(containerView) {
                setOnClickListener {
                    if (adapterPosition != NO_POSITION) {
                        navigateToCartDetail()
                    }
                }
            }
        }

        private fun View.navigateToCartDetail() {
            val cartItem = CART_ITEMS[adapterPosition]
            setTransitionName(cartContentPreview, cartItem.cartImageTransitionName())
            setTransitionName(cartItemName, cartItem.name)
            setTransitionName(cartPrice, cartItem.price)
            findNavController().navigate(
                    R.id.cartDetailFragment,
                    CartDetailFragmentArgs
                            .Builder(cartItem)
                            .build()
                            .toBundle(),
                    null,
                    FragmentNavigatorExtras(
                            cartItemName to cartItem.name,
                            cartPrice to cartItem.price,
                            cartContentPreview to "${cartItem.status}+${cartItem.price}+${cartItem.name}"
                    )
            )
        }

        fun bindCartItem(cartItem: CartItem) {
            GlideApp.with(containerView).load(cartItem.drawableRes).into(cartContentPreview)
            cartItemName.text = cartItem.name
            cartPrice.text = cartItem.price
            cartItemStatus.text = cartItem.status
        }

        companion object {
            fun create(parent: ViewGroup) = CartItemViewHolder(parent.inflate(R.layout.layout_cart_list_item_template))
        }
    }

    companion object {
        private val random = Random()

        private val statuses = listOf(
                "In Stock",
                "Out of Stock",
                "Only ${random.nextInt(5)} in stock"
        )
        private val items = listOf(
                "Pencil" to R.drawable.ic_pencil,
                "Tooth brush" to R.drawable.ic_tooth_brush,
                "Coffee Mug" to R.drawable.ic_cofee_mug
        )

        private val CART_ITEMS: List<CartItem> by lazy {
            ArrayList<CartItem>().also { list ->
                repeat(10) {
                    val item = items[random.nextInt(items.size)]
                    list += CartItem(
                            item.second,
                            item.first,
                            statuses[random.nextInt(statuses.size)],
                            "$${random.nextInt(50)}.${random.nextInt(99)}"
                    )
                }
            }
        }
    }
}