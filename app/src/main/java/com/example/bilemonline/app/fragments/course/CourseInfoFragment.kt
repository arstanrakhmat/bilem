package com.example.bilemonline.app.fragments.course

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.bilemonline.adapter.AuthorAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentCourseInfoBinding
import com.example.bilemonline.viewmodels.CourseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseInfoFragment : BaseFragment<FragmentCourseInfoBinding>() {

    private lateinit var authorAdapter: AuthorAdapter
    private val courseViewModel by viewModel<CourseViewModel>()
    private var gotCourseId: String? = null
    private val sharedPrefs by inject<UserPreferences>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseInfoBinding {
        return FragmentCourseInfoBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        setupObservers()
        arguments?.let {
            gotCourseId = it.getString("course_id")
        }
        courseViewModel.getCourseById("Bearer ${sharedPrefs.fetchToken()}", gotCourseId!!)
    }

    private fun setupObservers() {
        courseViewModel.gotCourseByid.observe(requireActivity()) {
            with(binding) {
                tvDescription.text = it.description
            }
        }

        courseViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
            Log.d("training", it.toString())
        }
    }

    private fun setupRV() {
        authorAdapter = AuthorAdapter()
        binding.rvAuthors.adapter = authorAdapter
    }
}