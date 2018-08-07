package com.tix.jetpack.activity

import android.support.v4.app.FragmentActivity
import com.tix.jetpack.R
import com.tix.uilibrary.viewmodel.BaseViewModel
import com.tix.uilibrary.fragment.BaseStateFragment
import com.tix.uilibrary.util.getViewModel
import kotlinx.android.synthetic.main.frag_single_test_layout.*

/**
作者：created by ztcao on 2018/7/24 17 : 04
 */
class TestFragment : BaseStateFragment<String>() {
    override fun getBaseViewModel(): BaseViewModel<String> {
        var testViewModel = getViewModel<TestViewModel>(context as FragmentActivity)
        testViewModel.name = "ViewModel 子类覆盖实现"
        return testViewModel
    }

    override fun displayData(data: String?) {
        textView.text = data
    }

    override fun getStateLayoutContent() = R.layout.frag_single_test_layout



}