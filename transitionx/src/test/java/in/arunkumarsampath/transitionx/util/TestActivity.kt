package `in`.arunkumarsampath.transitionx.util

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class TestActivity : Activity() {
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create a view hierarchy programmatically
        textView = TextView(this).apply {
            id = View.generateViewId()
        }.also { setContentView(textView) }
    }
}