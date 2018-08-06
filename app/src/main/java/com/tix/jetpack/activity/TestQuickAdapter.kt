package com.tix.jetpack.activity

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tix.jetpack.R

/**
作者：created by ztcao on 2018/8/6 16 : 17
 */
class TestQuickAdapter(datas:List<String>):BaseQuickAdapter<String , BaseViewHolder>(R.layout.item_test2,datas) {
    override fun convert(helper: BaseViewHolder, item: String) {
        var view = helper.getView<TextView>(R.id.tv_title)
        view.text ="第${item}个元素"
    }
}