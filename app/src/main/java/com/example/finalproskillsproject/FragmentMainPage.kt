package com.example.finalproskillsproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalproskillsproject.databinding.HistoryBsLayoutBinding
import com.example.finalproskillsproject.databinding.LoginFragmentBinding
import com.example.finalproskillsproject.databinding.MainMenuFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.NonDisposableHandle.parent

class FragmentMainPage: Fragment() {
    private var _binding: MainMenuFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    private var _bindingBS:HistoryBsLayoutBinding?=null
    private val bindingBS get()=_binding!!
    private var transactionInfo:BottomSheetDialog?=null

    private val historyAdapter=HistoryAdapter()
    private val cardsAdapter=CardsAdapter()
    private val cashBacksAdapter=CashbacksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=MainMenuFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonsOnClickListener()
        setUpBottomSheetActivity()
        setUpTexts()
        setUpAdapters()
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


    private fun buttonsOnClickListener(){
        binding.addCard.setOnClickListener {
            //findNavController().navigate(R.id.fragmentAddCard)
            transactionInfo?.show()

        }
        binding.transactionButton.setOnClickListener {
            findNavController().navigate(R.id.fragmentTransaction)
        }
        binding.addBalance.setOnClickListener {
            findNavController().navigate(R.id.fragmentBalanceIncrease)
        }

    }
    private fun setUpAdapters(){

    }
    private fun setUpBottomSheetActivity(){
        _bindingBS=HistoryBsLayoutBinding.inflate(LayoutInflater.from(requireContext())).also {
            transactionInfo= BottomSheetDialog(requireContext())
            transactionInfo?.setContentView(it.root)
        }
    }
    private fun setUpTexts(){
        binding.balance.text=viewModel.getBalanceInfo()
        binding.phoneNumber.text=viewModel.getNumber()
        binding.cashback.text=viewModel.getCashBack()
    }
}