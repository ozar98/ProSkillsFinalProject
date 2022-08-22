package com.example.finalproskillsproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalproskillsproject.databinding.BalanceIncreaseFragmentBinding

class FragmentBalanceIncrease: Fragment() {
    private var _binding:BalanceIncreaseFragmentBinding?=null
    private val binding get() =_binding!!

    private lateinit var viewModel:FragmentBalanceIncreaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[FragmentBalanceIncreaseViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=BalanceIncreaseFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonsClickListener()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun buttonsClickListener(){
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.submit.setOnClickListener {
            getEntriesAndVerify()
            notifyUser()

        }
    }
    private fun getEntriesAndVerify(){
        val amount=binding.amountReplenishEntry.text.toString()
        val cardNumber=binding.cardNumberReplenishEntry.text.toString()
        if (cardNumber.length==16){
            viewModel.increaseBalance(amount.toLong(),cardNumber)
            findNavController().navigateUp()
        }else{
            Toast.makeText(requireContext(),"Card Number is not valid", Toast.LENGTH_SHORT).show()
        }
    }
    private fun notifyUser(){
        if (viewModel.transferedSuccessfully()){
            Toast.makeText(requireContext(),getText(R.string.succesful_tranfer), Toast.LENGTH_SHORT).show()
        }
    }
}