package com.example.bilemonline.app.fragments.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.bilemonline.R
import com.example.bilemonline.app.activity.AuthorizationActivity
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentOnboardingBinding

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {

    private val fragmentList = arrayListOf<Fragment>(
        OnboardingDetailFirst(),
        OnboardingDetailSecondFragment(),
        OnboardingDetailThirdFragment()
    )

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingBinding {
        return FragmentOnboardingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeCurrentItem()
        setupViewPager()
        clickListener()

//        binding.changeActivity.setOnClickListener {
//            val intent = Intent(requireContext() , MainActivity::class.java)
//            startActivity(intent)
//        }

    }

    private fun setupViewPager() {

        val adapter = OnboardingViewPagerAdapter(
            fragmentList, requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)
    }

    private fun observeCurrentItem() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == fragmentList.size - 1) {
                    binding.bntNext.text = resources.getText(R.string.start)
                } else {
                    binding.bntNext.text = resources.getText(R.string.next)
                }
            }
        })
    }

    private fun clickListener() {
        binding.bntNext.setOnClickListener {
            val currentFragmentIndex = binding.viewPager.currentItem
            val nextFragmentIndex = currentFragmentIndex + 1

            if (nextFragmentIndex < fragmentList.size) {
                binding.viewPager.setCurrentItem(nextFragmentIndex, true)
            } else {
                startActivity(Intent(requireContext(), AuthorizationActivity::class.java))
            }
        }

        binding.btnSkip.setOnClickListener {
            startActivity(Intent(requireContext(), AuthorizationActivity::class.java))
        }
    }

}