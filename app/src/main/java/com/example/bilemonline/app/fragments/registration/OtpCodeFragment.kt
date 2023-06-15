package com.example.bilemonline.app.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bilemonline.R
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentOtpCodeBinding

class OtpCodeFragment : BaseFragment<FragmentOtpCodeBinding>() {

    private val args by navArgs<OtpCodeFragmentArgs>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOtpCodeBinding {
        return FragmentOtpCodeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirm.setOnClickListener {
            if (binding.pinView.length() < 6) {
                binding.tvOtpError.visibility = View.VISIBLE
            } else {
                binding.tvOtpError.visibility = View.GONE
                when (args.parentFragment) {
                    1 -> findNavController().navigate(R.id.action_otpCodeFragment_to_newPasswordFragment)
                    else -> Toast.makeText(
                        requireContext(),
                        "get from REGISTRATION fragment",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}