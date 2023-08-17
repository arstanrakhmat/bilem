package com.example.bilemonline.app.fragments.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.bilemonline.R
import com.example.bilemonline.adapter.SectionCompletionViewPagerAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentSectionCompletionBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SectionCompletionFragment : BaseFragment<FragmentSectionCompletionBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSectionCompletionBinding {
        return FragmentSectionCompletionBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupTabLayout(binding.tabLayoutCompletionSection, binding.viewPager)
    }

    private fun setupTabLayout(tabLayout: TabLayout, viewPager: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_text)
                1 -> tab.setIcon(R.drawable.ic_video)
                2 -> tab.setIcon(R.drawable.ic_quiz)
            }
        }.attach()
    }

    private fun setupViewPager() {
        val adapter = SectionCompletionViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
    }

}