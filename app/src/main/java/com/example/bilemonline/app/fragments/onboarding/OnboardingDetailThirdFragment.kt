package com.example.bilemonline.app.fragments.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentOnboardingDetailThirdBinding

class OnboardingDetailThirdFragment : BaseFragment<FragmentOnboardingDetailThirdBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingDetailThirdBinding {
        return FragmentOnboardingDetailThirdBinding.inflate(inflater, container, false)
    }

}