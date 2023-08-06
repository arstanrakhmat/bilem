package com.example.bilemonline.app.fragments.course

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentCourseInfoBinding

class CourseInfoFragment : BaseFragment<FragmentCourseInfoBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseInfoBinding {
        return FragmentCourseInfoBinding.inflate(inflater, container, false)
    }
}