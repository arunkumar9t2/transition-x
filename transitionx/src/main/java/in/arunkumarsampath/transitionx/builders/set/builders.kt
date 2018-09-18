package `in`.arunkumarsampath.transitionx.builders.set

import android.support.transition.AutoTransition
import android.support.transition.TransitionSet


class DefaultTransitionSetBuilder : TransitionSetBuilder<TransitionSet>(TransitionSet())

class AutoTransitionBuilder : TransitionSetBuilder<TransitionSet>(AutoTransition())