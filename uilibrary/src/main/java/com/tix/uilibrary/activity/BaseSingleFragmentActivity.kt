package com.tix.uilibrary.activity

import android.os.Bundle
import com.tix.uilibrary.fragment.BaseFragment
import com.tix.uilibrary.R
import com.tix.uilibrary.util.addFragment
import com.tix.uilibrary.util.replaceFragment

/**
 * 单个Fragment的基类
 * Created by ztcao on 2018/7/24
 */
abstract class BaseSingleFragmentActivity : BaseActivity() {

    protected abstract fun  createFragment(): BaseFragment

    override fun getPlaceholderLayout() = R.layout.act_single_fragment

    override fun initUIEvent(savedInstanceState: Bundle?) {
        super.initUIEvent(savedInstanceState)
        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            addFragment(createFragment(), R.id.fragment_container)
        }
    }

    fun <T : BaseFragment> addOrReplaceFragment(fragment: T) {
        var fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            addFragment(fragment, R.id.fragment_container)
        } else {
            replaceFragment(fragment, R.id.fragment_container)
        }
    }

    protected fun <T:BaseFragment> getCurFragment(): T {
        return supportFragmentManager.findFragmentById(R.id.fragment_container) as T
    }

}