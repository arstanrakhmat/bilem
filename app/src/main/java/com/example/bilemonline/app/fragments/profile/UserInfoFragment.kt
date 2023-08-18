package com.example.bilemonline.app.fragments.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentUserInfoBinding
import com.example.bilemonline.viewmodels.UserViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoFragment : BaseFragment<FragmentUserInfoBinding>() {

    private val userViewModel by viewModel<UserViewModel>()
    private val sharedPrefs by inject<UserPreferences>()
    private lateinit var userId: String

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
        binding.btnUpdateUser.setOnClickListener {
            updateUser2()
        }
        binding.btnArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObserver() {
        userViewModel.userInfo.observe(requireActivity()) {
            userId = it.id
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

        userViewModel.dataUpdated.observe(requireActivity()) {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(requireContext(), "Данные усппешно обновлены", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

        userViewModel.error.observe(requireActivity()) {
            Log.d("Error in user fragment", it.toString())
        }

    }

    private fun updateUser() {
        userViewModel.updateUserInfo(
            userId,
            binding.etName.text.toString(),
            binding.etLastname.text.toString(),
            binding.etAge.text.toString().toInt(),
            binding.etAbout.text.toString(),
            binding.etFieldOfActivity.text.toString(),
            binding.etEducation.text.toString(),
            binding.etCity.text.toString()
        )

        binding.progressBar.visibility = View.VISIBLE
    }

    private fun updateUser2() {
        userViewModel.updateUserInfo2(
            userId,
            binding.etName.text.toString(),
            binding.etLastname.text.toString(),
            binding.etAbout.text.toString()
        )

        binding.progressBar.visibility = View.VISIBLE
    }


}