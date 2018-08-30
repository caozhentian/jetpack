package com.tix.uilibrary.application

import android.app.Application
import com.tix.net.RetrofitManager
import org.greenrobot.eventbus.EventBus

/**
作者：created by ztcao on 2018/5/7 16 : 12
 */
abstract class BaseAppApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        baseAppApplication = this
        //初始化公共库
        initRetrofitManager()
        initLogInfo()
        initPush()
        //EventBus.getDefault().register(this)
    }
    override fun onTerminate() {
        super.onTerminate()
        //EventBus.getDefault().unregister(this)
    }

    protected open fun initRetrofitManager() {

    }

    private fun initLogInfo(){

    }

    private fun initPush(){

    }

//    //处理用户其它设备登录问题
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onTokenExpiresMessage(event: TokenExpireEvent) = dealTokenExpiresMessage()

    abstract fun dealTokenExpiresMessage()

    companion object {
        lateinit var baseAppApplication :BaseAppApplication
    }

}