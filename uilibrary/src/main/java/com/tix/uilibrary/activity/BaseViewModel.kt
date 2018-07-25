package com.tix.uilibrary.activity

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable

/**
 * ViewModel的基类
作者：created by ztcao on 2018/7/25 10 : 56
 */
abstract class BaseViewModel<T>:ViewModel() {

    // Create a Observable  with a T
    private var data: Observable<T>? = null
    abstract fun  getObservableData():Observable<T>
}