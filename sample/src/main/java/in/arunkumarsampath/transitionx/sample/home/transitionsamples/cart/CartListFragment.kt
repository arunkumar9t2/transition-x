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

package `in`.arunkumarsampath.transitionx.sample.home.transitionsamples.cart

import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.sample.home.transitionsamples.BaseSampleFragment
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.layout_content_cart_list.*
import timber.log.Timber

class CartListFragment : BaseSampleFragment() {

    override val contentLayoutResource = R.layout.layout_content_cart_list
    override val titleRes = R.string.cart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCartList()
    }

    private fun setupCartList() {
        cartList.adapter = CartAdapter()
    }


    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView")
    }
}