package com.tix.jetpack.app

import com.tix.jetpack.config.BASE_URL
import com.tix.jetpack.net.CommParamInterceptor
import com.tix.net.RetrofitManager
import com.tix.uilibrary.application.BaseAppApplication

/**
作者：created by ztcao on 2018/8/28 16 : 15
 */
class AppApplication:BaseAppApplication() {

    override fun initRetrofitManager() {
        super.initRetrofitManager()
        RetrofitManager.init(BASE_URL ,arrayListOf(CommParamInterceptor()))
    }
    override fun dealTokenExpiresMessage() {

    }
}