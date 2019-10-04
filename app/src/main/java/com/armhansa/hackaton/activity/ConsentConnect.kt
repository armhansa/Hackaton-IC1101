package com.armhansa.hackaton.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armhansa.hackaton.R
import kotlinx.android.synthetic.main.activity_consent_add_friend.*
import kotlinx.android.synthetic.main.activity_consent_connect.*

class ConsentConnect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consent_connect)
        setView()
    }
    fun setView() {
        btnBackConsentConnect.setOnClickListener {
            onBackPressed()
        }
    }
}
