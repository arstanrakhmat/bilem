package com.example.bilemonline.app.fragments.section.sectionType

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.model.ContentX
import com.example.bilemonline.databinding.FragmentQuizBinding

class QuizFragment(quizContent: ContentX) : BaseFragment<FragmentQuizBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuizBinding {
        return FragmentQuizBinding.inflate(inflater, container, false)
    }

}