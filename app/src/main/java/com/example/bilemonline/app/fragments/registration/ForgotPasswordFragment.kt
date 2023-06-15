package com.example.bilemonline.app.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.R
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentForgotPasswordBinding
import com.example.bilemonline.utils.setFilledDrawable
import com.example.bilemonline.utils.validEmail

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

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
                val action =
                    ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToOtpCodeFragment(
                        1
                    )
                findNavController().navigate(action)

            }
        }
    }

    private fun checkEditText(): Boolean {
        return validEmail(binding.etNewEmail, binding.tvEmailError)
    }

}