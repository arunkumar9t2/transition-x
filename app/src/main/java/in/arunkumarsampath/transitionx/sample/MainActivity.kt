package `in`.arunkumarsampath.transitionx.sample

import `in`.arunkumarsampath.transitionx.transition
import android.os.Bundle
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.view.View
import androidx.core.view.isGone
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            constraintLayout.transition {
                duration = 4000
                interpolator = LinearOutSlowInInterpolator()
            }
            helloWorldText.toggleVisibility()
        }
    }
}

private fun View.toggleVisibility() {
    isGone = !isGone
}
