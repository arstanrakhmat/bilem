package com.example.bilemonline.app.fragments.section

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bilemonline.R
import com.example.bilemonline.adapter.SectionAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentCourseSectionBinding
import com.example.bilemonline.viewmodels.CourseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CourseSectionFragment : BaseFragment<FragmentCourseSectionBinding>() {

    private val args: CourseSectionFragmentArgs by navArgs()
    private val courseViewModel by viewModel<CourseViewModel>()
    private val sharedPrefs by inject<UserPreferences>()
    private lateinit var sectionAdapter: SectionAdapter

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCourseSectionBinding {
        return FragmentCourseSectionBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()
        courseViewModel.getSectionByModuleId("Bearer ${sharedPrefs.fetchToken()}", args.section)

        sectionAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("sectionCompletion", it)
            }

            findNavController().navigate(R.id.sectionCompletionFragment, bundle)
        }

        sectionAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("sectionCompletion", it)
            }

            findNavController().navigate(R.id.action_courseSectionFragment_to_sectionCompletionFragment, bundle)
        }

        binding.btnArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun setupObservers() {
        courseViewModel.gotSectionByModuleId.observe(requireActivity()) {
            sectionAdapter.differ.submitList(it.sections)

        }

        courseViewModel.error.observe(requireActivity()) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
            Log.d("errorMmm", it.toString())
        }

    }

    private fun setupAdapter() {
        sectionAdapter = SectionAdapter()
        binding.rvSection.apply {
            adapter = sectionAdapter
        }

    }

}