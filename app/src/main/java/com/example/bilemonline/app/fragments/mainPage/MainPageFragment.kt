package com.example.bilemonline.app.fragments.mainPage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentMainPageBinding

class MainPageFragment : BaseFragment<FragmentMainPageBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainPageBinding {
        return FragmentMainPageBinding.inflate(inflater, container, false)
    }
}