package pl.kamilbaziak.newweathercodingtask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.kamilbaziak.newweathercodingtask.R
import pl.kamilbaziak.newweathercodingtask.ui.weatherfragment.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    private val viewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.text_type_city_to_check_weather)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getCities(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}