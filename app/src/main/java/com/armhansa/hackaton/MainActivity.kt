package com.armhansa.hackaton

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.activity.FindDeviceActivity
import com.armhansa.hackaton.activity.RegisterActivity
import com.armhansa.hackaton.activity.SettingActivity
import com.armhansa.hackaton.extension.makeToast
import com.armhansa.hackaton.util.SCBPreference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "armhansa=>"
        const val REQUEST_ENABLE_BLUETOOTH = 2403
    }

    private val scbPref by lazy { SCBPreference(this) }
    private val bluetoothAdapter: BluetoothAdapter? by lazy { BluetoothAdapter.getDefaultAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkBluetooth()

        setView()
    }

    private fun setView() {
        btnSetting.setOnClickListener {
            if (scbPref.isFirstTime()) {
                RegisterActivity.startActivity(this)
            } else {
                SettingActivity.startActivity(this)
            }
        }
        btnTransfer.setOnClickListener {
            FindDeviceActivity.startActivity(this)
        }
    }

    private fun checkBluetooth() {
        // Get the default adapter
        if (bluetoothAdapter == null) {
            alertDialog("Sorry, This device doesn't support bluetooth.")
        }
        if (bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BLUETOOTH)
        }
    }

    private fun alertDialog(message: String) {
        AlertDialog.Builder(this).apply {
            setCancelable(false)
            setMessage(message)
            setPositiveButton("OK") { _, _ ->
                finish()
            }
            val dialog = this.create()
            dialog.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            if (resultCode == Activity.RESULT_OK) {
                makeToast("Bluetooth has opened", Toast.LENGTH_LONG)
            } else if (resultCode == RESULT_CANCELED) {
                makeToast("Sorry Please open bluetooth for use!", Toast.LENGTH_LONG)
                Handler().postDelayed({ finish() }, 5000)
            }
        }
    }

}
