package com.armhansa.hackaton.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armhansa.hackaton.R
import kotlinx.android.synthetic.main.activity_profile_setting.*

class Profile_setting : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_setting)

        btnProfileSetting.setOnClickListener {
            SettingName.startActivity(this)
        }
    }


}
