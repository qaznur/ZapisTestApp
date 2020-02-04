package kz.nura.zapistestapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.databinding.ActivityMainBinding
import kz.nura.zapistestapp.network.Network
import kz.nura.zapistestapp.ui.fragments.HomeFragment
import kz.nura.zapistestapp.ui.fragments.ProfileFragment
import kz.nura.zapistestapp.ui.fragments.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.navView.setOnNavigationItemSelectedListener(navListener)

        val job = Job()
        val mainScope = CoroutineScope(job + Dispatchers.IO)

        mainScope.launch {
            val response = Network.apiService.getPopularSalons()
            Log.d("###", "response: $response")
        }

        loadFragment(HomeFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(HomeFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                loadFragment(SearchFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                loadFragment(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
