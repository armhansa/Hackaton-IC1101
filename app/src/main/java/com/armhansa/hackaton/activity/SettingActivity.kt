package com.armhansa.hackaton.activity

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import com.armhansa.hackaton.extension.makeToast
import com.armhansa.hackaton.util.SCBPreference
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_setting.etUsername

class SettingActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_DISCOVERING_DEVICE = 2711
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SettingActivity::class.java))
        }
    }

    private val scbPref by lazy { SCBPreference(this) }
    private val bluetoothAdapter: BluetoothAdapter by lazy { BluetoothAdapter.getDefaultAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        setView()
    }

    private fun setView() {
        val oldUsername = scbPref.getUsername()
        if (oldUsername != "default") {
            etUsername.hint = oldUsername
        }
        btnChangeUsername.setOnClickListener {
            if (etUsername.text.toString().isNotEmpty()) {
                scbPref.saveUsername(etUsername.text.toString())
                scbPref.saveNotFirstTime()
                onBackPressed()
            } else {
                makeToast("Please insert username before!", Toast.LENGTH_LONG)
            }
        }
        bluetoothAdapter.run {
            switchVisible.isChecked = isDiscovering
        }
        switchVisible.setOnCheckedChangeListener { _, b -> // compoundButton, b
            val isDiscovering = bluetoothAdapter.isDiscovering
            if (b) {
                bluetoothAdapter.apply {
                    if (scbPref.getOldBtName() == null) {
                        scbPref.saveOldBtName(name)
                    }
                    val username = scbPref.getUsername()
                    name = "szb_$username"
                    if (this.isDiscovering.not()) {
                        startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
                            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600)
                        }, REQUEST_DISCOVERING_DEVICE)
                        makeToast("Making Device Discoverable", Toast.LENGTH_SHORT)
                    }
                }
            } else {
                bluetoothAdapter.name = scbPref.getOldBtName()
            }
            bluetoothAdapter.run {
                Log.d("armhansa=>", "Discovering: $isDiscovering -> ${this.isDiscovering}")
            }
            makeToast(bluetoothAdapter.name, Toast.LENGTH_SHORT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DISCOVERING_DEVICE) {
            if (resultCode != 0) {
                makeToast("Open Successful", Toast.LENGTH_SHORT)
            } else {
                makeToast("Open Failed", Toast.LENGTH_SHORT)
                switchVisible.isChecked = false
            }
        }
    }

}
