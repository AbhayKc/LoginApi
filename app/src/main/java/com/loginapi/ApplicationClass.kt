package com.loginapi

import android.annotation.SuppressLint
import android.app.Application


@SuppressLint("Registered")
class ApplicationClass: Application() {

    companion object {
        internal var token:String="33333xd"
        var instance: ApplicationClass? = null
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}

