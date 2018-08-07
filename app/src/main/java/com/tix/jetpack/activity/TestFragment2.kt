package com.tix.jetpack.activity

import android.support.v4.app.FragmentActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tix.uilibrary.fragment.BaseListFrament
import com.tix.uilibrary.util.getViewModel
import com.tix.uilibrary.viewmodel.PageViewModel

/**
作者：created by ztcao on 2018/7/24 17 : 04
 */
class TestFragment2 : BaseListFrament<String>() {
    override fun getPageViewModel(): PageViewModel<String> {
        return getViewModel<TestPageViewModel>(context as FragmentActivity)
    }

    override fun initAdater(): BaseQuickAdapter<String, BaseViewHolder> {
        return TestQuickAdapter(getData())
    }
}