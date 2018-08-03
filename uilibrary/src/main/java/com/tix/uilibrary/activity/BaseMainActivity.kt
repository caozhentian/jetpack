package com.tix.uilibrary.activity

import android.os.Bundle
import android.support.annotation.MenuRes
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.KeyEvent
import android.view.MenuItem
import com.tix.uilibrary.R
import com.tix.uilibrary.adapter.SimpleFragmentPagerAdapter2
import com.tix.uilibrary.fragment.BaseFragment
import com.tix.uilibrary.util.AppActivityManager
import com.tix.uilibrary.util.showInfoToast
import kotlinx.android.synthetic.main.act_main_layout.*

/**
 * 支持底部导航栏 --- 主页面
作者：created by ztcao on 2018/7/25 17 : 36
 */
open abstract class BaseMainActivity:BaseActivity() {
    private var  firstTime:Long = 0
    override fun getPlaceholderLayout() = R.layout.act_main_layout

    override fun initUIEvent(savedInstanceState: Bundle?) {
        super.initUIEvent(savedInstanceState)
        var fragments = getFragments()
        var fragmentPagerAdapter : FragmentPagerAdapter = SimpleFragmentPagerAdapter2<BaseFragment>(supportFragmentManager ,fragments )
        viewPaper.adapter = fragmentPagerAdapter
        viewPaper.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                var menuItem: MenuItem? = null
                if (menuItem != null) {
                    menuItem.isChecked = false
                } else {
                    navigation.menu.getItem(0).isChecked = false
                }
                menuItem = navigation.menu.getItem(position)
                menuItem.isChecked = true
            }
        })
        navigation.inflateMenu(getBottomMenus())
        if(fragments.size !=  navigation.menu.size()){
            throw IllegalArgumentException("fragment和导航栏参数数必须相等")
        }
        navigation.setOnNavigationItemSelectedListener({
            var menus = navigation.menu
            for(index in 0..fragments.size){
                if(menus.getItem(index) == it ){
                    viewPaper.currentItem = index
                    break
                }
            }
            true
        })
    }
    //生成一组Fragment
    abstract fun getFragments():List<BaseFragment>
    //生成一个底部导航菜单
    @MenuRes
    abstract fun getBottomMenus(): Int

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        var secondTime :Long = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ( secondTime - firstTime < 2000) {
                AppActivityManager.finishAll()
            } else {
                showInfoToast(getString(R.string.retry_quick))
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event)
    }
}