package `in`.arunkumarsampath.transitionx.sample

import `in`.arunkumarsampath.transitionx.autoTransition
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
            constraintLayout.autoTransition {
                scaleRotate()
            }
            helloWorldText.scaleX = 1.5F
            helloWorldText.scaleY = 1.5f
        }
    }
}