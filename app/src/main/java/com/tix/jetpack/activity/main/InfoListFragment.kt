package com.tix.jetpack.activity.main

import android.support.v4.app.FragmentActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tix.jetpack.model.Information
import com.tix.uilibrary.fragment.BaseListFrament
import com.tix.uilibrary.util.getViewModel
import com.tix.uilibrary.viewmodel.PageViewModel

/**
作者：created by ztcao on 2018/7/24 17 : 04
 */
class InfoListFragment : BaseListFrament<Information>() {
    override fun getPageViewModel(): PageViewModel<Information> {
        return getViewModel<InfoPageViewModel>(context as FragmentActivity)
    }

    override fun initAdater(): BaseQuickAdapter<Information, BaseViewHolder> {
        return InfoQuickAdapter(getData())
    }
}