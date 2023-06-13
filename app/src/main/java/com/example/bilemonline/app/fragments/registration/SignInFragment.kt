package com.example.bilemonline.app.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.R
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentSignInBinding
import com.example.bilemonline.utils.setupDynamicStrokeColorForNameAndEmail

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_registrationFragment)
        }

        binding.btnForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }

        binding.etLogin.setupDynamicStrokeColorForNameAndEmail(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
            binding.tvLoginError,
            isEmailValidationEnabled = true
        )
    }
}