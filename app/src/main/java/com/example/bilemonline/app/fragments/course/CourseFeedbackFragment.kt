package com.example.bilemonline.app.fragments.course

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bilemonline.adapter.FeedbackAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentCourseFeedbackBinding
import com.example.bilemonline.viewmodels.CourseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseFeedbackFragment : BaseFragment<FragmentCourseFeedbackBinding>() {

    private var gotCourseId: String? = null
    private val courseViewModel by viewModel<CourseViewModel>()
    private lateinit var feedbackAdapter: FeedbackAdapter
    private val sharedPrefs by inject<UserPreferences>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseFeedbackBinding {
        return FragmentCourseFeedbackBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setupObservers()
        arguments?.let {
            gotCourseId = it.getString("course_id")
        }
        courseViewModel.getCourseById("Bearer ${sharedPrefs.fetchToken()}", gotCourseId!!)
    }

    private fun setupObservers() {
        courseViewModel.gotCourseByid.observe(requireActivity()) {
            with(binding) {
                ratingInText.text = it.rating.toDouble().toString()
                ratingInStars.rating = it.rating.toFloat()
            }
        }

        courseViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
            Log.d("training", it.toString())
        }
    }

    private fun setupRv() {
        feedbackAdapter = FeedbackAdapter()
        binding.rvFeedback.adapter = feedbackAdapter
    }
}