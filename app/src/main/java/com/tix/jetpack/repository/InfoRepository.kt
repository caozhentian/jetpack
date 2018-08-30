package com.tix.jetpack.repository

import com.google.gson.reflect.TypeToken
import com.tix.jetpack.config.INFO_LIST_URL
import com.tix.jetpack.model.Information
import com.tix.net.RetrofitManager
import com.tix.uilibrary.model.PageEntity
import io.reactivex.Observable

/**
作者：created by ztcao on 2018/8/30 10 : 19
 */
object InfoRepository {

    fun getInfoList(params:Map<String , String>):Observable<PageEntity<Information>>{
        val type = object : TypeToken<MutableList<Information>>() {}.type
        return RetrofitManager.getInstance().post<MutableList<Information>>(INFO_LIST_URL ,params , type)
                .map { it ->
                    var pageEntity = PageEntity<Information>()
                    pageEntity.data = it
                    pageEntity
                }
    }
}