package com.cnpeng.bus.main

import android.app.Application
import android.content.Context

/**
 * 作者：CnPeng

 * 时间：2018/2/2:上午10:57

 * 说明：
 */
class CusApplication : Application() {
    lateinit var mContext: Context

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
    }
}