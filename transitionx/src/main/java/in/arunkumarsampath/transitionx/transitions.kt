package `in`.arunkumarsampath.transitionx

import `in`.arunkumarsampath.transitionx.builders.TransitionSetBuilder
import `in`.arunkumarsampath.transitionx.builders.common.AutoTransitionBuilder
import android.support.transition.TransitionManager
import android.view.ViewGroup

inline fun ViewGroup.transition(cancel: Boolean = true, transitionsBuilder: TransitionSetBuilder.() -> Unit = {}) {
    if (cancel) {
        TransitionManager.endTransitions(this)
    }
    TransitionManager.beginDelayedTransition(
            this,
            TransitionSetBuilder().apply(transitionsBuilder).transition
    )
}

inline fun ViewGroup.autoTransition(cancel: Boolean, autoTransitionBuilder: AutoTransitionBuilder.() -> Unit = {}) {
    if (cancel) {
        TransitionManager.endTransitions(this)
    }
    TransitionManager.beginDelayedTransition(
            this,
            AutoTransitionBuilder().apply(autoTransitionBuilder).transition
    )
}