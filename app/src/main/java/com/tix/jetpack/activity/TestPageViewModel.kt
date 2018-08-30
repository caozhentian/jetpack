package com.tix.jetpack.activity

import com.tix.uilibrary.model.PageEntity
import com.tix.uilibrary.viewmodel.PageViewModel
import io.reactivex.Observable

/**
作者：created by ztcao on 2018/8/7 10 : 23
 */
class TestPageViewModel:PageViewModel<String>() {
    override fun getMainObservableData(): Observable<PageEntity<String>> {
        var data = arrayListOf("1" ,"2","3","4" ,"5","6","7" ,"8","9","10" ,"11","12","13","14")
        var pageEntity = PageEntity<String>()
        pageEntity.data = data
        return Observable.just(pageEntity)
    }
}