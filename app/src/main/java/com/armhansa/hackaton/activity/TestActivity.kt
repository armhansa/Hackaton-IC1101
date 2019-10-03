package com.armhansa.hackaton.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import kotlinx.android.synthetic.main.activity_setting_name.*

class TestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_name)
        setView()

    }
    fun setView() {
        btnBack.setOnClickListener {
            onBackPressed()
        }
    }

}