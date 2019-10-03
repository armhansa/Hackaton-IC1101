package com.armhansa.hackaton.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import com.armhansa.hackaton.extension.makeToast
import kotlinx.android.synthetic.main.activity_select_method.*

class SelectMethodActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context?) {
            context?.run {
                startActivity(Intent(this, SelectMethodActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_method)

        btnAroundMe.setOnClickListener {
            makeToast("Test", Toast.LENGTH_LONG)
            AroundMeActivity.startActivity(this)
        }
    }

}