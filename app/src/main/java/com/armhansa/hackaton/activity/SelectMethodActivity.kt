package com.armhansa.hackaton.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import com.armhansa.hackaton.extension.makeToast
import kotlinx.android.synthetic.main.activity_select_method.*
import kotlinx.android.synthetic.main.activity_setting_name.*

class SelectMethodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_method)

        btnAroundMe.setOnClickListener {
            makeToast("Test", Toast.LENGTH_LONG)
            AroundMeActivity.startActivity(this)
        }
    }

    fun setView() {
        btnBackSelectMethod.setOnClickListener {
            onBackPressed()
        }
    }

}