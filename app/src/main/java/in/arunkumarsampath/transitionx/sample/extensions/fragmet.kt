@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.sample.extensions

import android.support.v4.app.Fragment

inline fun Fragment.dpToPx(dp: Double): Int {
    return requireContext().dpToPx(dp)
}