package com.example.bilemonline.app.fragments.course

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.bilemonline.R
import com.example.bilemonline.adapter.CourseInfoViewPagerAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentCourseBinding
import com.example.bilemonline.viewmodels.CourseViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseFragment : BaseFragment<FragmentCourseBinding>() {

    private val args: CourseFragmentArgs by navArgs()
    private val courseViewModel by viewModel<CourseViewModel>()
    private val sharedPrefs by inject<UserPreferences>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseBinding {
        return FragmentCourseBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupTabLayout(binding.tabLayoutCourse, binding.viewPagerCourse)
        clickListeners()
        courseViewModel.getCourseById("Bearer ${sharedPrefs.fetchToken()}", args.course.id)
//        setupObservers()
    }

    private fun clickListeners() {
        binding.btnStartLearning.setOnClickListener {
            val action =
                CourseFragmentDirections.actionCourseFragmentToCourseModuleFragment(
                    args.course.id,
                    args.course.title
                )
            findNavController().navigate(action)
        }

        binding.btnAddFeedback.setOnClickListener {
            feedbackBottomSheet()
        }

        binding.btnAddToFavorites.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            courseViewModel.addCourseToFavorites(
                "Bearer ${sharedPrefs.fetchToken()}",
                args.course.id
            )
        }
    }

    private fun setupObservers() {
        courseViewModel.gotCourseByid.observe(requireActivity()) {
            with(binding) {
                tvCourseTitle.text = it.title
                rbRating.rating = it.rating.toFloat()
                tvHowManyStudied.text = it.students
            }

            binding.progressBar.visibility = View.GONE
        }

        courseViewModel.isSavedToFavorites.observe(requireActivity()) {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(activity, "Успешно добавленное в изберанное", Toast.LENGTH_LONG)
                .show()
            binding.btnAddToFavorites.text = resources.getString(R.string.added_to_favorites)
        }

        courseViewModel.error.observe(requireActivity()) {
            Toast.makeText(activity, "An error occurred: $it", Toast.LENGTH_LONG)
                .show()
            Log.d("training", it.toString())
        }
    }

    private fun setupTabLayout(tabLayout: TabLayout, viewPager2: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = resources.getString(R.string.course_info)
                1 -> tab.text = resources.getString(R.string.course_feedback)
            }
        }.attach()
    }

    private fun setupViewPager() {
        val adapter = CourseInfoViewPagerAdapter(requireActivity(), args.course.id)
        binding.viewPagerCourse.adapter = adapter
    }

    private fun feedbackBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(
            requireContext(),
            R.style.BottomSheetStyle
        )

        val view = layoutInflater.inflate(R.layout.bottom_sheet_feedback, null)

        view.findViewById<TextView>(R.id.btnSendFeedback).setOnClickListener {
            ActivityCompat.recreate(requireActivity())
            Toast.makeText(requireContext(), "Спасибо за отзыв", Toast.LENGTH_LONG).show()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}