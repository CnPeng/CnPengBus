package com.cnpeng.bus.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebSettings
import com.cnpeng.bus.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 *  CnPeng 180131
 *
 *  确定基色:亮天蓝色
 *  线路选择：1、右上角按钮    2、侧滑界面中条目   3、底部悬浮按钮
 *  双击返回退出APP
 *  底部切换线路的按钮允许用户动态拖拽定义在左侧或者右侧，点击选线路，长按拖拽
 *  右上角改成收藏线路功能
 *
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mRecentLineSpHelper: RecentLineSpHelper = RecentLineSpHelper(this)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fab.setOnClickListener { view ->
        //    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //            .setAction("Action", null)
        //            .show()
        //}

        initDrawerLayout()
        val latestLineNum = initLineNum()
        initWebViewData(latestLineNum)
    }

    /**
     * 初始化线路数据。
     * 进入APP时初始化该数据--
     *      --进入APP时检测之前是否使用过，如果之前查询过，展示之前查询的页面。未使用过默认展示K1
     */
    private fun initLineNum(): String {
        return mRecentLineSpHelper.getLatestLine()
    }

    /**
     * 填充webView主体内容
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewData(lineNum: String) {
        webView.clearCache(true)

        val url = """http://chiping.weixin4bus.com:4001/LineServer/WeiXin!getStation.action?lineCode=$lineNum&lineName=${lineNum}路"""
        webView.loadUrl(url)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true    //不启用不展示小汽车
        webSettings.builtInZoomControls = true
    }

    /**
     *初始化侧拉界面
     */
    private fun initDrawerLayout() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.item_line1 -> {
                changeLineNum("1")
                return true
            }
            R.id.item_line2 -> {
                changeLineNum("2")
                return true
            }
            R.id.item_line3 -> {
                changeLineNum("3")
                return true
            }
            R.id.item_line4 -> {
                changeLineNum("4")
                return true
            }
            R.id.item_line5 -> {
                changeLineNum("5")
                return true
            }
            R.id.item_line6 -> {
                changeLineNum("6")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * 切换线路
     */
    private fun changeLineNum(lineNum: String) {
        mRecentLineSpHelper.saveLatestLine(lineNum)
        initWebViewData(lineNum)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_line_search -> {
                // Handle the camera action
            }
            R.id.nav_station_search -> {

            }
            R.id.nav_transfer_search -> {

            }
            R.id.nav_line_favorite -> {

            }
            R.id.nav_recent_search -> {

            }
            R.id.nav_about -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_suggestion -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}