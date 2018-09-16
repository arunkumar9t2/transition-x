package `in`.arunkumarsampath.transitionx.builders.changetext

import `in`.arunkumarsampath.transitionx.builders.TransitionBuilder
import `in`.arunkumarsampath.transitionx.transition.changetext.ChangeText
import `in`.arunkumarsampath.transitionx.transition.changetext.ChangeTextBehavior

class ChangeTextBuilder : TransitionBuilder<ChangeText>(ChangeText()) {

    var changeTextBehavior: Int
        @ChangeTextBehavior get() = transition.changeBehavior
        set(@ChangeTextBehavior value) {
            transition.changeBehavior = value
        }
}