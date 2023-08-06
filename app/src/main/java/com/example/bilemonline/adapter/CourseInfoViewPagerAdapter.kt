package com.example.bilemonline.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bilemonline.app.fragments.course.CourseFeedbackFragment
import com.example.bilemonline.app.fragments.course.CourseInfoFragment

private const val NUM_OF_FRAGMENTS = 2

class CourseInfoViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = NUM_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CourseInfoFragment()
            1 -> CourseFeedbackFragment()
            else -> Fragment()
        }
    }
}