package com.tix.uilibrary.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
作者：created by ztcao on 2018/7/4 14 : 17
 */
//获取fragment ViewModel
inline fun <reified T : ViewModel> getViewModel(fragment: Fragment):T{
    var viewModel = ViewModelProviders.of(fragment).get(T::class.java)
    return viewModel
}
//获取Activity ViewModel
inline fun <reified T : ViewModel> getViewModel(activity: FragmentActivity):T{
    var viewModel = ViewModelProviders.of(activity).get(T::class.java)
    return viewModel
}

