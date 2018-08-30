package com.tix.jetpack.activity.main

import com.tix.jetpack.model.HomeData
import com.tix.jetpack.repository.HomeRepository
import com.tix.uilibrary.viewmodel.BaseViewModel
import io.reactivex.Observable

/**
作者：created by ztcao on 2018/8/29 15 : 37
 */
class HomeViewModel:BaseViewModel<HomeData>() {

    override fun getMainObservableData(): Observable<HomeData> {
        return HomeRepository.getHomeData()
    }
}