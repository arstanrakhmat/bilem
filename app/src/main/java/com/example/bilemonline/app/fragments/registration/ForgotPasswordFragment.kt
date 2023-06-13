package com.example.bilemonline.app.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentForgotPasswordBinding {
        return FragmentForgotPasswordBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnApply.setOnClickListener {
            val action =
                ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToOtpCodeFragment(1)
            findNavController().navigate(action)
        }
    }

}