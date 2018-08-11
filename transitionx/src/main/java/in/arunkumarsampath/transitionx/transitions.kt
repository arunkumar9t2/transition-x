package `in`.arunkumarsampath.transitionx

import `in`.arunkumarsampath.transitionx.builders.set.AutoTransitionBuilder
import `in`.arunkumarsampath.transitionx.builders.set.DefaultTransitionSetBuilder
import android.support.transition.TransitionManager
import android.view.ViewGroup

inline fun ViewGroup.transition(cancel: Boolean = false, transitionsBuilder: DefaultTransitionSetBuilder.() -> Unit = {}) {
    if (cancel) {
        TransitionManager.endTransitions(this)
    }
    TransitionManager.beginDelayedTransition(
            this,
            DefaultTransitionSetBuilder().apply(transitionsBuilder).transition
    )
}

inline fun ViewGroup.autoTransition(cancel: Boolean = false, autoTransitionBuilder: AutoTransitionBuilder.() -> Unit = {}) {
    if (cancel) {
        TransitionManager.endTransitions(this)
    }
    TransitionManager.beginDelayedTransition(
            this,
            AutoTransitionBuilder().apply(autoTransitionBuilder).transition
    )
}