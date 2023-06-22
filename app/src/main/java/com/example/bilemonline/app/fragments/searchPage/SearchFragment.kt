package com.example.bilemonline.app.fragments.searchPage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

}