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
import com.example.bilemonline.databinding.FragmentRegistrationBinding
import com.example.bilemonline.utils.*

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

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
                Toast.makeText(requireContext(), "Validated", Toast.LENGTH_SHORT).show()

                val action =
                    RegistrationFragmentDirections.actionRegistrationFragmentToOtpCodeFragment(0)
                findNavController().navigate(action)

            }
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