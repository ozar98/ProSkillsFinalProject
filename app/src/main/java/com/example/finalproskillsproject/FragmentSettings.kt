package com.example.finalproskillsproject

import android.content.Context.MODE_PRIVATE
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalproskillsproject.databinding.ChangeLanguageBsBinding
import com.example.finalproskillsproject.databinding.HistoryBsLayoutBinding
import com.example.finalproskillsproject.databinding.RegistrationFragmentBinding
import com.example.finalproskillsproject.databinding.SettingsFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class FragmentSettings : Fragment() {
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!

    private var _bindingBS: ChangeLanguageBsBinding? = null
    private val bindingBS get() = _bindingBS!!
    private var languageChange: BottomSheetDialog? = null

    private lateinit var viewModel: MainViewModel

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
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBottomSheetActivity()
        onButtonsClickListener()
        observeLiveData()

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


    private fun onButtonsClickListener() {
        binding.settingName.setOnClickListener {
            findNavController().navigate(
                FragmentSettingsDirections.actionFragmentSettingsToChangeProfileFragment(
                    viewModel.personID
                )
            )
        }
        binding.settingsQr.setOnClickListener {

        }
        binding.settingsLanguage.setOnClickListener {
            Log.d("TAG_Test", "show")
            languageChange?.show()
        }
        binding.settingsVersion.setOnClickListener {

        }
        binding.help.setOnClickListener {

        }
        binding.logOut.setOnClickListener {
            requireActivity().getSharedPreferences(
                MainActivity.SHARED_PREFERENCES_KEY,
                MODE_PRIVATE
            ).edit().remove(
                ID_KEY
            ).apply()
            findNavController().navigate(R.id.action_fragmentSettings_to_fragmentLogin)
        }
        bindingBS.back.setOnClickListener {
            languageChange?.dismiss()
        }
        bindingBS.changeLanguage.setOnClickListener {
            changeLanguage()
        }


    }

    private fun setUpBottomSheetActivity() {
        _bindingBS = ChangeLanguageBsBinding.inflate(LayoutInflater.from(requireContext())).also {
            languageChange = BottomSheetDialog(requireContext())
            languageChange?.setContentView(it.root)
        }
        bindingBS.english.isChecked = language == "en"
        bindingBS.russian.isChecked = language == "ru"
    }

    private fun changeLanguage() {
        if (bindingBS.english.isChecked) {
            language = "en"
            requireContext().getSharedPreferences(MainActivity.SHARED_PREFERENCES_KEY, MODE_PRIVATE)
                .edit().putString(MainActivity.LANGUAGE_KEY, "en").apply()
        } else if (bindingBS.russian.isChecked) {
            language = "ru"
            requireContext().getSharedPreferences(MainActivity.SHARED_PREFERENCES_KEY, MODE_PRIVATE)
                .edit().putString(MainActivity.LANGUAGE_KEY, "ru").apply()
        }

        requireActivity().recreate()
    }

    private fun observeLiveData() {
        binding.apply {
            fullName.text = viewModel.personName
            settingName.text = viewModel.personName
        }
    }
}