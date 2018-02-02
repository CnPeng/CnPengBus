package com.cnpeng.bus.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cnpeng.bus.R
import kotlinx.android.synthetic.main.activity_suggest.*

/**
 * 作者：CnPeng

 * 时间：2018/2/2:下午3:01

 * 说明：意见与建议界面
 */
class SuggestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggest)

        iv_back_suggest.setOnClickListener({ finish() })
    }
}