package com.barbieri.mini_tumblr_app.utils

import android.content.Context
import android.content.SharedPreferences
import com.barbieri.mini_tumblr_app.models.Preferences
import com.google.gson.Gson

object PreferencesUtils {


    private val gson = Gson()

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE)
    }

    fun getMyUserPref(context: Context, PREF: String): Preferences? {
        return gson.fromJson(getPrefs(context).getString(PREF, ""), Preferences::class.java)
    }

    fun setMyUserPref(context: Context, preferences: Preferences?, PREF: String) {
        getPrefs(context).edit().putString(PREF, gson.toJson(preferences)).apply()
    }

    fun cleanMyUserPref(context: Context, PREF: String) {
        getPrefs(context).edit().putString(PREF, null).apply()
    }



}