package com.example.bilemonline.utils

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.bilemonline.R

fun EditText.setFilledDrawable(
    filledDrawable: Drawable,
    defaultDrawable: Drawable,
) {
    val editText = this

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            if (p0.isNullOrEmpty()) {
                editText.background = defaultDrawable
            } else {
                editText.background = filledDrawable
            }
        }
    }

    editText.addTextChangedListener(textWatcher)
}

fun validName(editText: EditText, textView: TextView): Boolean {
    val name = editText.text.toString().trim()

    return if (name.isEmpty()) {
        textView.visibility = View.VISIBLE
        textView.text = textView.context.getString(R.string.empty_text_error)
        false
    } else if (name.length > 20) {
        textView.visibility = View.VISIBLE
        textView.text = textView.context.getString(R.string.invalid_name)
        false
    } else {
        textView.visibility = View.GONE
        true
    }
}

fun validEmail(editText: EditText, textView: TextView): Boolean {
    val email = editText.text.toString().trim()

    return if (email.isEmpty()) {
        textView.visibility = View.VISIBLE
        textView.text = textView.context.getString(R.string.empty_text_error)
        false
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        textView.visibility = View.VISIBLE
        textView.text = textView.context.getString(R.string.invalid_email)
        false
    } else {
        textView.visibility = View.GONE
        true
    }
}

fun validPassword(editText: EditText, textView: TextView): Boolean {
    val password = editText.text.toString().trim()

    return if (password.isEmpty()) {
        textView.visibility = View.VISIBLE
        textView.text = textView.context.getString(R.string.empty_text_error)
        false
    } else if (password.length < 4) {
        textView.visibility = View.VISIBLE
        textView.text = textView.context.getString(R.string.invalid_password)
        false
    } else {
        textView.visibility = View.GONE
        true
    }
}

fun validRepeatedPassword(
    editText: EditText,
    editText2: EditText,
    textView: TextView,
    textView2: TextView
): Boolean {
    val password = editText.text.toString().trim()
    val password2 = editText2.text.toString().trim()

    return if (password.isEmpty() && password2.isEmpty()) {
        textView.visibility = View.VISIBLE
        textView2.visibility = View.VISIBLE
        textView.text = textView.context.getString(R.string.empty_text_error)
        textView2.text = textView2.context.getString(R.string.empty_text_error)
        false
    } else if (password.length < 4) {
        textView.visibility = View.VISIBLE
        textView.text = textView.context.getString(R.string.invalid_password)
        false
    } else if (password != password2) {
        textView2.visibility = View.VISIBLE
        textView2.text = textView2.context.getString(R.string.password_does_not_match)
        false
    } else {
        textView.visibility = View.GONE
        textView2.visibility = View.GONE
        true
    }
}

