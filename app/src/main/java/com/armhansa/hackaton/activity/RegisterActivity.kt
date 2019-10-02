package com.armhansa.hackaton.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.armhansa.hackaton.R
import com.armhansa.hackaton.extension.makeToast
import com.armhansa.hackaton.util.SCBPreference
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.etUsername

class RegisterActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    private val scbPref by lazy { SCBPreference(this) }
    private var isFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setView()
    }

    private fun setView() {
        etUsername.hint = scbPref.getUsername()
        if (!scbPref.isFirstTime()) {
            btnRegister.text = "Change Username"
            isFinished = true
        }
        btnRegister.setOnClickListener {
            if (etUsername.text.toString().isNotEmpty()) {
                scbPref.saveUsername(etUsername.text.toString())
                scbPref.saveNotFirstTime()
                isFinished = true
                onBackPressed()
            } else {
                makeToast("Please insert username before!", Toast.LENGTH_LONG)
            }
        }
    }

    override fun onBackPressed() {
        if (isFinished) {
            super.onBackPressed()
        }
    }

}
