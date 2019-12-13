package testapp.ru.testapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import testapp.ru.testapplication.R

interface ISetTitle{
    fun setTitle(title: String)
}

class MainActivity : AppCompatActivity(R.layout.activity_main),
    NavController.OnDestinationChangedListener, ISetTitle {

    private val host: NavHostFragment by lazy {
        supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        supportActionBar?.setDisplayHomeAsUpEnabled( destination.id != R.id.moviesListFragment)
        supportActionBar?.title = destination.label.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navGraph = host.navController.navInflater.inflate(R.navigation.app_navigation)
        host.findNavController().graph = navGraph
        host.navController.addOnDestinationChangedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(this, R.id.nav_host_fragment).navigateUp()
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }
}
