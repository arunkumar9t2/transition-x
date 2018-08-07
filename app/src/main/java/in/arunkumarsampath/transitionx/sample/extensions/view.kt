@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.sample.extensions

import android.view.View
import androidx.core.view.isGone


inline fun View.toggleGone() {
    isGone = !isGone
}
