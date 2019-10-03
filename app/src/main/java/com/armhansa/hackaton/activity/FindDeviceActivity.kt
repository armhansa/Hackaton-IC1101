package com.armhansa.hackaton.activity

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.armhansa.hackaton.R
import com.armhansa.hackaton.adapter.BluetoothDeviceAdapter
import com.armhansa.hackaton.constant.TAG
import com.armhansa.hackaton.data.*
import com.armhansa.hackaton.extension.makeToast
import com.armhansa.hackaton.listener.OnBluetoothDeviceItemClick
import com.example.myapplication.basic_api.service.SCBManager
import kotlinx.android.synthetic.main.activity_find_device.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindDeviceActivity : AppCompatActivity(), OnBluetoothDeviceItemClick {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, FindDeviceActivity::class.java))
        }
    }

    private val bluetoothRvAdapter: BluetoothDeviceAdapter by lazy { BluetoothDeviceAdapter(this) }
    private val bluetoothAdapter: BluetoothAdapter by lazy { BluetoothAdapter.getDefaultAdapter() }
    private lateinit var mReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_device)

        setView()
    }

    private fun setView() {
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        discoverBluetoothDevice()
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
                        makeToast("Finished", Toast.LENGTH_SHORT)
//                        pbScanning.isVisible = false
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
        val accountTo = when (btDeviceUsername) {
            "vittaya" -> "863064180894994"
            "ningnoii" -> "524264469873048"
            "sandwish" -> "1100400881"
            "armhansa" -> "863064180894994"
            else -> "524264469873048"
        }
        makeToast("accountTo is $accountTo", Toast.LENGTH_LONG)

        getToken(accountTo)
    }

    private fun getToken(billerId: String) {
        var token: TokenModel
        val ex = ExampleData(
            "l7543ffd46de424db4814b1daa245e6fde",
            "0fd1dea27b5245d4a91996fbf94f9d09"
        )
        SCBManager().createService().getToken(exampleData = ex).enqueue(object : Callback<TokenModel> {
            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                makeToast("Error! Can not get token!", Toast.LENGTH_LONG)
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                makeToast("Response Successful", Toast.LENGTH_LONG)
                Log.d(TAG, response.body().toString())
                response.body()?.run {
                    makeToast("${this.data?.accessToken}", Toast.LENGTH_LONG)

                    apiDeeplinkTansaction(this, 500, billerId)
//                    Handler().postDelayed({ gotoDeepLink("https://www.google.co.th") }, 1000)
                }
            }
        })
    }

    fun apiDeeplinkTansaction(token: TokenModel, amount: Int, accountTo: String) {
        val billPayment = BillPaymentModel(amount, accountTo = accountTo)
        val bodyDeeplink =
            DeeplinkTransactionBody(billPayment = billPayment, sessionValidUntil = "", sessionValidityPeriod = 60);
        var deeplink: DeeplinkTransactionModel
        var scbManager: SCBManager = SCBManager()
        println("ningnananoii > go to depplink$token")
        val accessToken: String
        if (token.data!!.accessToken.isNotEmpty()) {
            accessToken = token.data.accessToken
            println("ningnananoii > have access token$accessToken")
            scbManager.setHeaderDeeplinkTransactionService(accessToken)
            println("ningnananoii > ::$scbManager")
        }
        scbManager.createDeeplinkTransactionService().createsTransaction(body = bodyDeeplink)
            .enqueue(object : Callback<DeeplinkTransactionModel> {

                override fun onFailure(call: Call<DeeplinkTransactionModel>, t: Throwable) {
                    println("ningnananoii > deeplink FAILED ! $t")

                }

                override fun onResponse(
                    call: Call<DeeplinkTransactionModel>,
                    response: Response<DeeplinkTransactionModel>
                ) {
                    println("ningnananoii > depplinkb have data ")
                    println("ningnanaoii > $response")
                    response.body()?.apply {
                        deeplink = this
                        println("ningnananoii > deeplink have data2 ::$deeplink")
                    }


                }

            })

    }

    private fun gotoDeepLink(deepLink: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)))
    }

}
