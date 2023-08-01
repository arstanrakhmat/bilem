package com.example.bilemonline.app.fragments.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bilemonline.R
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentNewPasswordBinding
import com.example.bilemonline.utils.*
import com.example.bilemonline.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding>() {

    private val args by navArgs<NewPasswordFragmentArgs>()
    private val authViewModel by viewModel<AuthViewModel>()
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
        setupObservers()
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
        binding.btnApply.setOnClickListener {
            if (checkEditTexts()) {
                authViewModel.userChangePassword(
                    args.email,
                    binding.etPassword.text.toString(),
                    binding.etPasswordRepeat.text.toString()
                )

                Log.d("ChaningPassword", "${args.email}, ${binding.etPassword.text.toString()}")
            }
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {
        authViewModel.changed.observe(requireActivity()) {
            findNavController().navigate(R.id.action_newPasswordFragment_to_signInFragment)
            Toast.makeText(requireContext(), "Пароль успешно обновлен", Toast.LENGTH_SHORT).show()
        }

        authViewModel.errorMessage.observe(requireActivity()) {
            Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_SHORT).show()
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