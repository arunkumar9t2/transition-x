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
import `in`.arunkumarsampath.transitionx.sample.util.glide.GlideApp
import `in`.arunkumarsampath.transitionx.transitionSet
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_cart_detail_content.*

class CartDetailFragment : Fragment() {

    private val cartItem by lazy { CartDetailFragmentArgs.fromBundle(arguments).cartItem }

    private fun applyTransition() {
        sharedElementEnterTransition = transitionSet {
            transitionSet {
                changeImage()
                moveResize()
                changeClipBounds()
                scaleRotate()
                standardEasing()
                duration = 375
                +cartItem.cartImageTransitionName()
            }
            transitionSet {
                standardEasing()
                moveResize()
                scaleRotate()
                add(cartItem.name, cartItem.price)
                duration = 375
            }
        }
        enterTransition = transitionSet {
            slide()
            fadeIn()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_cart_detail_content, container, false)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTransition()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        setupViews()
    }

    private fun setupViews() {
        ViewCompat.setTransitionName(
                cartContentPreview,
                cartItem.cartImageTransitionName()
        )

        with(cartItemName) {
            text = cartItem.name
            ViewCompat.setTransitionName(this, cartItem.name)
        }

        with(cartItemPrice) {
            text = cartItem.price
            ViewCompat.setTransitionName(this, cartItem.price)
        }
        loadCartImage()
    }

    private fun loadCartImage() {
        GlideApp.with(this)
                .load(cartItem.drawableRes)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                }).into(cartContentPreview)
    }
}