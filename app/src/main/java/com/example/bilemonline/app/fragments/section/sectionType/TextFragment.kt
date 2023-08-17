package com.example.bilemonline.app.fragments.section.sectionType

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentTextBinding

class TextFragment : BaseFragment<FragmentTextBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTextBinding {
        return FragmentTextBinding.inflate(inflater, container, false)
    }

}