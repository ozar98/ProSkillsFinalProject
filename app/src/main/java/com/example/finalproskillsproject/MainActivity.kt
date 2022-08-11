package com.example.finalproskillsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.finalproskillsproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var fragmentLogin: FragmentLogin= FragmentLogin()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //setFragmentChangeListener()

    }

    private fun setFragmentChangeListener(){
        val navController = findNavController(R.id.main_fragment)
        findViewById<BottomNavigationView>(R.id.bottomNavigationID).setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragmentLogin -> hideBottomNav()
                R.id.fragmentRegistration -> hideBottomNav()
                else -> showBottomNav()
            }
        }

    }
    private fun showBottomNav() {
        binding.bottomNavigationID.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.bottomNavigationID.visibility = View.GONE

    }
}