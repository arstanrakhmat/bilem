package com.example.bilemonline.app.fragments.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentNewPasswordBinding

class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding>() {

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewPasswordBinding {
        return FragmentNewPasswordBinding.inflate(inflater, container, false)
    }
}