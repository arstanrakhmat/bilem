package com.example.bilemonline.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bilemonline.app.fragments.section.sectionType.QuizFragment
import com.example.bilemonline.app.fragments.section.sectionType.TextFragment
import com.example.bilemonline.app.fragments.section.sectionType.VideoFragment

private const val NUM_OF_FRAGMENTS = 3

class SectionCompletionViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return NUM_OF_FRAGMENTS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TextFragment()
            1 -> VideoFragment()
            2 -> QuizFragment()
            else -> Fragment()
        }
    }
}