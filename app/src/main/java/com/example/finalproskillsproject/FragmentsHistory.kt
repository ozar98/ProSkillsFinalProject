package com.example.finalproskillsproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.finalproskillsproject.databinding.HistoryBsLayoutBinding
import com.example.finalproskillsproject.databinding.HistoryFragmentBinding
import com.example.finalproskillsproject.databinding.RegistrationFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class FragmentsHistory: Fragment() {
    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HistoryViewModel

    private var _bindingBS:HistoryBsLayoutBinding?=null
    private val bindingBS get()=_bindingBS!!
    private var transactionInfo: BottomSheetDialog?=null


    private val historyAdapter=HistoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[HistoryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= HistoryFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        observeLiveData()
        setUpBottomSheetActivity()
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

    private fun setupAdapters(){
        binding.historyRv.adapter=historyAdapter
    }
    private fun observeLiveData(){
        viewModel.transactionLiveData.observe(viewLifecycleOwner){
            if (it!=null){
                historyAdapter.submitList(it)
            }
        }
        viewModel.getTransactions()

        historyAdapter.onItemClick={
            showTransaction(it)
        }
    }

    private fun setUpBottomSheetActivity(){
        _bindingBS= HistoryBsLayoutBinding.inflate(LayoutInflater.from(requireContext())).also {
            transactionInfo= BottomSheetDialog(requireContext())
            transactionInfo?.setContentView(it.root)
        }
    }
    private fun showTransaction(transaction: TransactionResponse) {
        transactionInfo?.show()
        bindingBS.transactionPersonBs.text="${getText(R.string.transaction)} ${transaction?.sendername}"
        bindingBS.transactionAmount.text=transaction.amount.toString()+" TJS"
        if (transaction.receivertype=="Card") {
            bindingBS.transactionReceiverBs.visibility=View.GONE
            bindingBS.transactionImageBs.setImageResource(R.drawable.ic_credit_card)
        }else if(transaction.receivertype=="Phone"){

            bindingBS.transactionReceiverBs.visibility=View.VISIBLE
            bindingBS.transactionImageBs.setImageResource(R.drawable.ic_wallet)
            bindingBS.transactionReceiverBs.text="${getText(R.string.receiverTransaction)} ${transaction.receivername}"
        }
        bindingBS.transactionMethodBs.text="${getText(R.string.payment_method)} ${transaction.sendertype}"
        bindingBS.transactionDateBs.text=transaction.date
        bindingBS.transactionId.text="${getText(R.string.transactionID)} ${transaction.id.toString()}"
        bindingBS.back.setOnClickListener {
            transactionInfo?.dismiss()
        }
    }
}