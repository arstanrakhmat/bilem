package com.example.bilemonline.app.fragments.registration

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bilemonline.R
import com.example.bilemonline.app.activity.MainActivity
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.data.model.UserActivateRequest
import com.example.bilemonline.databinding.FragmentOtpCodeBinding
import com.example.bilemonline.viewmodels.AuthViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtpCodeFragment : BaseFragment<FragmentOtpCodeBinding>() {

    private val args by navArgs<OtpCodeFragmentArgs>()
    private val authViewModel by viewModel<AuthViewModel>()
    private val sharedPreferences by inject<UserPreferences>()
    private var timer: CountDownTimer? = null

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOtpCodeBinding {
        return FragmentOtpCodeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        clickListeners()

    }

    private fun clickListeners() {
        binding.btnApply.setOnClickListener {
            if (binding.pinView.length() < 6) {
                binding.tvOtpError.visibility = View.VISIBLE
            } else {
                binding.tvOtpError.visibility = View.GONE
                when (args.parentFragment) {
                    1 -> {
                        cancelTimer()
                        findNavController().navigate(R.id.action_otpCodeFragment_to_newPasswordFragment)
                    }

                    else -> {
                        cancelTimer()

                        val otp = binding.pinView.text?.trim().toString()

                        if (otp.length < 6) {
                            binding.tvOtpError.visibility = View.VISIBLE
                        } else {
                            authViewModel.userActivate(
                                UserActivateRequest(
                                    args.userName,
                                    args.email,
                                    otp
                                )
                            )
                        }
                    }
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

    private fun setupObservers() {
        authViewModel.activated.observe(requireActivity()) {
            Toast.makeText(requireContext(), "Юзер Активирован", Toast.LENGTH_SHORT).show()
            sharedPreferences.saveToken(it.accessToken)
            sharedPreferences.saveRefreshToken(it.refreshToken)
            startActivity(Intent(requireContext(), MainActivity::class.java))

            Log.d(
                "tokens",
                "Token: ${sharedPreferences.fetchToken()}\n Refresh Token: ${sharedPreferences.fetchRefreshToken()}"
            )
        }

        authViewModel.errorMessage.observe(requireActivity()) {
            binding.tvOtpError.visibility = View.VISIBLE
            Log.d("auth", it)
            Toast.makeText(requireContext(), "Incorrect otp", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvResendCode.text =
                    "Отправить код повторно через: 00:${millisUntilFinished / 1000}"
                binding.btnResendOtp.visibility = View.GONE
                binding.tvResendCode.visibility = View.VISIBLE
            }

            override fun onFinish() {
                binding.tvResendCode.visibility = View.GONE
                binding.btnResendOtp.visibility = View.VISIBLE
            }
        }.start()
    }

    private fun cancelTimer() {
        timer?.cancel()
        timer = null
    }
}