package com.example.bilemonline.app.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.R
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentSignInBinding
import com.example.bilemonline.utils.setFilledDrawable
import com.example.bilemonline.utils.validEmail
import com.example.bilemonline.utils.validPassword

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilledDrawable()
        clickListener()

    }

    private fun setFilledDrawable() {
        binding.etLogin.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )

        binding.etPassword.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )
    }

    private fun clickListener() {
        binding.btnSignIn.setOnClickListener {
            if (checkEditTexts()) {
                Toast.makeText(requireContext(), "Validated", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_registrationFragment)
        }

        binding.btnForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }
    }

    private fun checkEditTexts(): Boolean {
        val validEmail = validEmail(binding.etLogin, binding.tvLoginError)
        val validPassword = validPassword(binding.etPassword, binding.tvPasswordError)

        return validEmail && validPassword
    }

}