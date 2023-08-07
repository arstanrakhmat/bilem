package com.example.bilemonline.app.fragments.course

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.bilemonline.R
import com.example.bilemonline.adapter.CourseInfoViewPagerAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentCourseBinding
import com.example.bilemonline.viewmodels.CourseViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseFragment : BaseFragment<FragmentCourseBinding>() {

    private val args: CourseFragmentArgs by navArgs()
    private val courseViewModel by viewModel<CourseViewModel>()

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
        courseViewModel.getCourseById(args.course.id)
        setupObservers()
    }

    private fun setupObservers() {
        courseViewModel.gotCourseByid.observe(requireActivity()) {
            with(binding) {
                tvCourseTitle.text = it.title
                rbRating.rating = it.rating.toFloat()

            }
        }

        courseViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
            Log.d("training", it.toString())
        }
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