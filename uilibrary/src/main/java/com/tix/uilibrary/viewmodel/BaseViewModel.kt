package com.tix.uilibrary.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable

/**
 * ViewModel的基类
作者：created by ztcao on 2018/7/25 10 : 56
 */
abstract class BaseViewModel<T>:ViewModel() {

    // Create a Observable  with a T
    private var data: Observable<T>? = null
    //获取页面的最主要数据的Observable
    abstract fun  getMainObservableData():Observable<T>
}