package com.tix.jetpack.activity

import com.tix.uilibrary.activity.BaseViewModel
import io.reactivex.Observable

/**
作者：created by ztcao on 2018/7/25 11 : 26
 */
class TestViewModel:BaseViewModel<String>() {

    var name:String = ""
    override fun getObservableData(): Observable<String> {
       return  Observable.just(name)
    }
}