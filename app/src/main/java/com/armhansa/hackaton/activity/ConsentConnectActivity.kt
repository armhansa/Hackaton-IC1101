package com.armhansa.hackaton.activity

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import com.armhansa.hackaton.extension.makeToast
import kotlinx.android.synthetic.main.activity_consent_connect.*

class ConsentConnectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consent_connect)

        setView()
    }

    private fun setView() {
        btnBackConsentConnect.setOnClickListener {
            finish()
        }
        btnCancelConnect.setOnClickListener {
            finish()
        }
        btnAcceptConnect.setOnClickListener {
            makeToast("ยอมรับให้ armhansa โอนเงินแล้ว", Toast.LENGTH_LONG)
            Handler().postDelayed({
                finish()
            }, 3000)
        }
    }
}
