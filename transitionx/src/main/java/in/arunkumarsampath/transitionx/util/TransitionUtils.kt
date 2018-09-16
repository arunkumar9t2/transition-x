package `in`.arunkumarsampath.transitionx.util

import android.animation.Animator
import android.animation.AnimatorSet


object TransitionUtils {

    fun mergeAnimators(animator1: Animator?, animator2: Animator?): Animator? {
        return when {
            animator1 == null -> animator2
            animator2 == null -> animator1
            else -> {
                AnimatorSet().apply {
                    playTogether(animator1, animator2)
                }
            }
        }
    }
}