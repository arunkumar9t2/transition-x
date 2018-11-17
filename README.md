# Transition X

**Kotlin DSL for choreographing Android Transitions**

[![CircleCI](https://circleci.com/gh/arunkumar9t2/transition-x/tree/master.svg?style=svg)](https://circleci.com/gh/arunkumar9t2/transition-x/tree/master)[ ![Download](https://api.bintray.com/packages/arunkumar9t2/maven/transition-x/images/download.svg) ](https://bintray.com/arunkumar9t2/maven/transition-x/_latestVersion)[![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23335-blue.svg)](http://androidweekly.net/#335)

<img src="https://github.com/arunkumar9t2/transition-x/blob/master/sample/src/main/res/mipmap-xxxhdpi/ic_launcher.png" align="left"
width="200" hspace="10" vspace="10">

Transition X aims to simplify construction of `Transition` instances for using with `TransitionManager` available since API 19 and now available upto API 14 via AndroidX.

`TransitionManager` makes it easy to animate simple changes to layout without needing to explicitly calculate and specify `from` and `to` like `Animator` or `Animation` expects. When you call `TransitionManager.beginDelayedTransition(layout, transition)` before updating a layout, the framework automatically does a diff on before and after states and animates the difference.

`Transition X` is intended to simplify construction of these `Transition` instances to take full advantage of the framework and provide a clear, concise, type safe and extensible DSL.

I highly recommend reading the introduction blog post on [Medium.](https://medium.com/@arunkumar9t2/meet-transition-x-declarative-kotlin-dsl-for-choreographing-android-transitions-dff25ebb61f9)

# Download

- Add repository to your project level `build.gradle` file.

```groovy
allprojects {
    repositories {
        jcenter()
    }
}
```

- Add library dependency to module level `build.gradle` file.

```groovy
dependencies{
    implementation 'in.arunkumarsampath:transition-x:1.0.0'
}
```

# Getting Started

Currently `TransitionSet`s can be constructed from XML like shown below:

```xml
<transitionSet xmlns:android="http://schemas.android.com/apk/res/android" android:transitionOrdering="sequential">
	<fade android:fadingMode="fade_out" />
	<changeBounds />
	<fade android:fadingMode="fade_in" />
	<targets>
	   <target android:targetId="@+id/textView" />
	</targets>
 </transitionSet>
```

Programmaticaly, the above XML can be used like this:

```Kotlin
TransitionManager.beginDelayedTransition(constraintLayout, TransitionInflater
      .from(requireContext())
      .inflateTransition(R.transition.auto))
// Layout changes
```

With _Transition X_, the construction and usage can greatly simplified while being type safe and extensible.

```Kotlin
constraintLayout.prepareTransition {
	fadeOut()
	moveResize()
	fadeIn()
	+textView // Add text as target using '+' operator
}
// Layout changes
```

`prepareTransition` is an extension to `ViewGroup` and allows us to construct many layers of transition declaratively. This is a relatively simple example, read on for further sample snippets and their resulting animation.

# Samples

#### Snack bar transition

```Kotlin
snackbarConstraintLayout.prepareTransition {
  moveResize {  // Move resize uses ChangeBounds
    +fab
  }
  slide {
    +snackbarMessage  // We want the snackBar to slide from bottom, so we add it as target.
  }
  ease {
    decelerateEasing
  }
}
snackbarMessage.toggleGone()
```

![enter image description here](https://github.com/arunkumar9t2/transition-x/raw/master/art/snackbar_transition.gif)

#### Cascade Transition

It is possible to write normal logical code in the `prepareTransition` block. Here we add `moveResize` using loops and by adding a start delay based on position, we can emulate a cascade transition.

```Kotlin
constraintLayout.prepareTransition {
  arrayOf(textView,
  textView2,
  textView3,
  textView4
  ).forEachIndexed { position, view ->
	  moveResize {
	     +view
	    startDelay = ((position + 1) * 150).toLong()
	  }
 }
 moveResize { +fab }
 ease {
    decelerateEasing
 }
}
// Layout changes
(if (defaultState) constraint1 else constraint2).applyTo(constraintLayout)
```

![enter image description here](https://github.com/arunkumar9t2/transition-x/raw/master/art/cascade_transition.gif)

#### Custom transitions

Transition X can work with custom transitions. In case you have a custom transition and you want to work with Transition X's DSL, it is possible to do so by using
`customTransition<Type: Transition>{}` method. In the following example, `ChangeCardColor` is a custom transition that animates `cardBackgroundColor` property of `MaterialCardView`.

```kotlin
constraintLayout.prepareTransition {
  customTransition<ChangeCardColor> {
     +colorChangeCardView  // By adding targets we can contain on which views the said transition will run.
  }
  changeColor {
     +colorChangeTextView
  }
  duration = 1000
}
// Layout changes
colorChangeCardView.setCardBackgroundColor(color)
colorChangeTextView.setTextColor(calcForegroundWhiteOrBlack(color))
```

![enter image description here](https://github.com/arunkumar9t2/transition-x/raw/master/art/custom_transition.gif)

#### Arc motion

Here the `imageView`'s gravity is changed from `START | CENTER_VERTICAL` to `TOP | CENTER_HORIZONTAL`. By using a `pathMotion` it is possible to control the motion of the animation to follow material guidelines' arc motion.

```Kotlin
frameLayout.prepareTransition {
  moveResize {
    pathMotion = ArcMotion()
    +userIconView
  }
}
```

![enter image description here](https://github.com/arunkumar9t2/transition-x/raw/master/art/arc_motion.gif)

#### Advanced choreography

By using techniques above and coupling it with further customization via lifecycle listeners such as `onEnd` or `onPause` it is possible to have finer control over the entire transition process. In the example below, notice how different views are configured with different parameters for transition type, interpolation and ordering.

```Kotlin
constraintLayout.prepareTransition {
  auto {
     ease {
       standardEasing
     }
     exclude(metamorphosisDesc2)
  }
  transitionSet {
     fade()
     slide()
     ease {
       accelerateEasing
     }
     +metamorphosisDesc2
  }
  changeImage { add(*imageViews) }
  onEnd {
     constraintLayout.prepareTransition {
         moveResize()
         changeText {
             +collapseButton
             changeTextBehavior = ChangeText.CHANGE_BEHAVIOR_OUT_IN
         }
   }
   collapseButton.setText(R.string.collapse)
 }
 duration = 300
}
expandConstraint.applyTo(constraintLayout)
metamorphosisDesc2.isGone = false
metamorphosisDesc.isGone = true
```

![enter image description here](https://github.com/arunkumar9t2/transition-x/raw/master/art/metamorphosis.gif)

#### Shared Element Transitions

Library provides a `transitionSet{}` block which can be used to create an `Transition` instance. Then this instance can be used with `Fragment` with `Fragment.sharedElementEnterTransition` property.

```kotlin
fragment.sharedElementEnterTransition = transitionSet {
            transitionSet {
                changeImage()
                moveResize()
                changeClipBounds()
                scaleRotate()
                ease {
                    standardEasing
                }
                duration = 375
                +cartItem.cartImageTransitionName()
            }
            transitionSet {
                ease {
                    standardEasing
                }
                moveResize()
                scaleRotate()
                add(cartItem.name, cartItem.price)
                duration = 375
            }
        }
```

[Example](https://github.com/arunkumar9t2/transition-x/tree/master/sample/src/main/java/in/arunkumarsampath/transitionx/sample/home/transitionsamples/cart)

# Tasks

- [x] Initial release of Kotlin DSL
- [x] Provide samples for Shared Element Transitions
- [x] Package common transition within the library module
- [ ] Add wiki with best practices and gotchas.

# Contributions

Contributions are welcome! I would greatly appreciate creating an issue to discuss major changes before submitting a PR directly.
How can you help:

- Improving test coverage.
- Finding the DSL not sufficient for your case? Create an issue so we can discuss.
- Adding more animation samples to the sample app.

# License

    Copyright 2018, Arunkumar.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
