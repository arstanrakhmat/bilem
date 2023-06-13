package com.example.bilemonline.utils

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.bilemonline.R

fun EditText.setupDynamicStrokeColorForNameAndEmail(
    filledDrawable: Drawable,
    defaultDrawable: Drawable,
    errorTextView: TextView,
    isEmailValidationEnabled: Boolean = false
): Boolean {
    val editText = this
    var isValid = false

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            if (isEmailValidationEnabled) {
                if (p0.isNullOrEmpty()) {
                    errorTextView.visibility = View.VISIBLE
                    errorTextView.text = resources.getText(R.string.empty_text_error)
                    editText.background = defaultDrawable
                } else if (!isValidEmail(p0.toString())) {
                    editText.background = filledDrawable
                    errorTextView.text = resources.getText(R.string.invalid_email)
                    errorTextView.visibility = View.VISIBLE
                } else {
                    editText.background = filledDrawable
                    errorTextView.visibility = View.GONE
                    isValid = true
                }
            } else {
                if (p0.isNullOrEmpty()) {
                    errorTextView.visibility = View.VISIBLE
                    editText.background = defaultDrawable
                } else {
                    editText.background = filledDrawable
                    errorTextView.visibility = View.GONE
                    isValid = true
                }
            }
        }
    }

    editText.addTextChangedListener(textWatcher)

    return isValid
}

fun isValidEmail(email: String): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}
