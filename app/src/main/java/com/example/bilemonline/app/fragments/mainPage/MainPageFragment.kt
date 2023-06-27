package com.example.bilemonline.app.fragments.mainPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.R
import com.example.bilemonline.adapter.CategoryAdapter
import com.example.bilemonline.adapter.CourseAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.model.Category
import com.example.bilemonline.data.model.Course
import com.example.bilemonline.databinding.FragmentMainPageBinding

class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {

    private lateinit var courseAdapter: CourseAdapter
    private lateinit var categoryAdapter: CategoryAdapter

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
    }

    private fun setupRVForFreeCourses() {
//        val snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(binding.rvFreeCourses)

        binding.rvFreeCourses.layoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)

        val courses = listOf(
            Course(
                "English for beginners",
                "Language zone",
                R.drawable.image_course_item,
                3.5,
                12,
                1500
            ),

            Course(
                "English for intermediates",
                "Language zone",
                R.drawable.image_item_couse_2,
                4.7,
                19,
                1999
            ),

            Course(
                "English for advanced",
                "Language zone",
                R.drawable.image_course_item,
                4.4,
                43,
                1999
            ),
            Course(
                "English for kids",
                "Language zone",
                R.drawable.image_item_couse_2,
                5.0,
                97,
                1999
            )
        )


        courseAdapter = CourseAdapter(courses)
        binding.rvFreeCourses.apply {
            adapter = courseAdapter
        }

        binding.rvPaidCourses.apply {
            adapter = courseAdapter
        }
    }

    private fun setupRvCategories() {
        binding.rvCategories.layoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)

        val categories = listOf(
            Category("UX/UI Дизайн", "32 курса", R.color.custom_blue),
            Category("UX/UI Дизайн", "32 курса", R.color.custom_pink),
            Category("UX/UI Дизайн", "32 курса", R.color.custom_purple),
            Category("UX/UI Дизайн", "32 курса", R.color.custom_yellow),
            Category("UX/UI Дизайн", "32 курса", R.color.custom_green),
            Category("UX/UI Дизайн", "32 курса", R.color.custom_blue),
        )

        categoryAdapter = CategoryAdapter(categories)
        binding.rvCategories.apply {
            adapter = categoryAdapter
        }
    }
}