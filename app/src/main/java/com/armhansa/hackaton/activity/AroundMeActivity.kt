package com.armhansa.hackaton.activity

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.armhansa.hackaton.R
import com.armhansa.hackaton.adapter.BluetoothDeviceAdapter
import com.armhansa.hackaton.basic_api.CallScbApi
import com.armhansa.hackaton.constant.REQUEST_ENABLE_BLUETOOTH
import com.armhansa.hackaton.constant.TAG
import com.armhansa.hackaton.extension.makeToast
import com.armhansa.hackaton.listener.OnBluetoothDeviceItemClick
import com.armhansa.hackaton.listener.OnCallbackScbApi
import kotlinx.android.synthetic.main.activity_around_me.*

class AroundMeActivity : AppCompatActivity(), OnBluetoothDeviceItemClick, OnCallbackScbApi {

    private val bluetoothRvAdapter: BluetoothDeviceAdapter by lazy { BluetoothDeviceAdapter(this) }
    private val bluetoothAdapter: BluetoothAdapter by lazy { BluetoothAdapter.getDefaultAdapter() }
    private lateinit var mReceiver: BroadcastReceiver

    private var usernameClicked = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_around_me)

        setView()
    }

    private fun setView() {
        checkBluetooth()
        rvBluetoothDevice.run {
            adapter = bluetoothRvAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
        }
        pullToRefresh.setOnRefreshListener {
            discoverBluetoothDevice()
        }
    }

    private fun discoverBluetoothDevice() {
        mReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, intent: Intent?) {
                when (intent?.action) {
                    BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                        makeToast("Started", Toast.LENGTH_SHORT)
//                        pbScanning.isVisible = true
                        bluetoothRvAdapter.reset()

                        pullToRefresh.isRefreshing = true
                    }
                    BluetoothDevice.ACTION_FOUND -> {
                        val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                        device?.let {
                            it.name?.let { name ->
                                if (name.startsWith("szb_") && bluetoothRvAdapter.isNew(name.toLowerCase())) {
                                    bluetoothRvAdapter.addBluetoothDevice(
                                        name.toLowerCase().slice(
                                            IntRange(4, name.count() - 1)
                                        )
                                    )
                                }
                            }
                        }
                    }
                    BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                        pullToRefresh.isRefreshing = false
                    }
                }
            }
        }

        val filter = IntentFilter()
        filter.addAction(BluetoothDevice.ACTION_FOUND)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        registerReceiver(mReceiver, filter)
        bluetoothAdapter.startDiscovery()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        try {
            bluetoothAdapter.cancelDiscovery()
            unregisterReceiver(mReceiver)
        } catch (t: Throwable) {
            Log.d(TAG, t.message.toString())
        }
        super.onDestroy()
    }

    override fun onClickBluetoothItem(btDeviceUsername: String) {
        usernameClicked = btDeviceUsername
        val accountTo = "524264469873048"
        makeToast("accountTo is $accountTo", Toast.LENGTH_LONG)

        pullToRefresh.isRefreshing = true
        CallScbApi(this).getToken(accountTo)
    }

    override fun toastError(throwable: Throwable) {
        makeToast(throwable.message, Toast.LENGTH_SHORT)
    }

    override fun callbackDeeplink(deepLink: String?) {
        gotoDeepLink(deepLink)
    }

    override fun setLoading(isLoading: Boolean) {
        pullToRefresh.isRefreshing = isLoading
    }

    private fun gotoDeepLink(deepLink: String?) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("$deepLink?callback_url=findpay://callback?user=$usernameClicked")))
    }

    private fun checkBluetooth() {
        // Get the default adapter
        if (bluetoothAdapter == null) {
            alertDialog("Sorry, This device doesn't support bluetooth.")
        } else if (!bluetoothAdapter?.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent,
                REQUEST_ENABLE_BLUETOOTH
            )
        } else {
            discoverBluetoothDevice()
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
                discoverBluetoothDevice()
            } else if (resultCode == RESULT_CANCELED) {
                makeToast("Sorry Please open bluetooth for use!", Toast.LENGTH_LONG)
                Handler().postDelayed({ finish() }, 5000)
            }
        }
    }

}
