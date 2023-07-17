package com.example.bilemonline.app.fragments.myLearnings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bilemonline.R
import com.example.bilemonline.adapter.CourseListInLearningsAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.model.Course
import com.example.bilemonline.databinding.FragmentWishListCoursesBinding

class WishListCoursesFragment : BaseFragment<FragmentWishListCoursesBinding>() {

    private lateinit var courseListAdapter: CourseListInLearningsAdapter

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWishListCoursesBinding {
        return FragmentWishListCoursesBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
    }

    private fun setupRv() {

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

        courseListAdapter = CourseListInLearningsAdapter(courses)
        binding.recycler.adapter = courseListAdapter
    }


}