package com.example.finalproskillsproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
    private val bindingBS get()=_bindingBS!!
    private var transactionInfo:BottomSheetDialog?=null

    private val historyAdapter=HistoryAdapter()
    private val cardsAdapter=CardsAdapter()
    private val cashBacksAdapter=CashbacksAdapter()

    private val args:FragmentMainPageArgs by navArgs()

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
        _binding=MainMenuFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapters()
        observeLiveData()
        setUpBottomSheetActivity()
        buttonsOnClickListener()
        setUpTexts()

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
            findNavController().navigate(FragmentMainPageDirections.actionFragmentMainPageToFragmentAddCard(args.id))

        }
        binding.transactionButton.setOnClickListener {
            findNavController().navigate(FragmentMainPageDirections.actionFragmentMainPageToFragmentTransaction(args.id))
        }
        binding.addBalance.setOnClickListener {
            findNavController().navigate(FragmentMainPageDirections.actionFragmentMainPageToFragmentBalanceIncrease(args.id))
        }

        historyAdapter.onItemClick={
            showTransaction(it)
        }

    }

    private fun setUpBottomSheetActivity(){
        _bindingBS=HistoryBsLayoutBinding.inflate(LayoutInflater.from(requireContext())).also {
            transactionInfo= BottomSheetDialog(requireContext())
            transactionInfo?.setContentView(it.root)
        }
    }
    private fun setUpTexts(){

    }
    private fun observeLiveData() {
        viewModel.personLiveData.observe(viewLifecycleOwner){ person->
            person?.let {
                binding.balance.text=person.amount.toString()
                binding.cashback.text=person.cashbackamount.toString()
                binding.phoneNumber.text=person.phoneNumber
            }
        }
        viewModel.cardsLiveData.observe(viewLifecycleOwner){
            if (it!=null){
                Log.d("TAG_CASH", "${it}")
                cardsAdapter.submitList(it)
            }
        }
        viewModel.transactionLiveData.observe(viewLifecycleOwner){
            if (it!=null){
                historyAdapter.submitList(it)
            }
        }
        viewModel.cashbackLiveData.observe(viewLifecycleOwner){
            if (it!=null){

                cashBacksAdapter.submitList(it)
            }
        }

        viewModel.getPerson(args.id)
        viewModel.getCards()
        viewModel.getTransactions()
        viewModel.getCashbacks()

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

    private fun setupAdapters(){
        binding.cardsRv.adapter=cardsAdapter
        binding.historyRv.adapter=historyAdapter
        binding.cashbackRv.adapter=cashBacksAdapter
    }
}