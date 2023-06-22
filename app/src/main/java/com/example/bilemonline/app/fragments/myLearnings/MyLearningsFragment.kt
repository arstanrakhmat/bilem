package com.example.bilemonline.app.fragments.myLearnings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentMyLearningsBinding

class MyLearningsFragment : BaseFragment<FragmentMyLearningsBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyLearningsBinding {
        return FragmentMyLearningsBinding.inflate(inflater, container, false)
    }


}