package com.tix.jetpack.activity.login

import com.tix.jetpack.R
import com.tix.uilibrary.activity.BaseSingleFragmentActivity

/**
作者：created by ztcao on 2018/8/29 14 : 19
 */
class LoginActivity:BaseSingleFragmentActivity(){

   override fun createFragment():LoginFragment {
        return LoginFragment()
    }
    override fun getTitleStrRes() = R.string.login
    override fun isShowNav() = false
}