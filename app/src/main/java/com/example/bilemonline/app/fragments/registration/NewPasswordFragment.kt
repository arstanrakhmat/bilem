package com.example.bilemonline.app.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.R
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentNewPasswordBinding
import com.example.bilemonline.utils.*

class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewPasswordBinding {
        return FragmentNewPasswordBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilledDrawable()
        clickListener()
    }

    private fun setFilledDrawable() {
        binding.etPassword.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )

        binding.etPasswordRepeat.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )
    }

    private fun clickListener() {
        binding.btnSignIn.setOnClickListener {
            if (checkEditTexts()) {
                findNavController().navigate(R.id.action_newPasswordFragment_to_signInFragment)
            }
        }
    }

    private fun checkEditTexts(): Boolean {
        val validPassword = validPassword(binding.etPassword, binding.tvPasswordError)
        val validPasswordRepeat = validRepeatedPassword(
            binding.etPassword,
            binding.etPasswordRepeat,
            binding.tvPasswordError,
            binding.tvPasswordRepeatError
        )

        return validPassword && validPasswordRepeat
    }
}