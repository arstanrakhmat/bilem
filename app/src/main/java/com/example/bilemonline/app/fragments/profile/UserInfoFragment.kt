package com.example.bilemonline.app.fragments.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentUserInfoBinding
import com.example.bilemonline.viewmodels.UserViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>() {

    private val userViewModel by viewModel<UserViewModel>()
    private val sharedPrefs by inject<UserPreferences>()

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserInfoBinding {
        return FragmentUserInfoBinding.inflate(inflater, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        userViewModel.getProfileInfo("Bearer ${sharedPrefs.fetchToken()}")
    }

    private fun setupObserver() {
        userViewModel.userInfo.observe(requireActivity()) {
            binding.tvUserName.text = it.username
            binding.etName.setText(it.profile.firstName)
            binding.etLastname.setText(it.profile.lastName)
            binding.etAge.setText(it.profile.age.toString())
            binding.etAbout.setText(it.profile.about.toString())
            binding.etAbout.setText(it.profile.fieldOfActivity.toString())
//            binding.etWorkingPlace.setText(it.profile.workingPlace.toString())
            binding.etCity.setText(it.profile.city.toString())
            binding.progressBar.visibility = View.GONE
        }

        userViewModel.error.observe(requireActivity()) {
            Log.d("Error in user fragment", it.toString())
        }

    }


}