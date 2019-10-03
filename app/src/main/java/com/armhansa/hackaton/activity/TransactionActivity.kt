package com.armhansa.hackaton.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armhansa.hackaton.R
import kotlinx.android.synthetic.main.activity_transaction.*

class TransactionActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context?) {
            context?.run {
                startActivity(Intent(this, TransactionActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        btnSelectMethod.setOnClickListener {
            SelectMethodActivity.startActivity(this)
        }

    }
}
