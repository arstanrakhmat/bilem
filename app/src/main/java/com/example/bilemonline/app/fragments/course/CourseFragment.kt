package com.example.bilemonline.app.fragments.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bilemonline.R
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentCourseBinding

class CourseFragment : BaseFragment<FragmentCourseBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseBinding {
        return FragmentCourseBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}