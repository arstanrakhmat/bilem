package com.example.bilemonline.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bilemonline.app.fragments.myLearnings.AllCoursesFragment
import com.example.bilemonline.app.fragments.myLearnings.CompletedCoursesFragment
import com.example.bilemonline.app.fragments.myLearnings.FavoriteCoursesFragment

private const val NUM_OF_FRAGMENTS = 3

class LearningsViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = NUM_OF_FRAGMENTS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllCoursesFragment()
            1 -> FavoriteCoursesFragment()
            2 -> CompletedCoursesFragment()
            else -> Fragment()
        }
    }
}