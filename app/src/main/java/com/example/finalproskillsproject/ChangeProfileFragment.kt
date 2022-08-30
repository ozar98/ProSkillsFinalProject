package com.example.finalproskillsproject

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalproskillsproject.databinding.ChangeProfileInfoFragmentBinding
import java.util.*

class ChangeProfileFragment:Fragment() {
    private var _binding: ChangeProfileInfoFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel:MainViewModel

    private val args:ChangeProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(it)[MainViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=ChangeProfileInfoFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getButtonsClicked()
        getData()
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

    private fun getButtonsClicked(){
        binding.changeBirth.setOnClickListener {
            getBirthDate()
        }
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.applyChanges.setOnClickListener {
            changeProfileData()

        }
    }
    private fun getBirthDate(){
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                binding.birthFixed.setText(viewModel.getBirthDate(year,month,day))
                binding.birthFixed.visibility=View.VISIBLE
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    private fun changeProfileData(){
        viewModel.changeProfile(binding.changeName.text.toString(),
        binding.birthFixed.text.toString())
        findNavController().navigateUp()
    }
    private fun getData(){
       viewModel.personLiveData.observe(viewLifecycleOwner){
            binding.apply {
                phone.setText(it?.phoneNumber)
                changeName.setText(it?.fullName)

            }
        }
    }


}