package com.example.bilemonline.data

import android.content.Context
import android.content.SharedPreferences
import com.example.bilemonline.utils.Constants

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("App", Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    fun saveUserId(token: String?) {
        if (token != null) {
            editor.putString(Constants.TOKEN, token).apply()
        }
    }

    fun fetchUserId(): String? {
        return prefs.getString(Constants.TOKEN, "token")
    }
}