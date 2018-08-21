package `in`.arunkumarsampath.transitionx.sample

import `in`.arunkumarsampath.transitionx.sample.transition.changecolor.ChangeColor
import `in`.arunkumarsampath.transitionx.transition
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            constraintLayout.transition {
                customTransition<ChangeColor>()
                scaleRotate {
                    duration = 300
                }
                +helloWorldText

                onEnd { _ ->
                    constraintLayout.transition {
                        customTransition<ChangeColor>()
                        scaleRotate {
                            duration = 300
                        }
                        +helloWorldText
                    }

                    with(helloWorldText) {
                        scaleX = 1F
                        scaleY = 1f
                        rotationX += Random().nextInt(90)
                        rotationY += Random().nextInt(90)
                        background = ColorDrawable(Color.RED)
                    }
                }
            }
            with(helloWorldText) {
                scaleX = 1.5F
                scaleY = 1.5f
                background = ColorDrawable(Color.GREEN)
            }
        }
    }
}