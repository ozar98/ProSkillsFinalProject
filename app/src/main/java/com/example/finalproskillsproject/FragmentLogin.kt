package com.example.finalproskillsproject

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.finalproskillsproject.databinding.LoginFragmentBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentLogin: Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel:LoginViewModel

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[LoginViewModel::class.java]
        sharedPreferences = requireActivity().getSharedPreferences(MainActivity.SHARED_PREFERENCES_KEY, MODE_PRIVATE)
        if(sharedPreferences.contains(ID_KEY)) {
            viewModel.id = sharedPreferences.getInt(ID_KEY, 0)
            findNavController().navigate(FragmentLoginDirections.actionFragmentLoginToFragmentMainPage(viewModel.id))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeFragmentListener()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun userAuthentication(){
        val login=binding.loginEditText.text.toString()
        val password=binding.password.text.toString()
        viewModel.verifyPerson(login,password)

    }
    private fun changeFragmentListener(){
        binding.loginButton.setOnClickListener {
            userAuthentication()
        }
        viewModel.liveData.observe(viewLifecycleOwner){
            if (viewModel.id!=0){
                sharedPreferences.edit().putInt(ID_KEY, viewModel.id).apply()
                findNavController().navigate(FragmentLoginDirections.actionFragmentLoginToFragmentMainPage(viewModel.id))
            }
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistration)
        }
    }



}