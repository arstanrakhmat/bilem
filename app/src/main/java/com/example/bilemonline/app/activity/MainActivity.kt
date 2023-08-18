package com.example.bilemonline.app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.bilemonline.R
import com.example.bilemonline.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomFragmentList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.bottomNavigationView.setupWithNavController(
//            findNavController(R.id.fragmentContainer)
//        )
        val bottomNavView: BottomNavigationView = binding.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavView.setupWithNavController(navController)

        bottomFragmentList = listOf(
            R.id.mainPageFragment,
            R.id.searchFragment,
            R.id.myLearningsFragment,
            R.id.profileFragment
        )

        navController.addOnDestinationChangedListener {_, nd: NavDestination, _ ->
            if (nd.id in bottomFragmentList) {
                binding.bottomNavigationView.visibility = View.VISIBLE
            } else {
                binding.bottomNavigationView.visibility = View.GONE
            }
        }

    }

    private fun setupNav() {
        val navCtrl = findNavController(R.id.fragmentContainer)
        binding.bottomNavigationView.setupWithNavController(navCtrl)

        navCtrl.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainPageFragment, R.id.searchFragment, R.id.myLearningsFragment, R.id.profileFragment ->
                    showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    private fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

}