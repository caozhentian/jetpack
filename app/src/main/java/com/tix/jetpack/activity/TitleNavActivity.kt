package com.tix.jetpack.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.tix.jetpack.R
import com.tix.uilibrary.activity.BaseActivity

/**
作者：created by ztcao on 2018/7/24 11 : 12
 */
class TitleNavActivity :BaseActivity(){
    override fun getPlaceholderLayout() = R.layout.title_nav_layout
    override fun getTitleStrRes() = R.string.title_nav
    override fun isShowNav() = false

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // 绑定toobar跟menu
        menuInflater.inflate(R.menu.menu_title_nav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_home -> {
            // User chose the "Settings" item, show the app settings UI...
            Toast.makeText(this , "主页",Toast.LENGTH_LONG).show()
            //setActionMenu(R.menu.menu_title_nav1)
            var intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            true
        }

        R.id.action_mine -> {
            //Toast.makeText(this , "我的",Toast.LENGTH_LONG).show()
            //setActionMenu(R.menu.menu_title_nav)
            var intent = Intent(this , TestSingleFragmentActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}