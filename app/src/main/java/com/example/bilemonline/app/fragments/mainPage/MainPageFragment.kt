package com.example.bilemonline.app.fragments.mainPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.R
import com.example.bilemonline.adapter.CategoryAdapter
import com.example.bilemonline.adapter.CourseAdapter
import com.example.bilemonline.adapter.FreeCourseAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentMainPageBinding
import com.example.bilemonline.viewmodels.CategoryViewModel
import com.example.bilemonline.viewmodels.CourseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {

    private val courseViewModel by viewModel<CourseViewModel>()
    private val categoryViewModel by viewModel<CategoryViewModel>()
    private val sharePreferences by inject<UserPreferences>()

    private lateinit var courseAdapter: CourseAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var freeCourseAdapter: FreeCourseAdapter

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRVForFreeCourses()
        setupRvCategories()
        initPaidCourses()
        initCategory()
        courseViewModel.getListOfCoursesPaid(
            "Bearer ${sharePreferences.fetchToken()}",
            1,
            10,
            "ASC",
            "id",
            false
        )
        courseViewModel.getListOfCoursesFree(
            "Bearer ${sharePreferences.fetchToken()}",
            1,
            10,
            "ASC",
            "id",
            true
        )
        categoryViewModel.getListOfCategories(2, 20, "DESC", "id")

        courseAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("course", it)
            }
            findNavController().navigate(R.id.action_mainPageFragment_to_courseFragment, bundle)
        }

        categoryAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("category", it)
            }

            findNavController().navigate(
                R.id.action_mainPageFragment_to_coursesFromCategoryFragment,
                bundle
            )
        }

        freeCourseAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("course", it)
            }
            findNavController().navigate(R.id.action_mainPageFragment_to_courseFragment, bundle)
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

    private fun initCategory() {
        categoryViewModel.category.observe(requireActivity()) {
            categoryAdapter.differ.submitList(it.data)
        }

        categoryViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun setupRVForFreeCourses() {

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

    private fun setupRvCategories() {
        binding.rvCategories.layoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)

        categoryAdapter = CategoryAdapter()
        binding.rvCategories.apply {
            adapter = categoryAdapter
        }
    }
}