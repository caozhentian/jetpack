package com.tix.uilibrary.viewmodel

import com.tix.uilibrary.model.PageEntity
import com.tix.uilibrary.model.PageInfo

/**
作者：created by ztcao on 2018/8/6 17 : 29
 */
abstract class PageViewModel<T>: BaseViewModel<PageEntity<T>>() {
    var nextPage : Int = 0
    var pageSize : Int = PageInfo.PAGE_DEFAULT_SIZE
    var keyword:   String = ""
    //总的数据
    var totalData = arrayListOf<T>()
}