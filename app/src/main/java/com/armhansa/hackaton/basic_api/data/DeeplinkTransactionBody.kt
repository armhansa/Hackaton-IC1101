package com.example.myapplication.basic_api.data

import com.example.myapplication.basic_api.TRANSACTION_TYPE
import com.google.gson.JsonObject

data class DeeplinkTransactionBody (
    val transactionType : String = TRANSACTION_TYPE,
    val transactionSubType: List<String> = arrayListOf("BP"),
    val sessionValidityPeriod: Int,
    val sessionValidUntil: String,
    val billPayment:BillPaymentModel
//    val creditCardFullAmount: CreditCardFullAmountModel
) {

}