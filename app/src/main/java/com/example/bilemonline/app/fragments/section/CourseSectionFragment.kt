package com.example.bilemonline.app.fragments.section

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentCourseSectionBinding

class CourseSectionFragment : BaseFragment<FragmentCourseSectionBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseSectionBinding {
        return FragmentCourseSectionBinding.inflate(inflater, container, false)
    }

}