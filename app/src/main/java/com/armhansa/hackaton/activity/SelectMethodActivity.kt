package com.armhansa.hackaton.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import kotlinx.android.synthetic.main.activity_select_method.*

class SelectMethodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_method)

        btnAroundMe.setOnClickListener {
            startActivity(
                Intent(this, AroundMeActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
            finish()
        }
    }

}