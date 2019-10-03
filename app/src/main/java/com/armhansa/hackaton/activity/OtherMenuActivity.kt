package com.armhansa.hackaton.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import com.armhansa.hackaton.extension.makeToast
import kotlinx.android.synthetic.main.activity_other_menu.*

class OtherMenuActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context?) {
            context?.run {
                startActivity(Intent(this, OtherMenuActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_menu)

        btnSetting.setOnClickListener {
            makeToast("Test", Toast.LENGTH_LONG)
        }
        btnHome.setOnClickListener {
            startActivity(
                Intent(this, HomeActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }
    }

}
