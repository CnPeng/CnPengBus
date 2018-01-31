package com.cnpeng.bus

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebSettings
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

/**
 *  CnPeng 180131
 *  确定基色
 *  线路选择：1、右上角按钮    2、侧滑界面中条目   3、底部悬浮按钮
 *
 *
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        var url = """http://chiping.weixin4bus.com:4001/LineServer/WeiXin!getStation.action?lineCode=1&lineName=1路"""
        webView.loadUrl(url)


        val webSettings: WebSettings = webView.settings;
        webSettings.javaScriptEnabled = true    //不启用不展示小汽车
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
//            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
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
// R.id.nav_navigation -> {
//
// }
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
