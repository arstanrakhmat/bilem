package com.example.bilemonline.app.fragments.searchPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.adapter.CourseAdapter
import com.example.bilemonline.adapter.CoursesCategoryAdapter
import com.example.bilemonline.adapter.FreeCourseAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentSearchBinding
import com.example.bilemonline.viewmodels.CategoryViewModel
import com.example.bilemonline.viewmodels.CourseViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val courseViewModel by viewModel<CourseViewModel>()
    private val categoryViewModel by viewModel<CategoryViewModel>()
    private lateinit var coursesCategoryAdapter: CoursesCategoryAdapter
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var freeCourseAdapter: FreeCourseAdapter

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRvForCoursesCategory()
        initPaidCourses()
        initCategory()
        setupRVForFreeCourses()
        courseViewModel.getListOfCoursesPaid(1, 10, "ASC", "id", false)
        courseViewModel.getListOfCoursesFree(1, 10, "ASC", "id", true)
        categoryViewModel.getListOfCategories(1, 20, "ASC", "id")
    }

    private fun initCategory() {
        categoryViewModel.category.observe(requireActivity()) {
            coursesCategoryAdapter.differ.submitList(it.data)
        }

        categoryViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun initPaidCourses() {
        courseViewModel.coursesPaid.observe(requireActivity()) {
            courseAdapter.differ.submitList(it.data)
        }

        courseViewModel.coursesFree.observe(requireActivity()) {
            freeCourseAdapter.differ.submitList(it.data)
        }

        courseViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun setupRvForCoursesCategory() {

        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.SPACE_EVENLY
        binding.rvCoursesCategory.layoutManager = layoutManager

        coursesCategoryAdapter = CoursesCategoryAdapter()
        binding.rvCoursesCategory.apply {
            adapter = coursesCategoryAdapter
        }
    }

    private fun setupRVForFreeCourses() {
//        val snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(binding.rvFreeCourses)

        binding.rvFreeCourses.layoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)

        courseAdapter = CourseAdapter()
        freeCourseAdapter = FreeCourseAdapter()
        binding.rvFreeCourses.apply {
            adapter = freeCourseAdapter
        }

        binding.rvPaidCourses.apply {
            adapter = courseAdapter
        }
    }

}