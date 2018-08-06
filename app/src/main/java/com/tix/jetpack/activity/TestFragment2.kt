package com.tix.jetpack.activity

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tix.uilibrary.fragment.BaseListFrament
import com.tix.uilibrary.model.PageInfo
import io.reactivex.Observable

/**
作者：created by ztcao on 2018/7/24 17 : 04
 */
class TestFragment2 : BaseListFrament<String>() {
    override fun initAdater(): BaseQuickAdapter<String, BaseViewHolder> {
        return TestQuickAdapter(datas)
    }

    override fun getObservable(pageInfo: PageInfo): Observable<List<String>> {
       return Observable.just(arrayListOf("1" ,"2","3","4" ,"5","6","7" ,"8","9","10" ,"11","12","13","14"))
    }




}