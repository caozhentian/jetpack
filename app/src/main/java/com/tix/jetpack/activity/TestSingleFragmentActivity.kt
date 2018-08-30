package com.tix.jetpack.activity

import com.tix.jetpack.R
import com.tix.jetpack.activity.main.HomeFragment
import com.tix.uilibrary.activity.BaseSingleFragmentActivity

/**
作者：created by ztcao on 2018/7/24 17 : 04
 */
class TestSingleFragmentActivity:BaseSingleFragmentActivity() {
    override fun createFragment(): HomeFragment = HomeFragment()
    override fun getTitleStrRes() = R.string.sigle_fragment_test
}