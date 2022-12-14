package com.example.finalproskillsproject

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalproskillsproject.databinding.AddCardFragmentBinding
import java.util.*


class FragmentAddCard:Fragment() {

    private var _binding: AddCardFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel:AddCardViewModel
    private lateinit var cardType: List<String>
    private val args:FragmentAddCardArgs by navArgs()
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

        buttonsOnClickListeners()
        setupCardTypeSpinner()
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
    private fun getData(){
        val cardType=binding.cardTypeSpinner.adapter.getItem(binding.cardTypeSpinner.selectedItemPosition).toString()
        val cardNumber=binding.cardNumberEntry.text.toString()
        val validDate=binding.cardExpireEntry.text.toString()
        val bankName=binding.cardNameEntry.text.toString()
        Log.d("TAG_CARD", "${args.id}")
        viewModel.addCard(CardsRetrofit(1,cardType, cardNumber, validDate,1000,args.id,bankName))
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
    private fun setupCardTypeSpinner(){
        cardType = resources.getStringArray(R.array.CardType).toList()

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, cardType
        )
        binding.cardTypeSpinner.adapter = adapter
    }


}