package com.tix.jetpack.activity.main

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tix.jetpack.R
import com.tix.jetpack.model.Information

/**
作者：created by ztcao on 2018/8/6 16 : 17
 */
class InfoQuickAdapter(datas:List<Information>):BaseQuickAdapter<Information , BaseViewHolder>(R.layout.item_info,datas) {
    override fun convert(helper: BaseViewHolder, item: Information) {
        var view = helper.getView<TextView>(R.id.tv_title)
        view.text ="第${helper.layoutPosition}个元素--- ${item.name}"
    }
}