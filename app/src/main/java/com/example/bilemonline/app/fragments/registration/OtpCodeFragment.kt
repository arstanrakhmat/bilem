package com.example.bilemonline.app.fragments.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentOtpCodeBinding

class OtpCodeFragment : BaseFragment<FragmentOtpCodeBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOtpCodeBinding {
        return FragmentOtpCodeBinding.inflate(inflater, container, false)
    }
}