package com.example.bilemonline.app.fragments.myLearnings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bilemonline.adapter.CourseListInLearningsAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentAllCoursesBinding
import com.example.bilemonline.viewmodels.CourseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCoursesFragment : BaseFragment<FragmentAllCoursesBinding>() {

    private lateinit var courseListAdapter: CourseListInLearningsAdapter
    private val courseViewModel by viewModel<CourseViewModel>()
    private val sharedPrefs by inject<UserPreferences>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAllCoursesBinding {
        return FragmentAllCoursesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setupObservers()
        courseViewModel.getPassingCourses("Bearer ${sharedPrefs.fetchToken()}")
    }

    private fun setupObservers() {
        courseViewModel.gotPassingCourses.observe(requireActivity()) {
            courseListAdapter.differ.submitList(it)
            binding.progressBar.visibility = View.GONE
        }

        courseViewModel.error.observe(requireActivity()) {
            Log.d("allCoursesProblem", it.toString())
        }
    }

    private fun setupRv() {
        courseListAdapter = CourseListInLearningsAdapter()
        binding.recycler.adapter = courseListAdapter
    }
}