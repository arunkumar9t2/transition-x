package `in`.arunkumarsampath.transitionx

import `in`.arunkumarsampath.transitionx.builders.TransitionBuilder
import android.support.transition.TransitionManager
import android.support.transition.TransitionSet
import android.view.ViewGroup

fun ViewGroup.transition(
        cancel: Boolean = true,
        transitionsBuilder: TransitionBuilder<TransitionSet>.() -> Unit
) {
    if (cancel) {
        TransitionManager.endTransitions(this)
    }
    val transitionDelegate = TransitionBuilder(TransitionSet())
    transitionsBuilder(transitionDelegate)
    TransitionManager.beginDelayedTransition(this, transitionDelegate.transition)
}