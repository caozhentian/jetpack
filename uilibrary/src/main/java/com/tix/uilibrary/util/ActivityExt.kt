package com.tix.uilibrary.util

import com.tix.uilibrary.activity.BaseActivity
import es.dmoral.toasty.Toasty

/**
作者：created by ztcao on 2018/7/26 11 : 16
 */
fun BaseActivity.showInfoToast(info:String){
    Toasty.info(this , info).show()
}