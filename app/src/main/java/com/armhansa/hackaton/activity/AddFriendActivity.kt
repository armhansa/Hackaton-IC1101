package com.armhansa.hackaton.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.armhansa.hackaton.R
import com.armhansa.hackaton.extension.makeToast
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
                tvUsername.text = getString(R.string.consent_add_friend, it.split('?')[0])
            } else {
                tvUsername.text = getString(R.string.consent_add_friend, it)
            }
        }
        btnBackConsentAddFriend.setOnClickListener {
            gotoHomeActivity()
        }
        btnCancelAddFriend.setOnClickListener {
            gotoHomeActivity()
        }
        btnAcceptAddFriend.setOnClickListener {
            makeToast("ได้${tvUsername.text}เรียบร้อย", Toast.LENGTH_LONG)
            Handler().postDelayed({
                gotoHomeActivity()
            }, 3000)
        }
    }

    private fun gotoHomeActivity() {
        HomeActivity.startActivity(this)
        finish()
    }

}
