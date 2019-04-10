
# Transition X

<p align="center">
<img src="https://github.com/arunkumar9t2/transition-x/raw/master/sample/src/main/res/mipmap-xxxhdpi/ic_launcher.png" 
width="210" hspace="10" vspace="10">
</p>

**Kotlin DSL for choreographing Android Transitions**

[![CircleCI](https://circleci.com/gh/arunkumar9t2/transition-x/tree/master.svg?style=svg)](https://circleci.com/gh/arunkumar9t2/transition-x/tree/master)[ ![Download](https://api.bintray.com/packages/arunkumar9t2/maven/transition-x/images/download.svg) ](https://bintray.com/arunkumar9t2/maven/transition-x/_latestVersion) [![Documentation](https://img.shields.io/badge/documentation-%20-brightgreen.svg)](https://arunkumar9t2.github.io/transition-x/transitionx/index.html) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23335-blue.svg)](http://androidweekly.net/#335) 

`TransitionManager` makes it easy to animate simple changes to layout without needing to explicitly calculate and specify `from` and `to` like `Animator` or `Animation` expects. When you call `TransitionManager.beginDelayedTransition(layout, transition)` before updating a layout, the framework automatically does a diff on before and after states and animates the difference.

`Transition X` is intended to simplify construction of these `Transition` instances to take full advantage of the framework and provide a clear, concise, type safe and extensible DSL using Kotlin language features.

I highly recommend reading the introduction blog post on [my blog.](https://arunkumar.dev/transtition-x-declarative-kotlin-dsl-for-choreographing-android-transitions/)

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
    implementation 'in.arunkumarsampath:transition-x:1.0.1'
}
```

# Introduction

![enter image description here](https://raw.githubusercontent.com/arunkumar9t2/transition-x/develop/art/transition%20x%20intro.png)

As shown above, instead of creating XML files and later inflating them using `TransitionInflator`, it is possible to create `Transition` instances directly using `tranistionSet{}` block provided by the DSL.

With _**Transition X**_, the construction and usage can be greatly simplified with a `prepareTransition` extension added to `ViewGroup`.

*For example:*

```Kotlin
constraintLayout.prepareTransition {
  fadeOut {
      startDelay = 100
  }
  moveResize {
    pathMotion = ArcMotion()
  }
  fadeIn()
  +textView // Add textView as target using '+' operator
  exclude<RecyclerView>() // Exclude all recyclerViews
  ease {
    standardEasing // Applies FastOutSlowInInterpolator
  }
}
// Performing layout changes here will be animated just like
// calling TransitionManager.beginDelayedTransition()
```
All blocks are type-safe and has IDE auto complete support thanks to Kotlin.

# Getting Started
## Writing your first transition
TransitionSet's can be built programmatically like this.
```kotlin
TransitionSet().apply {
  addTransition(ChangeBounds().apply {
    startDelay = 100  
    setPathMotion(ArcMotion())  
  })  
}
```
The Transition X equivalent would be:
```kotlin
transitionSet {   
  moveResize {   
    startDelay = 100  
    pathMotion = ArcMotion()  
  }  
}
```
Some of the transition names are **opinionated** to better express their intent and promote clear code. Here `ChangeBounds` transition usually animates a `View`'s height, width, or location on screen hence the name `moveResize` to better convey what it does.
## Working with custom transitions
In case you have a custom transition class and want to use with the DSL, it is easy to do so. 

 - If your transition has a `public no arg` constructor then the transition can be added using `customTransition<Type: Transition>{}` method. Below example shows usage of `ChangeCardColor` which animates a `CardView`s cardBackground property.

```kotlin
constraintLayout.prepareTransition {  
  customTransition<ChangeCardColor> {  
    +colorChangeCardView  
  } 
}
```

 - If your transition does not have `public no arg` constructor then, you can instantiate the transition and then use `customTransition(transition) {}` instead to add the transition.
 - **Accessing custom properties** : In addition to the common properties like `startDelay`, `interpolator`, etc, if your transition has custom properties then `customProperties {}` block can be used. 

```kotlin
constraintLayout.prepareTransition {
  customTransition<ChangeCardColor> {
    +colorChangeCardView  
    customProperties {   
	  myProperty = "hi"  
	}  
  }
}
```
## Adding, removing and excluding targets
The DSL provides simplified syntax to deal with targets by talking to `Transition`'s add/exclude/remove API.

 - Use `+` operator or `add()` to add targets of type `String (Transition Name)` or `View` or `Resource Id`.
```kotlin
transitionSet {  
  +"TransitionName"  
  +userIconView
  add(userIconView)  
}
```
 - Use `-` operator or `remove()` to remove targets of type `String (Transition Name)` or `View` or `Resource Id`.
```kotlin
transitionSet {  
  -"TransitionName"  
  -userIconView
  remove(userIconView)  
}
```
 - `exclude` and `excludeChildren` methods are provided for excluding targets which can be useful in advanced transitions. It can be used on `Views`, `Resource Ids` or `Type`
```kotlin
transitionSet {  
  exclude<RecyclerView>()  
  exclude(R.id.accentBackground)
  excludeChildren(constraintLayout)  
}
```
## Interpolators
 - **Interpolators** can be directly added using `interpolator` property.
```kotlin
transitionSet {  
  moveResize()  
  slide()  
  interpolator = FastOutLinearInInterpolator()  
}
```

 - **Easing** - DSL provides a dedicated `ease` block to add interpolators recommended by [material design spec](https://material.io/design/motion/speed.html#easing).
		 
	*standardEasing*: Recommended for views that move within visible area of the layout. `FastOutSlowInInterpolator`
		 
	*decelerateEasing*: Recommended for views that appear/enter outside visible bounds of the layout. `LinearOutSlowInInterpolator`
		 
	*accelerateEasing*: Recommended for Views that exit visible bounds of the layout. `FastOutLinearInInterpolator`

```kotlin
transitionSet {  
  moveResize()  
  ease {  
    decelerateEasing  
  }  
}
```
## Nesting transitions
Often, for fined grained transitions it it necessary to add different transition sets for different targets. It is simple to nest multiple transition sets just by using `transitionSet {}` recursively.
```kotlin
transitionSet {  
  auto {   
    +"View 1"  
  }  
  transitionSet {   
    moveResize()  
    slide()  
    +"View 2"  
  }  
  transitionSet {   
    sequentially()  
    fadeOut()  
    moveResize()  
    fadeIn()  
  }  
}
```
## Additional transitions
The library packages additional transitions not present in the support library and the plan is to add more commonly used transitions to provide a full package. Currently the following transitions are packaged.
 - ***ChangeText***: Animates changes to a `TextView.text` property.
 - ***ChangeColor***: Animates changes to `View.background` if it is a `ColorDrawable` or changes to `TextView.textColor` if the target is a `TextView`.

# Samples

<table>
<tbody>
<tr>
<th width="20%">Sample</th>
<th width="30%">DSL</th>
<th width="50%">Demo</th>
</tr>
<tr>
<td><b>Snackbar animation</b></td>
<td>Snackbar is anchored below FAB. <code>moveResize</code> is used on on FAB since its position changes. <code>Slide</code> is used on <code>Snackbar</code> since it's visibility changes.
<pre>constraintLayout.prepareTransition {
  moveResize { 
    +fab
  }
  slide {
    +snackbarMessage
  }
  ease {
    decelerateEasing
  }
}
snackbarMessage.toggleGone()
</pre>
</td>
<td><img src="https://github.com/arunkumar9t2/transition-x/raw/master/art/snackbar_transition.gif" alt="" width="470" /></td>
</tr>
<tr>
<td><b>Cascade animation</b></td>
<td>It is possible to write normal logical code in the <code>prepareTransition</code> block. Here we add <code>moveResize</code> using loops and by adding a start delay based on position, we can emulate a cascade transition.
<pre>constraintLayout.prepareTransition {
  texts.forEachIndexed { position, view -&gt;
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
(if (defaultState) constraint1 else constraint2)
.applyTo(constraintLayout)
</pre>
</td>
<td><img src="https://github.com/arunkumar9t2/transition-x/raw/master/art/cascade_transition.gif" alt="" width="470" /></td>
</tr>
<tr></tr>
<tr>
<td><b>Custom Transition</b></td>
<td>In the following example, <code>ChangeCardColor</code> is a custom transition that animates <code>cardBackgroundColor</code> property of <code>MaterialCardView</code> .
<pre>constraintLayout.prepareTransition {
  customTransition&lt;ChangeCardColor&gt; {
     +cardView
  }
  changeColor {
     +textView
  }
  duration = 1000
}
// Layout changes
cardView.setCardBackgroundColor(color)
textView.setTextColor(calcForegroundWhiteOrBlack(color))
</pre>
</td>
<td><img src="https://github.com/arunkumar9t2/transition-x/raw/master/art/custom_transition.gif" alt="" width="470" /></td>
</tr>
<tr>
<td><b>Arc motion</b></td>
<td>Here the <code>imageView</code>'s gravity is changed from <code>START | CENTER_VERTICAL</code> to <code>TOP | CENTER_HORIZONTAL</code>. By using a pathMotion it is possible to control the motion of the animation to follow material guidelines' arc motion.
<pre>frameLayout.prepareTransition {
  moveResize {
    pathMotion = ArcMotion()
    +userIconView
  }
}
</pre>
</td>
<td><img src="https://github.com/arunkumar9t2/transition-x/raw/master/art/arc_motion.gif" alt="" width="470" /></td>
</tr>
<tr>
<td><b>Advanced choreography</b></td>
<td>By using techniques above and coupling it with further customization via lifecycle listeners such as <code>onEnd</code> or <code>onPause</code> it is possible to have finer control over the entire transition process. In the example below, notice how different views are configured with different parameters for transition type, interpolation and ordering.
<pre>constraintLayout.prepareTransition {
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
             changeTextBehavior <br />              = ChangeText.CHANGE_BEHAVIOR_OUT_IN
         }
   }
   collapseButton.setText(R.string.collapse)
 }
 duration = 300
}
expandConstraint.applyTo(constraintLayout)
metamorphosisDesc2.isGone = false
metamorphosisDesc.isGone = true
</pre>
</td>
<td><img src="https://github.com/arunkumar9t2/transition-x/raw/master/art/metamorphosis.gif" alt="" width="470" /></td>
</tr>
<tr>
<td><b>Shared element transition</b></td>
<td>Transition instances created by the DSL can be directly used with <code> activity.window.sharedElementEnterTransition</code> or <code>fragment.sharedElementEnterTransition.</code>
 <pre>
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
  </pre>
  </td>
<td>
<p>Demo - WIP.</p>
<p><a href="https://github.com/arunkumar9t2/transition-x/tree/master/sample/src/main/java/in/arunkumarsampath/transitionx/sample/home/transitionsamples/cart">Example</a></p>
</td>
</tr>
<tr>
<td><b>Animated Bottom Navigation</b></td>
<td>Bottom navigation animation implmentend using custom choreography instead of relying on <code>AutoTransition</code>. The implementation uses <code>ConstraintLayout</code> to define the layouts and then simply show/hides the labels and adds tint to the icons. TransitionManager does the rest.


    transitionSet {
      fadeOut()

      moveResize {
        startDelay = 50
        ease {
          standardEasing
        }
      }

     fadeIn {
       startDelay = 50
     }

     changeColor {
       navItems.map { it.text }.forEach { text -> add(text) }
       +constraintLayout
     }

      customTransition<ChangeImageTint> {
         navItems.map { it.icon }.forEach { icon -> add(icon) }
      }
    }
	
</td>
<td><img src="https://github.com/arunkumar9t2/transition-x/raw/master/art/animated_bottom_navigation.gif" alt="" width="470" /></td>
</tr>
</tr>
</tbody>
</table>

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

    Copyright 2019, Arunkumar.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
