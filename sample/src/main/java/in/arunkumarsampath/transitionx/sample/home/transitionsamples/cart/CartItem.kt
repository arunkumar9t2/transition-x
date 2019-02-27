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

import android.os.Parcelable
import android.support.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartItem(
        @param:DrawableRes val drawableRes: Int,
        val name: String,
        val status: String,
        val price: String
) : Parcelable {

    fun cartImageTransitionName() = "$status+$price+$name"
}