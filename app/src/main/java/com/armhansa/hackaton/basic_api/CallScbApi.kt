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
        println("ningnananoii")
        val ex = TokenBody()
        SCBManager().createService().getToken(exampleData = ex).enqueue(object :
            Callback<TokenModel> {

            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                println("ningnananoii > FAILED ! $t")
                listener.toastError(t)
                listener.setLoading(false)
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                println("ningnananoii > have data")
                println("ningnananoii > " + response.body())
                println("ningnanaoii > " + ex)
                response.body()?.apply {
                    println("ningnananoii > have data2$this")
                    apiDeeplinkTeansaction(this,100,accountTo)
                }
            }

        })

        println("ningnananoii end")
    }
    fun apiDeeplinkTeansaction(token: TokenModel, amount:Int, accountTo:String){
        val billPayment = BillPaymentModel(amount,accountTo = accountTo)
        val bodyDeeplink = DeeplinkTransactionBody(billPayment = billPayment,sessionValidUntil = "",sessionValidityPeriod = SESSION_TIME)
        val scbManager = SCBManager()
        println("ningnananoii > go to depplink"+ token)
        val accessToken: String
        if(token.data!!.accessToken.isNotEmpty()){
            accessToken = token.data!!.accessToken
            println("ningnananoii > have access token"+ accessToken)
            scbManager.setHeaderDeeplinkTransactionService(accessToken)
            println("ningnananoii > ::"+scbManager.toString())
        }
        scbManager.createDeeplinkTransactionService().createsTransaction(body = bodyDeeplink).enqueue(object :
            Callback<DeeplinkTransactionModel> {
            override fun onFailure(call: Call<DeeplinkTransactionModel>, t: Throwable) {
                println("ningnananoii > deeplink FAILED ! $t")
                listener.toastError(t)
                listener.setLoading(false)
            }

            override fun onResponse(call: Call<DeeplinkTransactionModel>, response: Response<DeeplinkTransactionModel>) {
                println("ningnananoii > depplinkb have data ")
                println("ningnanaoii > " + response)
                response.body()?.data?.run {
                    println("ningnananoii > deeplink have data2 ::$this")
                    listener.callbackDeeplink(deeplinkUrl)
                }
                listener.setLoading(false)
            }
        })
    }

}