package com.example.bilemonline.app.fragments.searchPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bilemonline.R
import com.example.bilemonline.adapter.CourseAdapter
import com.example.bilemonline.adapter.CoursesCategory
import com.example.bilemonline.adapter.CoursesCategoryAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.model.Course
import com.example.bilemonline.databinding.FragmentSearchBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private lateinit var coursesCategoryAdapter: CoursesCategoryAdapter
    private lateinit var courseAdapter: CourseAdapter

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRvForCoursesCategory()
        setupRVForFreeCourses()
    }

    private fun setupRvForCoursesCategory() {

        val coursesCategory = listOf(
            CoursesCategory("Java"),
            CoursesCategory("Вестибюлярный апаратттттттт"),
            CoursesCategory("Java"),
            CoursesCategory("Java"),
            CoursesCategory("Python"),
            CoursesCategory("Програмирование"),
            CoursesCategory("Дизайн"),
            CoursesCategory("Бизнес"),
            CoursesCategory("Джава"),
            CoursesCategory("Питхон"),
            CoursesCategory("Програмирование"),
            CoursesCategory("Фронт"),
            CoursesCategory("Програмирование")
        )

        val layoutManager = FlexboxLayoutManager(requireContext())
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.SPACE_EVENLY
        binding.rvCoursesCategory.layoutManager = layoutManager

        coursesCategoryAdapter = CoursesCategoryAdapter(coursesCategory)
        binding.rvCoursesCategory.apply {
            adapter = coursesCategoryAdapter
        }
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

}