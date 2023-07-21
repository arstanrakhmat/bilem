package com.example.bilemonline.app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.bilemonline.R
import com.example.bilemonline.databinding.ActivityOnboardingBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}