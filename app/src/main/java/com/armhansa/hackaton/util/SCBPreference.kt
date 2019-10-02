package com.armhansa.hackaton.util

import android.content.Context
import android.content.SharedPreferences

class SCBPreference(context: Context) {

    companion object {
        private const val SHARE_PREF_FILE_NAME = "ScbHackaton"
        private const val FIRST_TIME_PREF_KEY = "isFirstTime"
        private const val USERNAME_PREF_KEY = "scbUsername"
        private const val OLD_BLUETOOTH_NAME_KEY = "oldBluetoothName"
        private const val DEFAULT_BT_NAME = "default"
    }

    private val pref: SharedPreferences = context.getSharedPreferences(SHARE_PREF_FILE_NAME, Context.MODE_PRIVATE)

    fun saveNotFirstTime() {
        pref.edit().run {
            putBoolean(FIRST_TIME_PREF_KEY, false)
            apply()
        }
    }

    fun isFirstTime(): Boolean = pref.getBoolean(FIRST_TIME_PREF_KEY, true)

    fun saveUsername(username: String) {
        pref.edit().run {
            putString(USERNAME_PREF_KEY, username)
            apply()
        }
    }

    fun getUsername(): String = pref.getString(USERNAME_PREF_KEY, "Default") ?: "Default"

    fun saveOldBtName(oldBtName: String) {
        pref.edit().run {
            putString(OLD_BLUETOOTH_NAME_KEY, DEFAULT_BT_NAME)
            apply()
        }
    }

    fun getOldBtName() = pref.getString(OLD_BLUETOOTH_NAME_KEY, null)

}