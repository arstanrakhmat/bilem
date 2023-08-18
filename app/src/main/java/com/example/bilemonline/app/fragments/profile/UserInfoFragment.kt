package com.example.bilemonline.app.fragments.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentUserInfoBinding

class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserInfoBinding {
        return FragmentUserInfoBinding.inflate(inflater, container, false)
    }


}