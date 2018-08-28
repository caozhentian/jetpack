package com.tix.jetpack.activity

import android.support.v4.app.FragmentActivity
import com.tix.jetpack.R
import com.tix.uilibrary.viewmodel.BaseViewModel
import com.tix.uilibrary.fragment.BaseStateFragment
import com.tix.uilibrary.util.getViewModel
import kotlinx.android.synthetic.main.frag_single_test_layout.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

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
        testType()
    }

    override fun getStateLayoutContent() = R.layout.frag_single_test_layout


   private fun testType(){
       var apples = arrayListOf<String>()
       var mineClass:Type = apples.javaClass
       if(mineClass is ParameterizedType){
           var types = mineClass.actualTypeArguments
           var curType = types[0]
       }
   }

}