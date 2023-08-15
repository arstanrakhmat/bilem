package com.example.bilemonline.app.fragments.module

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bilemonline.adapter.ModuleAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentCourseModuleBinding
import com.example.bilemonline.viewmodels.CourseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseModuleFragment : BaseFragment<FragmentCourseModuleBinding>() {

    private val courseViewModel by viewModel<CourseViewModel>()
    private val args: CourseModuleFragmentArgs by navArgs()
    private val sharedPrefs by inject<UserPreferences>()
    private lateinit var moduleAdapter: ModuleAdapter

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseModuleBinding {
        return FragmentCourseModuleBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCourseName.text = args.title
        binding.btnArrowBack.setOnClickListener { findNavController().navigateUp() }
        setupRv()
        setupObserver()
        courseViewModel.getModuleById("Bearer ${sharedPrefs.fetchToken()}", args.courseId)
    }

    private fun setupObserver() {
        courseViewModel.gotModuleById.observe(requireActivity()) {
            moduleAdapter.differ.submitList(it)
        }
        courseViewModel.error.observe(requireActivity()) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
            Log.d("errorMmm", it.toString())
        }
    }

    private fun setupRv() {
        moduleAdapter = ModuleAdapter()
        binding.rvModule.apply {
            adapter = moduleAdapter
        }
    }

}