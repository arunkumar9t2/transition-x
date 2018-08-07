package `in`.arunkumarsampath.transitionx.builders.slide

import android.annotation.SuppressLint
import android.support.annotation.IntDef
import android.view.Gravity
import kotlin.annotation.AnnotationRetention.SOURCE

@SuppressLint("RtlHardcoded")
@IntDef(
        Gravity.LEFT,
        Gravity.TOP,
        Gravity.RIGHT,
        Gravity.BOTTOM,
        Gravity.START,
        Gravity.END
)
@Retention(SOURCE)
annotation class GravityFlag