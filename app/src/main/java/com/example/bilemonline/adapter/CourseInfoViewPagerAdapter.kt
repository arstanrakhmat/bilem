package com.example.bilemonline.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bilemonline.app.fragments.course.CourseFeedbackFragment
import com.example.bilemonline.app.fragments.course.CourseInfoFragment

private const val NUM_OF_FRAGMENTS = 2

class CourseInfoViewPagerAdapter(fragmentActivity: FragmentActivity, private val courseId: String) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = NUM_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = CourseInfoFragment()
                fragment.arguments = Bundle().apply {
                    putString("course_id", courseId)
                }
                fragment
            }

            1 -> {
                val fragment = CourseFeedbackFragment()
                fragment.arguments = Bundle().apply {
                    putString("course_id", courseId)
                }
                fragment
            }
            else -> Fragment()
        }
    }
}