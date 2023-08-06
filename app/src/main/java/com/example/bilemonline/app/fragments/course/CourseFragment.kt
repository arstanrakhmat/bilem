package com.example.bilemonline.app.fragments.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.bilemonline.R
import com.example.bilemonline.adapter.CourseInfoViewPagerAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentCourseBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CourseFragment : BaseFragment<FragmentCourseBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseBinding {
        return FragmentCourseBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupTabLayout(binding.tabLayoutCourse, binding.viewPagerCourse)
    }

    private fun setupTabLayout(tabLayout: TabLayout, viewPager2: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.course_info)
                1 -> tab.text = resources.getString(R.string.course_feedback)
            }
        }.attach()
    }

    private fun setupViewPager() {
        val adapter = CourseInfoViewPagerAdapter(requireActivity())
        binding.viewPagerCourse.adapter = adapter
    }
}