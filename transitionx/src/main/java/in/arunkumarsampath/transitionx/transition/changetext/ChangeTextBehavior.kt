package `in`.arunkumarsampath.transitionx.transition.changetext

import android.support.annotation.IntDef

@IntDef(value = [
    ChangeText.CHANGE_BEHAVIOR_IN,
    ChangeText.CHANGE_BEHAVIOR_OUT,
    ChangeText.CHANGE_BEHAVIOR_OUT_IN,
    ChangeText.CHANGE_BEHAVIOR_KEEP
])
@Retention(AnnotationRetention.SOURCE)
annotation class ChangeTextBehavior