package com.mobile.feedfinder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mobile.feedfinder.R
import com.mobile.feedfinder.databinding.ActivityMainBinding
import com.mobile.feedfinder.service.FirebaseImplementor

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(
            this, binding.mainDrawerLayout, binding.toolbar,
            R.string.nav_open, R.string.nav_close
        )

        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        binding.mainDrawerLayout.addDrawerListener(toggle)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.mainBottomNavigation.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.findAnimalsFragment,R.id.shareAnimalLocationFragment),
            binding.mainDrawerLayout
        )

        binding.navigationView.setupWithNavController(navController)

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.logOutItem -> {
                    binding.mainDrawerLayout.closeDrawers()
                    FirebaseImplementor().logOut(binding.root.context)
                    true
                }
                else -> {
                    binding.mainDrawerLayout.closeDrawers()
                    it.onNavDestinationSelected(navController) ||
                            super.onOptionsItemSelected(it)
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }

}