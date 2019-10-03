package com.armhansa.hackaton.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context?) {
            context?.run {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setView()
    }

    private fun setView() {
        btnMyTransaction.setOnClickListener {
            TransactionActivity.startActivity(this)
        }
        btnOtherMenu.setOnClickListener {
            startActivity(
                Intent(this, OtherMenuActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            )
        }

    }

}
