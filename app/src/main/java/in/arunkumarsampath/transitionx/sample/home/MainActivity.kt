package `in`.arunkumarsampath.transitionx.sample.home

import `in`.arunkumarsampath.transitionx.sample.R
import `in`.arunkumarsampath.transitionx.prepareTransition
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationMenu()
    }

    private fun setupBottomNavigationMenu() {
        NavigationUI.setupWithNavController(bottomNavigation, navController)
        navController.addOnNavigatedListener { _, destination ->
            rootCoordinatorLayout.prepareTransition {
                +bottomNavigation
                slide()
            }
            bottomNavigation.isGone = destination.id != R.id.homeFragment
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}