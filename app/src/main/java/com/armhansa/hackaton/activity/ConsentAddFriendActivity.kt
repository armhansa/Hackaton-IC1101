package com.armhansa.hackaton.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armhansa.hackaton.R
import kotlinx.android.synthetic.main.activity_around_me.*
import kotlinx.android.synthetic.main.activity_consent_add_friend.*

class ConsentAddFriendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consent_add_friend)
        setView()
    }
    fun setView() {
        btnBackConsentAddFriend.setOnClickListener {
            onBackPressed()
        }
    }
}
