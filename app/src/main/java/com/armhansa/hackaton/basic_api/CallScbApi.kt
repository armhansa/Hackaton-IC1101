package com.armhansa.hackaton.basic_api

import com.armhansa.hackaton.listener.OnCallbackScbApi
import com.example.myapplication.basic_api.SESSION_TIME
import com.example.myapplication.basic_api.data.*
import com.example.myapplication.basic_api.service.SCBManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallScbApi(val listener: OnCallbackScbApi) {

    fun getToken(accountTo: String) {
        val ex = TokenBody()
        SCBManager().createService().getToken(exampleData = ex).enqueue(object :
            Callback<TokenModel> {

            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                listener.toastError(t)
                listener.setLoading(false)
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                response.body()?.apply {
                    apiDeeplinkTeansaction(this,100,accountTo)
                }
            }

        })

    }
    fun apiDeeplinkTeansaction(token: TokenModel, amount:Int, accountTo:String){
        val billPayment = BillPaymentModel(amount,accountTo = accountTo)
        val bodyDeeplink = DeeplinkTransactionBody(billPayment = billPayment,sessionValidUntil = "",sessionValidityPeriod = SESSION_TIME)
        val scbManager = SCBManager()
        val accessToken: String
        if(token.data!!.accessToken.isNotEmpty()){
            accessToken = token.data.accessToken
            scbManager.setHeaderDeeplinkTransactionService(accessToken)
        }
        scbManager.createDeeplinkTransactionService().createsTransaction(body = bodyDeeplink).enqueue(object :
            Callback<DeeplinkTransactionModel> {
            override fun onFailure(call: Call<DeeplinkTransactionModel>, t: Throwable) {
                listener.toastError(t)
                listener.setLoading(false)
            }

            override fun onResponse(call: Call<DeeplinkTransactionModel>, response: Response<DeeplinkTransactionModel>) {
                response.body()?.data?.run {
                    listener.callbackDeeplink(deeplinkUrl)
                }
                listener.setLoading(false)
            }
        })
    }

}