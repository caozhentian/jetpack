package com.tix.jetpack.activity.main

import android.support.v4.app.FragmentActivity
import com.tix.jetpack.R
import com.tix.jetpack.model.HomeData
import com.tix.uilibrary.fragment.BaseStateFragment
import com.tix.uilibrary.util.getViewModel
import com.tix.uilibrary.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.frag_home_layout.*

/**
作者：created by ztcao on 2018/7/24 17 : 04
 */
class HomeFragment : BaseStateFragment<HomeData>() {

    override fun getBaseViewModel(): BaseViewModel<HomeData> {
        return getViewModel<HomeViewModel>(context as FragmentActivity)
    }

    override fun displayData(data: HomeData?) {
        textView.text = data?.unReadNumber.toString()
    }

    override fun getStateLayoutContent() = R.layout.frag_home_layout

}