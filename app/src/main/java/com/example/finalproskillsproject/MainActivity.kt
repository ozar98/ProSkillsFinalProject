package com.example.finalproskillsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.finalproskillsproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragmentChangeListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    /**---------------------------------------------------------------------------------------------
     *  private
     * -------------------------------------------------------------------------------------------*/

    private fun setFragmentChangeListener(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView : BottomNavigationView = binding.bottomNavigationID

        binding.bottomNavigationID.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                    R.id.fragmentMainPage -> {navView.visibility=View.VISIBLE
                }
                    R.id.fragmentsHistory->{navView.visibility=View.VISIBLE
                }
                    R.id.fragmentSettings->{
                    navView.visibility=View.VISIBLE
                }
                else -> {
                    navView.visibility = View.GONE
                    this.supportActionBar?.hide()
                }
            }


        }

    }

}