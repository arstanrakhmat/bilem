package com.example.bilemonline.app.fragments.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentOnboardingDetailSecondBinding

class OnboardingDetailSecondFragment : BaseFragment<FragmentOnboardingDetailSecondBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingDetailSecondBinding {
        return FragmentOnboardingDetailSecondBinding.inflate(inflater, container, false)
    }

}