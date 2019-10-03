package com.armhansa.hackaton.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armhansa.hackaton.R

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


    }
}
