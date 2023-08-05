package com.example.bilemonline.app.fragments.registration

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.bilemonline.R
import com.example.bilemonline.app.activity.MainActivity
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.UserPreferences
import com.example.bilemonline.databinding.FragmentSignInBinding
import com.example.bilemonline.utils.setFilledDrawable
import com.example.bilemonline.utils.validEmail
import com.example.bilemonline.utils.validPassword
import com.example.bilemonline.viewmodels.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val authViewModel by viewModel<AuthViewModel>()
    private lateinit var sharedPreferences: UserPreferences
    private lateinit var googleSignInClient: GoogleSignInClient

    private val RC_SIGN_IN = 123

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilledDrawable()
        clickListener()
        setupObservers()

        sharedPreferences = UserPreferences(requireContext())

    }

    private fun setFilledDrawable() {
        binding.etLogin.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )

        binding.etPassword.setFilledDrawable(
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_filled)!!,
            ContextCompat.getDrawable(requireContext(), R.drawable.edit_text_input_default)!!,
        )
    }

    private fun clickListener() {
        binding.btnSignIn.setOnClickListener {
            if (checkEditTexts()) {
//                Toast.makeText(requireContext(), "Validated", Toast.LENGTH_SHORT).show()
                authViewModel.userSignIn(
                    binding.etLogin.text.toString(),
                    binding.etPassword.text.toString()
                )
//                Log.d("LoginChekc", "Email: ${binding.etLogin.toString()}")
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_registrationFragment)
        }

        binding.btnForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }

        binding.btnGoogle.setOnClickListener {
//            signInGoogle()
            startGoogleSignIn()
        }
    }

    private fun startGoogleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Sign-in was successful, now you can access the account information
                val account = task.getResult(ApiException::class.java)
                handleSignedInAccount(account)
            } catch (e: ApiException) {
                // Sign-in failed, handle the error
                // You can show a message or take appropriate action
            }
        }
    }

    private fun handleSignedInAccount(account: GoogleSignInAccount?) {
        if (account != null) {
            Log.d(
                "GoogleAuth",
                "Account Info: Email is ${account.email}\n, Photo is ${account.photoUrl}\n Name is ${account.displayName}"
            )
            Toast.makeText(requireContext(), "Successfully authenticated", Toast.LENGTH_SHORT)
                .show()
            // TODO: Do something with the user's information
        }
    }

//    private fun signInGoogle() {
//        val signInIntent = googleSignInClient.signInIntent
//        launcher.launch(signInIntent)
//    }

//    private val launcher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//                handleResults(task)
//            } else {
//                Toast.makeText(requireContext(), "Could not do anything else", Toast.LENGTH_SHORT).show()
//            }
//        }

//    private fun handleResults(task: Task<GoogleSignInAccount>) {
//        if (task.isSuccessful) {
//            val account: GoogleSignInAccount? = task.result
//            if (account != null) {
//                updateUI(account)
//            }
//        } else {
//            Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT).show()
//        }
//    }

//    private fun updateUI(account: GoogleSignInAccount) {
//        Log.d("GoogleAuth", "Token: ${account.idToken}")
//
//        val accInfo = GoogleSignIn.getLastSignedInAccount(requireContext())
//        if (accInfo != null) {
//            Log.d(
//                "GoogleAuth",
//                "Account Info: Email is ${accInfo.email}\n, Photo is ${accInfo.photoUrl}\n Name is ${accInfo.displayName}"
//            )
//        }
//
//        Toast.makeText(requireContext(), "Successfully authenticated", Toast.LENGTH_SHORT).show()
//    }

    private fun setupObservers() {
        authViewModel.singIn.observe(requireActivity()) {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            sharedPreferences.saveToken(it.accessToken)
            sharedPreferences.saveRefreshToken(it.refreshToken)
        }

        authViewModel.errorMessage.observe(requireActivity()) {
            Log.d("authE", it)
            Toast.makeText(requireContext(), "Неверный логин или пароль!", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun checkEditTexts(): Boolean {
        val validEmail = validEmail(binding.etLogin, binding.tvLoginError)
        val validPassword = validPassword(binding.etPassword, binding.tvPasswordError)

        return validEmail && validPassword
    }

}