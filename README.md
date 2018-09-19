# Transition X
**Kotlin DSL for choreographing Android Transitions**  [![CircleCI](https://circleci.com/gh/arunkumar9t2/transition-x/tree/master.svg?style=svg)](https://circleci.com/gh/arunkumar9t2/transition-x/tree/master)[ ![Download](https://api.bintray.com/packages/arunkumar9t2/maven/transition-x/images/download.svg?version=1.0.0-beta4) ](https://bintray.com/arunkumar9t2/maven/transition-x/1.0.0-beta4/link)

<img src="https://github.com/arunkumar9t2/transition-x/blob/master/sample/src/main/res/mipmap-xxxhdpi/ic_launcher.png" align="left"
width="200" hspace="10" vspace="10">

Transition X aims to simplify construction of `Transition` instances for using with `TransitionManager` available since API 19 and now available upto API 14 via AndroidX.

`TransitionManager` makes it easy to animate simple changes to layout without needing to explicitly calculate and specify `from` and `to` like `Animator` or `Animation` expects. When you call `TransitionManager.beginDelayedTransition(layout, transition)` before you change something, the framework automatically does a diff on before and after `Scene`s and animates the difference.

`Transition X` is intended to simplify construction of these `Transition`  instances to take full advantage of the framework and provide a clear, concise and extensible DSL.

## Example
Scale and animate background color changes of `helloWorldText` View using transition X. Transition X provides methods for inbuilt transitions in Android X like 
`ChangeTransform`, `ChangeClipBounds`, `ChangeImageTransform` etc. It is possible to use your own transitions by using `customTransition()` API.
```kotlin
constraintLayout.transition {  // transition{} is an extension to ViewGroup
  customTransition<ChangeColor>()  // ChangeColor is a custom transition
  scaleRotate {  // scaleRotate() internally uses ChangeTransform
	  duration = 300  // Customize individual transitions
  }  
  +helloWorldText  // Add helloWorldText as the target using `+` operator
} 
// Layout changes 
with(helloWorldText) {  
  scaleX = 1.5F  
  scaleY = 1.5f  
  background = ColorDrawable(Color.GREEN)  
}
```

More details and samples will be added soon.

## Contributions

Until a stable release is out, please create an issue to discuss your ideas before submitting a PR. 
