package com.example.myapplication.basic_api.data

class BillPaymentModel(
    val paymentAmount:Int,
    val accountTo: String,
    val ref1: String ="ABCDEFGHIJ1234567890",
    val ref2: String ="ABCDEFGHIJ1234567890",
    val ref3: String ="ABCDEFGHIJ1234567890"
) {
}