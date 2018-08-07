package `in`.arunkumarsampath.transitionx.builders.slide

import `in`.arunkumarsampath.transitionx.builders.TransitionBuilder
import android.support.transition.Slide

class SlideBuilder : TransitionBuilder<Slide>(Slide()) {

    var slideEdge: Int
        set(@GravityFlag value) {
            transition.slideEdge = value
        }
        @GravityFlag get() = transition.slideEdge
}