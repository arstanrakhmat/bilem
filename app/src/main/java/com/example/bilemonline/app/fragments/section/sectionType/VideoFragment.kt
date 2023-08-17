package com.example.bilemonline.app.fragments.section.sectionType

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentVideoBinding

class VideoFragment : BaseFragment<FragmentVideoBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVideoBinding {
        return FragmentVideoBinding.inflate(inflater, container, false)
    }
}