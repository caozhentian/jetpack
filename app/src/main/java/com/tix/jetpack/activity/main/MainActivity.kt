package com.tix.jetpack.activity.main

import com.tix.jetpack.R
import com.tix.uilibrary.activity.BaseMainActivity
import com.tix.uilibrary.fragment.BaseFragment

/**
作者：created by ztcao on 2018/7/26 12 : 00
 */
class MainActivity:BaseMainActivity() {
    override fun getFragments(): List<BaseFragment> {
        var fragments = arrayListOf<BaseFragment>()
        fragments.add(HomeFragment())
        fragments.add(InfoListFragment())
        fragments.add(MineFragment())
        return fragments
    }

    //底部导航栏
    override fun getBottomMenus() = R.menu.action_navigation
}