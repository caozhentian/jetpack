package com.tix.uilibrary.model

import java.io.Serializable

/**
 * 分页信息
 * Created by ztcao on 2018/1/26.
 */
open class PageInfo(var nextPage: Int, var pageSize: Int = PAGE_DEFAULT_SIZE , var keyword :String ="") : Serializable {

    fun toMap():Map<String, String>{
        return mapOf(PageInfo.NEXT_PAGE_KEY to nextPage.toString() ,
                PageInfo.PAGE_SIZE_KEY to       pageSize.toString() ,
                KEY_WORD to keyword)

    }
    companion object {
        const val PAGE_DEFAULT_SIZE: Int = 10
        const val PAGE_DEFAULT_FIRST_INDEX: Int = 0

        const val PAGE_SIZE_KEY = "pageSize"
        const val NEXT_PAGE_KEY = "nextPage"
        const val KEY_WORD = "keyword"
    }
}