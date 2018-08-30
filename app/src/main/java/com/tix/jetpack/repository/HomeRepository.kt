package com.tix.jetpack.repository

import com.tix.jetpack.config.HOME_URL
import com.tix.jetpack.model.HomeData
import com.tix.net.RetrofitManager
import io.reactivex.Observable

/**
作者：created by ztcao on 2018/8/29 15 : 40
 */
object HomeRepository {

    fun getHomeData():Observable<HomeData>{
        return RetrofitManager.getInstance().post<HomeData>(HOME_URL , mapOf(),HomeData::class.java)
    }
}