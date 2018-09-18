package `in`.arunkumarsampath.transitionx.sample.home.adapter

import android.support.annotation.IdRes
import android.support.annotation.StringRes

sealed class SampleItem {

    data class SampleTransitionItem(
            @param:IdRes val navigationId: Int,
            @param:StringRes val name: Int
    ) : SampleItem()

    data class HeaderItem(@param:StringRes val stringRes: Int) : SampleItem()
}

