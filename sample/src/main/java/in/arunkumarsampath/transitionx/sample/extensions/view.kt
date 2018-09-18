@file:Suppress("NOTHING_TO_INLINE")

package `in`.arunkumarsampath.transitionx.sample.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone

inline fun View.toggleGone() {
    isGone = !isGone
}

inline fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.removeCallbacks(vararg runnables: () -> Unit) = runnables.forEach { removeCallbacks(it) }
