package com.armhansa.hackaton.listener

interface OnCallbackScbApi {
    fun toastError(throwable: Throwable)
    fun callbackDeeplink(deepLink: String?)
    fun setLoading(isLoading: Boolean)
}