package com.example.bilemonline.app.fragments.registration

import android.os.Bundle
import android.os.CountDownTimer
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

        binding.btnApply.setOnClickListener {
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

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnResendOtp.setOnClickListener {
            startTimer()
        }
    }

    private fun startTimer() {
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvResendCode.text =
                    "Отправить код повторно через: 00:${millisUntilFinished / 1000}"
                binding.btnResendOtp.visibility = View.GONE
            }

            override fun onFinish() {
                binding.tvResendCode.visibility = View.GONE
                binding.btnResendOtp.visibility = View.VISIBLE
            }
        }.start()
    }
}