package com.tix.jetpack.activity

import com.tix.jetpack.R
import com.tix.uilibrary.activity.BaseViewModel
import com.tix.uilibrary.fragment.BaseStateFragment
import com.tix.uilibrary.util.getViewModel
import kotlinx.android.synthetic.main.frag_single_test_layout.*

/**
作者：created by ztcao on 2018/7/24 17 : 04
 */
class TestFragment : BaseStateFragment<String>() {
    override fun getBaseViewModel(): BaseViewModel<String> {
        return getViewModel<TestViewModel>(baseActivity)
    }

    override fun displayData(data: String?) {
        textView.text = data?:""
    }

    override fun getStateLayoutContent() = R.layout.frag_single_test_layout



}