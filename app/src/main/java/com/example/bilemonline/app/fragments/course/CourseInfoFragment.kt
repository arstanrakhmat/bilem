package com.example.bilemonline.app.fragments.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bilemonline.adapter.AuthorAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentCourseInfoBinding

class CourseInfoFragment : BaseFragment<FragmentCourseInfoBinding>() {

    private lateinit var authorAdapter: AuthorAdapter
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseInfoBinding {
        return FragmentCourseInfoBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
    }

    private fun setupRV() {
        authorAdapter = AuthorAdapter()
        binding.rvAuthors.adapter = authorAdapter
    }
}