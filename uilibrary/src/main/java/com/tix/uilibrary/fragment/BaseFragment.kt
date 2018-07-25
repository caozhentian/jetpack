package com.tix.uilibrary.fragment

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tix.uilibrary.UiEvent
import com.tix.uilibrary.activity.BaseActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Fragment 的基类
作者：created by ztcao on 2018/7/24 16 : 46
 */
abstract class BaseFragment: Fragment() {
    /**
     * 根 View
     */
    protected lateinit var rootView: View
    //host Activity
    protected lateinit var baseActivity:BaseActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(getLayout(), container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 在fragment使用这个extensions的时候，会找不到那个控件，解决办法  onCreateView（） 调用
        initView()
        initData(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override  fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        baseActivity = context as BaseActivity
    }
    /**
     * 设置根布局资源id
     */
    @LayoutRes
    protected abstract fun getLayout(): Int

    protected open fun initView()  { }
    protected open fun initData(savedInstanceState: Bundle?){}

    //处理用户其它设备登录问题
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUiEvent(event: UiEvent) {
        dealUiEvent()
    }
    //处理UI需要 更新事件
    protected abstract fun dealUiEvent()
}