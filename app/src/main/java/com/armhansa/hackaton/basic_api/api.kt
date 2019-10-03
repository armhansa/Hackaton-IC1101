package com.example.myapplication.basic_api

import com.example.myapplication.basic_api.data.*
import com.example.myapplication.basic_api.service.SCBManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class api {
    fun testApi(accountTo: String) {
        var token: TokenModel
        println("ningnananoii")
        val ex = TokenBody()
        SCBManager().createService().getToken(exampleData = ex).enqueue(object :
            Callback<TokenModel> {

            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                println("ningnananoii > FAILED ! $t")
            }

            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                println("ningnananoii > have data")
                println("ningnanaoii > " + response.body())
                println("ningnanaoii > " + ex)
                response.body()?.apply {
                    token = this

                    println("ningnananoii > have data2"+ token)
                    apiDeeplinkTeansaction(token,100,accountTo)
                }


            }

        })

        println("ningnananoii end")
    }
    fun apiDeeplinkTeansaction(token: TokenModel, amount:Int, accountTo:String){
        val billPayment = BillPaymentModel(amount,accountTo = accountTo)
        val bodyDeeplink = DeeplinkTransactionBody(billPayment = billPayment,sessionValidUntil = "",sessionValidityPeriod = SESSION_TIME);
        var deeplink: DeeplinkTransactionModel
        var scbManager: SCBManager = SCBManager()
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

            }

            override fun onResponse(call: Call<DeeplinkTransactionModel>, response: Response<DeeplinkTransactionModel>) {
                println("ningnananoii > depplinkb have data ")
                println("ningnanaoii > " + response)
                response.body()?.apply {
                    deeplink = this
                    println("ningnananoii > deeplink have data2 ::"+deeplink)
                }


            }

        })

    }
}