package com.example.bilemonline.app.fragments.section

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.bilemonline.R
import com.example.bilemonline.adapter.SectionCompletionViewPagerAdapter
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.data.model.ContentX
import com.example.bilemonline.databinding.FragmentSectionCompletionBinding
import com.example.bilemonline.utils.ContentType
import com.example.bilemonline.viewmodels.CourseViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SectionCompletionFragment : BaseFragment<FragmentSectionCompletionBinding>() {

    private val args: SectionCompletionFragmentArgs by navArgs()
    private val sharedPrefs by inject<UserPreferences>()
    private val courseViewModel by viewModel<CourseViewModel>()
    private lateinit var contentList: List<ContentX>
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSectionCompletionBinding {
        return FragmentSectionCompletionBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setupViewPager()
//        setupTabLayout(binding.tabLayoutCompletionSection, binding.viewPager)
        setupObserver()
        courseViewModel.getContentBySectionId(
            "Bearer ${sharedPrefs.fetchToken()}",
            args.sectionCompletion.id
        )
        binding.tvCourseName.text = args.sectionCompletion.theme
    }

    private fun setupTabLayout(tabLayout: TabLayout, viewPager: ViewPager2) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (contentList[position].type) {
                ContentType.TEXT.toString() -> tab.setIcon(R.drawable.ic_text)
                ContentType.VIDEO.toString() -> tab.setIcon(R.drawable.ic_video)
                ContentType.QUIZ.toString() -> tab.setIcon(R.drawable.ic_quiz)
            }
        }.attach()
    }

    private fun setupObserver() {
        courseViewModel.gotContentBySectionId.observe(requireActivity()) {
            contentList = it.content
            setupViewPager(it.content)
            setupTabLayout(binding.tabLayoutCompletionSection, binding.viewPager)
        }
        courseViewModel.error.observe(requireActivity()) {
            Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
            Log.d("contentError", it.toString())
        }

    }

    private fun setupViewPager(conList: List<ContentX>) {
        val adapter = SectionCompletionViewPagerAdapter(requireActivity(), conList)
        binding.viewPager.adapter = adapter
    }

}