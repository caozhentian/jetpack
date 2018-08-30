package com.tix.uilibrary.model

/**
作者：created by ztcao on 2018/8/6 17 : 41
 */
class PageEntity<T> {

    val pageIndex: Int = 0// 当前页数
    val pageSize: Int = 0// 一共的页数
    val count: Int = 0// 数据条数
    val pageCount: Int = 0// 每页的数据条数
    var data :MutableList<T> = arrayListOf<T>()

    fun isLastPage():Boolean{
        return false
    }
}