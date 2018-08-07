package `in`.arunkumarsampath.transitionx.builders.fade

import android.support.annotation.IntDef
import android.support.transition.Fade.IN
import android.support.transition.Fade.OUT

@IntDef(flag = true, value = [IN, OUT, IN or OUT])
@Retention(AnnotationRetention.SOURCE)
annotation class FadeMode