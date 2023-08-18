package com.example.bilemonline.app.fragments.course.fromCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bilemonline.adapter.CourseListInLearningsAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentCoursesFromCategoryBinding
import com.example.bilemonline.viewmodels.CourseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoursesFromCategoryFragment : BaseFragment<FragmentCoursesFromCategoryBinding>() {

    private lateinit var courseListAdapter: CourseListInLearningsAdapter
    private val courseViewModel by viewModel<CourseViewModel>()
    private val args: CoursesFromCategoryFragmentArgs by navArgs()
    private val sharePreferences by inject<UserPreferences>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCoursesFromCategoryBinding {
        return FragmentCoursesFromCategoryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        courseViewModel.getCoursesFromCategory(
            "Bearer ${sharePreferences.fetchToken()}",
            args.category.id
        )
        binding.tvCourseName.text = args.category.name
        binding.btnArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
        setupRv()
    }

    private fun setupObserver() {
        courseViewModel.gotCoursesFromCategory.observe(requireActivity()) {
            courseListAdapter.differ.submitList(it)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setupRv() {
        courseListAdapter = CourseListInLearningsAdapter()
        binding.rvCoursesFromCategory.adapter = courseListAdapter
    }
}