package com.example.bilemonline.data

import android.content.Context
import android.content.SharedPreferences
import com.example.bilemonline.utils.Constants

class UserPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("App", Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    private val refreshPrefs: SharedPreferences =
        context.getSharedPreferences("App2", Context.MODE_PRIVATE)
    private val refreshEditor = refreshPrefs.edit()

    fun saveToken(token: String?) {
        if (token != null) {
            editor.putString(Constants.TOKEN, token).apply()
        }
    }

    fun fetchToken(): String? {
        return prefs.getString(Constants.TOKEN, "token")
    }

    fun saveRefreshToken(refreshToken: String?) {
        if (refreshToken != null) {
            refreshEditor.putString(Constants.REFRESH_TOKEN, refreshToken).apply()
        }
    }

    fun fetchRefreshToken(): String? {
        return refreshPrefs.getString(Constants.REFRESH_TOKEN, "refresh_token")
    }
}