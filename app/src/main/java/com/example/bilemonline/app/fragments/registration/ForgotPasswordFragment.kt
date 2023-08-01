package com.example.bilemonline.app.fragments.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.R
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentForgotPasswordBinding
import com.example.bilemonline.utils.setFilledDrawable
import com.example.bilemonline.utils.validEmail
import com.example.bilemonline.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

    private val authViewModel by viewModel<AuthViewModel>()
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForgotPasswordBinding {
        return FragmentForgotPasswordBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilledDrawable()
        clickListeners()
        setupObservers()
    }

    private fun setFilledDrawable() {
        binding.etNewEmail.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )
    }

    private fun clickListeners() {
        binding.btnApply.setOnClickListener {
            if (checkEditText()) {
                authViewModel.userForgotPassword(binding.etNewEmail.text.toString())
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {
        authViewModel.resetPassword.observe(requireActivity()) {
            val action =
                ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToOtpCodeFragment(
                    parentFragment = 1, email = binding.etNewEmail.text.toString()
                )
            findNavController().navigate(action)
        }

        authViewModel.errorMessage.observe(requireActivity()) {
            Log.d("auth", it)
            Toast.makeText(requireContext(), "Something went wrong...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkEditText(): Boolean {
        return validEmail(binding.etNewEmail, binding.tvEmailError)
    }

}