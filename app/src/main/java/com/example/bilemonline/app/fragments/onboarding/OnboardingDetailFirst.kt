package com.example.bilemonline.app.fragments.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentOnboardingDetailFirstBinding

class OnboardingDetailFirst : BaseFragment<FragmentOnboardingDetailFirstBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingDetailFirstBinding {
        return FragmentOnboardingDetailFirstBinding.inflate(inflater, container, false)
    }

}