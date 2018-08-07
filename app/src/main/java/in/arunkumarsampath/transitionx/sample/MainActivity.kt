package `in`.arunkumarsampath.transitionx.sample

import `in`.arunkumarsampath.transitionx.sample.extensions.toggleGone
import `in`.arunkumarsampath.transitionx.transition
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            constraintLayout.transition {

            }
            helloWorldText.toggleGone()
        }
    }
}