package com.loginapi

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object Singleton {
     private var context: Context? = null

    internal fun getAppContext(): Context? {
        return context
    }
}