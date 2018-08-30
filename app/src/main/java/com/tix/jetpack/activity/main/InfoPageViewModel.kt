package com.tix.jetpack.activity.main

import com.tix.jetpack.model.Information
import com.tix.jetpack.repository.InfoRepository
import com.tix.uilibrary.model.PageEntity
import com.tix.uilibrary.model.PageInfo
import com.tix.uilibrary.viewmodel.PageViewModel
import io.reactivex.Observable

/**
作者：created by ztcao on 2018/8/30 10 : 08
 */
class InfoPageViewModel:PageViewModel<Information>() {
    override fun getMainObservableData(): Observable<PageEntity<Information>> {
        var params =  mapOf(PageInfo.NEXT_PAGE_KEY to nextPage.toString() ,
                PageInfo.PAGE_SIZE_KEY to pageSize.toString())
        return InfoRepository.getInfoList(params)
    }
}