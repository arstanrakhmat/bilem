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
import com.example.bilemonline.data.model.UserSignUpRequest
import com.example.bilemonline.databinding.FragmentRegistrationBinding
import com.example.bilemonline.utils.*
import com.example.bilemonline.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    private val authViewModel by viewModel<AuthViewModel>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilledDrawable()
        clickListeners()
        setupObservers()
    }

    private fun setFilledDrawable() {
        binding.etName.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )

        binding.etEmail.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )

        binding.etPassword.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )

        binding.etPasswordRepeat.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )
    }

    private fun clickListeners() {
        binding.bntCreateAnAccount.setOnClickListener {
            if (checkEditTexts()) {
//                Toast.makeText(requireContext(), "Validated", Toast.LENGTH_SHORT).show()

                authViewModel.userSignUp(
                    UserSignUpRequest(
                        binding.etName.text.toString(),
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString()
                    )
                )
            }
        }

        binding.btnHaveAccount.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {
        authViewModel.register.observe(requireActivity()) {
            Toast.makeText(requireContext(), it.code, Toast.LENGTH_LONG).show()

            val action =
                RegistrationFragmentDirections.actionRegistrationFragmentToOtpCodeFragment(
                    0,
                    binding.etName.text.toString(),
                    binding.etEmail.text.toString()
                )
            findNavController().navigate(action)
        }

        authViewModel.errorMessage.observe(requireActivity()) {
            Log.d("authError", it)
            Toast.makeText(
                requireContext(),
                "Пользователь с такими данными уже существует",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun checkEditTexts(): Boolean {
        val validName = validName(binding.etName, binding.tvNameError)
        val validEmail = validEmail(binding.etEmail, binding.tvLoginError)
        val validPassword = validPassword(binding.etPassword, binding.tvPasswordError)
        val validPasswordRepeat = validRepeatedPassword(
            binding.etPassword,
            binding.etPasswordRepeat,
            binding.tvPasswordError,
            binding.tvPasswordRepeatError
        )

        return validName && validEmail && validPassword && validPasswordRepeat
    }

}