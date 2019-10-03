package com.armhansa.hackaton.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.armhansa.hackaton.R
import kotlinx.android.synthetic.main.activity_add_friend.*

class AddFriendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)

        setView()
    }

    private fun setView() {
        intent.data?.getQueryParameter("user")?.let {
            if (it.contains('?')) {
                tvUsername.text = it.split('?')[0]
            } else {
                tvUsername.text = it
            }

        }
    }

}
