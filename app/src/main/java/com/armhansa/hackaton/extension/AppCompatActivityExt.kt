package com.armhansa.hackaton.extension

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.makeToast(message: String?, range: Int) {
    Toast.makeText(this, message, range).show()
}