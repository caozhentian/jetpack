package com.tix.jetpack.activity

import com.tix.jetpack.R
import com.tix.uilibrary.activity.BaseMainActivity
import com.tix.uilibrary.fragment.BaseFragment

/**
作者：created by ztcao on 2018/7/26 12 : 00
 */
class MainActivity:BaseMainActivity() {
    override fun getFragments(): List<BaseFragment> {
        var fragments = arrayListOf<BaseFragment>()
        fragments.add(TestFragment())
        fragments.add(TestFragment2())
        return fragments
    }

    override fun getBottomMenus() = R.menu.action_navigation
}