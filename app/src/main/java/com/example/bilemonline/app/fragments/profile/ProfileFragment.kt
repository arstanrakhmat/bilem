package com.example.bilemonline.app.fragments.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

}