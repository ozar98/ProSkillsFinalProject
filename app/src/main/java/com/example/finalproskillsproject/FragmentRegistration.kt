package com.example.finalproskillsproject

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalproskillsproject.databinding.RegistrationFragmentBinding
import java.util.*

class FragmentRegistration : Fragment() {
    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserInfo()
        onButtonsClickedListener()

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
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun getUserInfo() {
        val name = binding.registrationNameEntry.text.toString()
        val surname = binding.registrationSurnameEntry.text.toString()
        val password = binding.registrationPassword.text.toString()
        val phone = binding.registrationPhoneEntry.text.toString()
        val birthDate = binding.birthDateText.text.toString()
    }

    private fun entriesAreFilled(): Boolean {
        binding.apply {
            if (registrationNameEntry.text.isEmpty() ||
                registrationPhoneEntry.text.isEmpty() ||
                registrationSurnameEntry.text.isEmpty() ||
                registrationPassword.text.isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "please fill in required entry" + if (!registrationCheckbox.isChecked) " and accept conditions" else "",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }
        return true
    }

    private fun onButtonsClickedListener() {
        binding.registrationNext.setOnClickListener {
            if (entriesAreFilled() && binding.registrationCheckbox.isChecked) {

                findNavController().navigate(R.id.fragmentMainPage)
            }
        }
        binding.english.setOnClickListener {
            TODO()
        }
        binding.russian.setOnClickListener {
            TODO()
        }
        binding.datePicker.setOnClickListener {
            getBirthDate()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun getBirthDate() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                binding.birthDateText.text = viewModel.getBirthDate(year, month, day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()

    }
}