package com.example.bilemonline.app.fragments.myLearnings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.bilemonline.R
import com.example.bilemonline.adapter.LearningsViewPagerAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentMyLearningsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MyLearningsFragment : BaseFragment<FragmentMyLearningsBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyLearningsBinding {
        return FragmentMyLearningsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupTabLayout(binding.tabLayout, binding.viewPager)
    }

    private fun setupTabLayout(tabLayout: TabLayout, viewPager2: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.all_courses)
                1 -> tab.text = resources.getString(R.string.wish_list_courses)
                2 -> tab.text = resources.getString(R.string.favorite_courses)
                3 -> tab.text = resources.getString(R.string.completed_courses)
            }
        }.attach()
    }

    private fun setupViewPager() {
        val adapter = LearningsViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
    }
}