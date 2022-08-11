package com.example.finalproskillsproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalproskillsproject.databinding.LoginFragmentBinding

class FragmentLogin: Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

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

    }
    private fun changeFragmentListener(){
        binding.loginButton.setOnClickListener {
            userAuthentication()
            findNavController().navigate(R.id.fragmentMainPage)
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.fragmentRegistration)
        }
    }
}