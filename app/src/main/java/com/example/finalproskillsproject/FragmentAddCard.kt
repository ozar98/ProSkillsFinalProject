package com.example.finalproskillsproject

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalproskillsproject.databinding.AddCardFragmentBinding
import java.util.*


class FragmentAddCard:Fragment() {

    private var _binding: AddCardFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel:AddCardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[AddCardViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=AddCardFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getEntries()
        buttonsOnClickListeners()
    }
    private fun buttonsOnClickListeners(){
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.submit.setOnClickListener {
            getData()
            findNavController().navigateUp()
        }
        binding.validButton.setOnClickListener {
            getDate()
        }
    }
    private fun getEntries(){

    }
    private fun getData(){

    }
    private fun getDate(){
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                binding.cardExpireEntry.setText(viewModel.getBirthDate(year, month, day))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()

    }


}