package `in`.arunkumarsampath.transitionx.builders.fade

import `in`.arunkumarsampath.transitionx.builders.TransitionBuilder
import android.support.transition.Fade
import android.support.transition.Fade.IN
import android.support.transition.Fade.OUT

class FadeBuilder(@FadeMode fadingMode: Int = IN or OUT) : TransitionBuilder<Fade>(Fade(fadingMode))