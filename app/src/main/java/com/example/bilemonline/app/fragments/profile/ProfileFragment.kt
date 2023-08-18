package com.example.bilemonline.app.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.R
import com.example.bilemonline.app.activity.AuthorizationActivity
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    private fun clickListeners() {
        binding.llModifyAccount.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_userInfoFragment)
        }

        binding.btnSignOut.setOnClickListener {
            startActivity(Intent(requireContext(), AuthorizationActivity::class.java))
        }
    }

}