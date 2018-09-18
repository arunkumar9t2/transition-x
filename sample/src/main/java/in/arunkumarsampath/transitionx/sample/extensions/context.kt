@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.sample.extensions

import android.content.Context

inline fun Context.dpToPx(dp: Double): Int {
    val displayMetrics = resources.displayMetrics
    return (dp * displayMetrics.density + 0.5).toInt()
}