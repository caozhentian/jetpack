package com.tix.uilibrary.fragment

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fingdo.statelayout.StateLayout
import com.tix.uilibrary.R
import com.tix.uilibrary.viewmodel.BaseViewModel
import com.trello.rxlifecycle2.android.FragmentEvent
import kotlinx.android.synthetic.main.frag_state_layout.*

/**
 * 支持状态切换Fragment(观察者模式 ViewModel--LiveData )
 * 支持ViewPaper fragment 懒惰加载数据
作者：created by ztcao on 2018/7/24 17 : 58
 */
abstract  class BaseStateFragment<T> :BaseFragment() {

    override fun getLayout() = R.layout.frag_state_layout
    override fun initView() {
        super.initView()
        initStateLayout()
    }

    private fun initStateLayout(){
        val contentView = LayoutInflater.from(context).inflate(getStateLayoutContent() , null)
        state_layout.addView(contentView , ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT))
        state_layout.setRefreshListener(object : StateLayout.OnViewRefreshListener{
            override fun refreshClick() = refresh()
            override fun loginClick() = gotoLogin() })
        state_layout.setTipText(StateLayout.LOADING ,getString(R.string.loading_wait))
    }

    //内容的布局文件
    @LayoutRes abstract  fun getStateLayoutContent():Int
    //获取关联的ViewModel模型
    abstract fun getBaseViewModel(): BaseViewModel<T>

    //使用Rxjava2 加载数据
    override fun loadData(){
        state_layout.showLoadingView() //加载视图
        var baseViewModel = getBaseViewModel()
        baseViewModel.getMainObservableData()
                .compose(bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribe({
            state_layout.showContentView()
            displayData(it)
        }) {
            displayError(it)
        }
    }

    //成功展示数据
    abstract fun displayData(data:T?)
    //显示错误数据 子类可以定制处理
    protected open fun displayError(error:Throwable){
        state_layout.showErrorView()
    }
    //网络错误 或者 其他错误 重试事件
    protected open fun refresh(){ loadData()}
    //业务逻辑 重试事件
    protected open fun gotoLogin(){}

    override fun dealUiEvent() {
         onLazyLoad() //重新加载数据 刷新UI
    }
}