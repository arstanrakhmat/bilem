package com.example.bilemonline.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bilemonline.app.fragments.section.sectionType.QuizFragment
import com.example.bilemonline.app.fragments.section.sectionType.TextFragment
import com.example.bilemonline.app.fragments.section.sectionType.VideoFragment
import com.example.bilemonline.data.model.ContentX
import com.example.bilemonline.utils.ContentType

class SectionCompletionViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val contentList: List<ContentX>
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return contentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (contentList[position].type) {
            ContentType.QUIZ.toString() -> QuizFragment(contentList[position])
            ContentType.TEXT.toString() -> TextFragment(contentList[position])
            ContentType.VIDEO.toString() -> VideoFragment(contentList[position])
            else -> Fragment()
        }
    }
}