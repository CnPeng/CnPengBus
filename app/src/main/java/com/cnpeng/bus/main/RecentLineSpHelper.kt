package com.cnpeng.bus.main

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * 作者：CnPeng

 * 时间：2018/2/1:上午8:51

 * 说明：存储用户近期使用的线路信息
 */
class RecentLineSpHelper(mContext: Context) {

    private var mSp: SharedPreferences = mContext.getSharedPreferences("recentLine", MODE_PRIVATE)
    private val KEY_LATEST_LINE: String = "latestLineNum"

    /**
     * 存储最后一条线路信息
     */
    fun saveLatestLine(lineNum: String) {
        val edit: SharedPreferences.Editor = mSp.edit()
        edit.putString(KEY_LATEST_LINE, lineNum)
        edit.apply()
    }

    /**
     * 获取存储的最后一条信息信息，默认为1
     */
    fun getLatestLine(): String {
        return mSp.getString(KEY_LATEST_LINE, "1")
    }
}

