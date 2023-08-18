package com.example.bilemonline.app.fragments.section.sectionType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.core.view.isVisible
import com.example.bilemonline.R
import com.example.bilemonline.app.fragments.BaseFragment
import com.example.bilemonline.data.model.ContentX
import com.example.bilemonline.databinding.FragmentQuizBinding

class QuizFragment(private val quizContent: ContentX) : BaseFragment<FragmentQuizBinding>() {
    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuizBinding {
        return FragmentQuizBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvHeader.text = quizContent.header
        when (quizContent.isMulti) {
            false -> {
                binding.llCheckbox.visibility = View.GONE
                binding.radioGroup.removeAllViews()
                quizContent.questions.forEachIndexed { index, variant ->
                    val radioButton = RadioButton(requireContext())
                    radioButton.text = quizContent.questions[index]
                    binding.radioGroup.addView(radioButton)
                }
            }

            true -> {
                binding.radioGroup.visibility = View.GONE
                quizContent.questions.forEachIndexed { index, variant ->
                    val checkBox = CheckBox(requireContext())
                    checkBox.text = quizContent.questions[index]
                    binding.llCheckbox.addView(checkBox)
                }
            }
        }
    }
}