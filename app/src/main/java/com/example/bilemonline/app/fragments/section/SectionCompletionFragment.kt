package com.example.bilemonline.app.fragments.section

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentSectionCompletionBinding

class SectionCompletionFragment : BaseFragment<FragmentSectionCompletionBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSectionCompletionBinding {
        return FragmentSectionCompletionBinding.inflate(inflater, container, false)
    }

}