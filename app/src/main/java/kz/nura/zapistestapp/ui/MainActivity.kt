package kz.nura.zapistestapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.nura.zapistestapp.R
import kz.nura.zapistestapp.databinding.ActivityMainBinding
import kz.nura.zapistestapp.ui.fragments.HomeFragment
import kz.nura.zapistestapp.ui.fragments.ProfileFragment
import kz.nura.zapistestapp.ui.fragments.SearchFragment
import kz.nura.zapistestapp.viewmodels.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var viewModel: HomeViewModel

    private val fm = supportFragmentManager
    private lateinit var homeFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var profileFragment: Fragment
    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.navView.setOnNavigationItemSelectedListener(navListener)

        initFragments()
    }

    private fun initFragments() {
        if (fm.findFragmentByTag("home") != null) {
            homeFragment = fm.findFragmentByTag("home") as HomeFragment
        } else {
            homeFragment = HomeFragment()
            fm.beginTransaction()
                .add(R.id.fragment_container, homeFragment, "home")
                .commit()
        }

        if (fm.findFragmentByTag("search") != null) {
            searchFragment = fm.findFragmentByTag("search") as SearchFragment
        } else {
            searchFragment = SearchFragment()
            fm.beginTransaction()
                .add(R.id.fragment_container, searchFragment, "search")
                .hide(searchFragment)
                .commit()
        }

        if (fm.findFragmentByTag("profile") != null) {
            profileFragment = fm.findFragmentByTag("profile") as ProfileFragment
        } else {
            profileFragment = ProfileFragment()
            fm.beginTransaction()
                .add(R.id.fragment_container, profileFragment, "profile")
                .hide(profileFragment)
                .commit()
        }

        activeFragment = homeFragment
    }

    private fun loadFragment(fragment: Fragment) {
        fm.beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()
        activeFragment = fragment
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(homeFragment)
                title = getString(R.string.home_fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                loadFragment(searchFragment)
                title = getString(R.string.search_fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                loadFragment(profileFragment)
                title = getString(R.string.profile_fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
