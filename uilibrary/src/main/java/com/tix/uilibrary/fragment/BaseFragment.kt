package com.tix.uilibrary.fragment

import android.os.Bundle
import android.support.annotation.CheckResult
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tix.uilibrary.UiEvent
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.frag_state_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Fragment 的基类 支持Rxjava 声明周期管理
作者：created by ztcao on 2018/7/24 16 : 46
 */
abstract class BaseFragment: Fragment() , LifecycleProvider<FragmentEvent> {
    /**
     * 根 View
     */
    protected lateinit var rootView: View
    /**
     * 是否加载完成
     * 当执行完onCreatView,View的初始化方法后方法后即为true
     */
    protected var mIsViewPrepare: Boolean = false
    private val lifecycleSubject = BehaviorSubject.create<FragmentEvent>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(getLayout(), container, false)
        return rootView
    }

    /**
     * 设置根布局资源id
     */
    @LayoutRes
    protected abstract fun getLayout(): Int

    protected open fun initView()  {
        mIsViewPrepare = true
    }
    protected open fun initData(savedInstanceState: Bundle?){}

    //可以使用 使用Rxjava2 加载数据
    protected open fun loadData(){}

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
    //处理用户其它设备登录问题
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUiEvent(event: UiEvent) {
        dealUiEvent()
    }
    //处理UI需要 更新事件
    protected open fun dealUiEvent(){}

    @CheckResult
    override fun lifecycle(): Observable<FragmentEvent> {
        return lifecycleSubject.hide()
    }

    @CheckResult
    override fun <T> bindUntilEvent(event: FragmentEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event)
    }

    @CheckResult
    override fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject)
    }

    override fun onAttach(activity: android.app.Activity) {
        super.onAttach(activity)
        lifecycleSubject.onNext(FragmentEvent.ATTACH)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(FragmentEvent.CREATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW)
        // 在fragment使用这个extensions的时候，会找不到那个控件，解决办法  onViewCreated（） 调用
        initView()
        initData(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(FragmentEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(FragmentEvent.RESUME)
        if (userVisibleHint) {// 避免View Paper ,首次加载的，每个Fragment 调用onResume，多次请求网络
            onLazyLoad()
        }
    }

    override fun onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW)
        EventBus.getDefault().unregister(this)
        super.onDestroyView()
    }

    override fun onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY)
        super.onDestroy()
    }

    override fun onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH)
        super.onDetach()
    }
}