package com.example.finalproskillsproject


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalproskillsproject.databinding.TransactionFragmentBinding


class FragmentTransaction: Fragment() {
    private var _binding:TransactionFragmentBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: TransactionViewModel

    private val args:FragmentTransactionArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[TransactionViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=TransactionFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onButtonsClick()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun onButtonsClick(){
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.fragmentMainPage)
        }
        binding.submit.setOnClickListener {
            sendToNumber()
        }
    }

    private fun sendToNumber(){
        val amount=binding.amountEntry.text.toString()
        val number=binding.sendToEntry.text.toString()
        viewModel.receiverIDLiveData.observe(viewLifecycleOwner){
            viewModel.sendToNumber(args.id,amount.toLong(),number,it)
            Toast.makeText(
                requireContext(),
                getText(R.string.succesful_tranfer) ,
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigateUp()
        }
        viewModel.getreceiverID(number)
    }



}