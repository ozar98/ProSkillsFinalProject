package com.example.finalproskillsproject

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.finalproskillsproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragmentChangeListener()
        language =
            getSharedPreferences(SHARED_PREFERENCES_KEY, MODE_PRIVATE).getString(LANGUAGE_KEY, "ru")

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /**---------------------------------------------------------------------------------------------
     *  private
     * -------------------------------------------------------------------------------------------*/

    private fun setFragmentChangeListener() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView: BottomNavigationView = binding.bottomNavigationID

        //binding.bottomNavigationID.setupWithNavController(navController)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.bottomNavigationID.setOnItemSelectedListener {
            when(viewModel.currentFragment) {
                1 -> {
                    when(it.itemId) {
                        R.id.fragmentSettings -> {
                            navController.navigate(R.id.action_fragmentMainPage_to_fragmentSettings)
                        }
                        R.id.fragmentsHistory -> {
                            navController.navigate(R.id.action_fragmentMainPage_to_fragmentsHistory)
                        }
                    }
                }
                2 -> {
                    when(it.itemId) {
                        R.id.fragmentMainPage -> {
                            navController.navigateUp()
                        }
                        R.id.fragmentSettings -> {
                            navController.navigate(R.id.action_fragmentsHistory_to_fragmentSettings)
                        }
                    }
                }
                3 -> {
                    when(it.itemId) {
                        R.id.fragmentMainPage -> {
                            navController.navigateUp()
                        }
                        R.id.fragmentsHistory -> {
                            navController.navigate(R.id.action_fragmentSettings_to_fragmentsHistory)
                        }
                    }
                }
            }
            true
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragmentMainPage -> {
                    binding.bottomNavigationID.selectedItemId = R.id.fragmentMainPage
                    viewModel.currentFragment = 1
                    navView.visibility = View.VISIBLE
                }
                R.id.fragmentsHistory -> {
                    viewModel.currentFragment = 2
                    navView.visibility = View.VISIBLE
                }
                R.id.fragmentSettings -> {
                    viewModel.currentFragment = 3
                    navView.visibility = View.VISIBLE
                }
                else -> {
                    viewModel.currentFragment = 0
                    navView.visibility = View.GONE
                    this.supportActionBar?.hide()
                }
            }
        }

    }

    companion object {
        const val LANGUAGE_KEY = "English"
        const val SHARED_PREFERENCES_KEY = "1"
    }

}