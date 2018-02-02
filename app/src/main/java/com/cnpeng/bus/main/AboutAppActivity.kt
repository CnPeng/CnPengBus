package com.cnpeng.bus.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.MotionEvent.*
import com.cnpeng.bus.R
import com.cnpeng.bus.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_about_app.*

/**
 * 作者：CnPeng

 * 时间：2018/2/2:上午9:37

 * 说明："关于" 界面
 */
class AboutAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about_app)

        initView()
    }

    private fun initView() {
        tv_appAndVersionName.text = CommonUtils.getAppNameAndVersionName(this)
        iv_back_about.setOnClickListener({ finish() })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //TODO 重写触摸事件，右划关闭界面

        when (event!!.action) {
            ACTION_DOWN -> {

            }

            ACTION_MOVE -> {
                //TODO dialog提示是否关闭页面，并询问下次右划是否直接关闭页面
            }
            ACTION_UP -> {

            }
        }
        return super.onTouchEvent(event)
    }
}