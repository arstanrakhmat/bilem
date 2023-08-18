package com.example.bilemonline.app.fragments.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.R
import com.example.bilemonline.app.activity.AuthorizationActivity
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentProfileBinding
import com.example.bilemonline.viewmodels.UserViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val userViewModel by viewModel<UserViewModel>()
    private val sharedPrefs by inject<UserPreferences>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
        setupObserver()
        userViewModel.getProfileInfo("Bearer ${sharedPrefs.fetchToken()}")
    }

    private fun clickListeners() {
        binding.llModifyAccount.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_userInfoFragment)
        }

        binding.btnSignOut.setOnClickListener {
            startActivity(Intent(requireContext(), AuthorizationActivity::class.java))
        }

        binding.llChangeLanguage.setOnClickListener {
            languageBottomSheet()
        }
    }

    private fun setupObserver() {
        userViewModel.userInfo.observe(requireActivity()) {
            binding.tvUser.text = it.username
            binding.progressBar.visibility = View.GONE
        }

        userViewModel.error.observe(requireActivity()) {
            Log.d("Error in user fragment", it.toString())
        }

    }

    private fun setLocale(language: String, context: Context) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        context.createConfigurationContext(configuration)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }

    private fun setDefaultLocale(context: Context) {
        val locale = Locale.getDefault()
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        context.createConfigurationContext(configuration)
        context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
    }

    private fun languageBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetStyle
        )

        val view = layoutInflater.inflate(R.layout.bottom_sheet_language, null)

        view.findViewById<TextView>(R.id.russian).setOnClickListener {
            setDefaultLocale(requireContext())
            ActivityCompat.recreate(requireActivity())
            bottomSheetDialog.dismiss()
        }

        view.findViewById<TextView>(R.id.kyrgyz).setOnClickListener {
            setLocale("ky", requireContext())
            ActivityCompat.recreate(requireActivity())
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

}