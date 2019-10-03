package com.armhansa.hackaton.data

data class DeeplinkTransactionBody(
    val transactionType: String = "PURCHASE",
    val transactionSubType: List<String> = arrayListOf("BP"),
    val sessionValidityPeriod: Int,
    val sessionValidUntil: String,
    val billPayment: BillPaymentModel
) {

}