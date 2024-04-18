package com.bansri.imageloader

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // initializing the picker library
        ImageLoader.init(
            this,
            BuildConfig.ACCESS_KEY, //"your access key"
            BuildConfig.SECRET_KEY, //"your secret key"
            10 //"page size"
            /* optional page size (number of photos per page) */
        )
            /* .setLoggingEnabled(true) // if you want to see the http requests */
    }
}
