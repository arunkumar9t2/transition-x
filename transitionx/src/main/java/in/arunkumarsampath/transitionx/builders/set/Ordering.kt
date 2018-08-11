package `in`.arunkumarsampath.transitionx.builders.set

import android.support.annotation.IntDef
import android.support.transition.TransitionSet.ORDERING_SEQUENTIAL
import android.support.transition.TransitionSet.ORDERING_TOGETHER

@IntDef(value = [ORDERING_SEQUENTIAL, ORDERING_TOGETHER])
@Retention(AnnotationRetention.SOURCE)
annotation class Ordering