package com.armhansa.hackaton.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import com.armhansa.hackaton.util.SCBPreference
import kotlinx.android.synthetic.main.activity_setting_name.*

class SettingNameActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SettingNameActivity::class.java))
        }
    }

    private val scbPref by lazy { SCBPreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_name)

        setView()
    }

    private fun setView() {
        tvUsername.text = scbPref.getUsername()
        btnBack.setOnClickListener {
            onBackPressed()
        }
        btnUsernameSetting.setOnClickListener {
            SettingActivity.startActivity(this)
        }
    }

    override fun onResume() {
        super.onResume()
        tvUsername.text = scbPref.getUsername()
    }

}