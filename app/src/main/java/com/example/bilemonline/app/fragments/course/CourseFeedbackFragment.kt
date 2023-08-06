package com.example.bilemonline.app.fragments.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bilemonline.adapter.FeedbackAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentCourseFeedbackBinding

class CourseFeedbackFragment : BaseFragment<FragmentCourseFeedbackBinding>() {

    private lateinit var feedbackAdapter: FeedbackAdapter
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseFeedbackBinding {
        return FragmentCourseFeedbackBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
    }

    private fun setupRv() {
        feedbackAdapter = FeedbackAdapter()
        binding.rvFeedback.adapter = feedbackAdapter
    }
}