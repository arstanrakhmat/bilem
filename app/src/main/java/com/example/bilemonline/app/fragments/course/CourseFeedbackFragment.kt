package com.example.bilemonline.app.fragments.course

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentCourseFeedbackBinding

class CourseFeedbackFragment : BaseFragment<FragmentCourseFeedbackBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseFeedbackBinding {
        return FragmentCourseFeedbackBinding.inflate(inflater, container, false)
    }
}