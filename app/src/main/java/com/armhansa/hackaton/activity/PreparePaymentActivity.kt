package com.armhansa.hackaton.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.armhansa.hackaton.R
import com.armhansa.hackaton.constant.TAG
import com.armhansa.hackaton.data.ExampleData
import com.armhansa.hackaton.data.TokenModel
import com.armhansa.hackaton.extension.makeToast
import com.example.myapplication.basic_api.service.SCBManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreparePaymentActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, PreparePaymentActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prepare_payment)

        testApi()
    }

    private fun testApi() {
        val ex = ExampleData(
            "l7f031d768df40465ba05ae327022a5220",
            "8e25c09a6e0a4adeabfd6f50742c969d"
        )
        SCBManager().createService().getToken(exampleData = ex).enqueue(object : Callback<TokenModel> {
            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                makeToast("Error! Can not get token!", Toast.LENGTH_LONG)
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                makeToast("Response Successful", Toast.LENGTH_LONG)
                Log.d(TAG, response.body().toString())
                response.body()?.apply {
                    makeToast("Data is not empty", Toast.LENGTH_LONG)
                    this.data
                }
            }
        })
    }

    private fun gotoDeepLink(deepLink: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)))
    }

}
