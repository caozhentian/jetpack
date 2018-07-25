package com.tix.uilibrary.fragment

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fingdo.statelayout.StateLayout
import com.tix.uilibrary.R
import com.tix.uilibrary.activity.BaseViewModel
import kotlinx.android.synthetic.main.frag_state_layout.*

/**
 * 支持状态切换Fragment(观察者模式 ViewModel--LiveData )
 * 支持ViewPaper fragment 懒惰加载数据
作者：created by ztcao on 2018/7/24 17 : 58
 */
abstract  class BaseStateFragment<T> :BaseFragment() {
    /**
     * 是否加载完成
     * 当执行完onCreatView,View的初始化方法后方法后即为true
     */
    private var mIsViewPrepare: Boolean = false
    override fun getLayout() = R.layout.frag_state_layout
    override fun initView() {
        super.initView()
        initStateLayout()
        mIsViewPrepare = true
    }

    private fun initStateLayout(){
        val contentView = LayoutInflater.from(context).inflate(getStateLayoutContent() , null)
        state_layout.addView(contentView , ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT))
        state_layout.setRefreshListener(object : StateLayout.OnViewRefreshListener{
            override fun refreshClick() = refresh()
            override fun loginClick() = gotoLogin() })
        state_layout.setTipText(StateLayout.LOADING ,getString(R.string.loading_wait))
    }
    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        onLazyLoad()
    }

    //内容的布局文件
    @LayoutRes abstract  fun getStateLayoutContent():Int
    //获取关联的ViewModel模型
    abstract fun getBaseViewModel():BaseViewModel<T>
    //使用Rxjava2 加载数据
    protected fun loadData(){
        state_layout.showLoadingView() //加载视图
        var baseViewModel = getBaseViewModel()
        baseViewModel.getObservableData().subscribe({
            if(isDetached) return@subscribe
            state_layout.showContentView()
            displayData(it)
        }) {
            if(isDetached) return@subscribe
            displayError(it)
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onVisibleToUser()
        } else {
            onInVisibleToUser()
        }
    }
    /**
     * 用户可见时执行的操作
     */
    private fun onInVisibleToUser() {}
    /**
     * 用户可见时执行的操作
     */
    private fun onVisibleToUser() { onLazyLoad() }

    /**
     * 懒加载，仅当用户可见且view初始化结束后才会执行
     * 何为懒加载，就是view没有与用户交互的话并不会加载
     * 该方法主要在viewpager嵌套fragment的场景。
     * viewpager可以提前加载左右指定数目（数目可以通过setOffscreenPageLimit(int limit)设置）的fragment，
     * 如果使用懒加载，就只会做些view的创建等操作，避免提前执行其他页面的网络请求。
     * setUserVisibleHint(boolean isVisibleToUser)表示是否与用户可见，onCreatview方法前执行，当isVisibleToUser为true时表示对用户可见，
     * 执行自定义的onVisibleToUser()方法
     * 在onVisibleToUser()中，我们进行判断，当mIsPrepare为true且与用户可交互时执行onLazyLoad()方法进行懒加载。
     */
    protected fun onLazyLoad() {
        if (userVisibleHint && mIsViewPrepare) {// 避免View Paper ,首次加载的，每个Fragment 调用onResume，多次请求网络
            loadData()
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