package com.tix.uilibrary.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tix.uilibrary.R
import kotlinx.android.synthetic.main.act_base_layout.*

/**
 * Created by ztcao on 2018/7/24  基类
 * */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//竖屏
        setContentView(getLayout())
        initToolbar()
        initPlaceholder()
        initUIEvent(savedInstanceState)
        initData(savedInstanceState)
    }

    @LayoutRes
    private fun getLayout() = R.layout.act_base_layout

    @LayoutRes
    protected  abstract  fun getPlaceholderLayout():Int

    private fun initToolbar() {
        setSupportActionBar(base_toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(isShowNav())
        toolbar_cust_title.text = getString(getTitleStrRes())
        base_toolbar.setNavigationOnClickListener{ //默认实现
            nav()
        }
        if(isHideAppBar()){
            supportActionBar?.hide()
        }
    }
    //Toolbar 必须在onCreate()之后设置标题文本，否则默认标签将覆盖我们的设置
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (base_toolbar != null) {//base_toolbar就是android.support.v7.widget.Toolbar
            base_toolbar.title = ""//设置为空，可以自己定义一个居中的控件，当做标题控件使用
        }
    }

    private fun initPlaceholder(){
        val placeholderView = LayoutInflater.from(this).inflate(getPlaceholderLayout() , null)
        base_content_ll_layout.addView(placeholderView ,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    }
    //初始化UI事件
    protected open fun initUIEvent(savedInstanceState: Bundle?){

    }
    //初始化数据（从上一个界面Intent的数据）
    protected  open fun initData(savedInstanceState: Bundle?){

    }

    //导航事件
    protected  open fun nav(){
        finish()
    }

    //title 子类需要title 可以覆盖
    @StringRes
    protected  open fun getTitleStrRes() = R.string.empty_title
    //是否需要隐藏工具栏 --
    protected  open fun isHideAppBar() = false
    //是否显示导航 -- 返回
    protected  open fun isShowNav()    = true
    //设置title字体大小 --
    fun setCustTitleTextSize(@DimenRes custTitleTextSize:Int ){
        toolbar_cust_title.textSize = resources.getDimension(custTitleTextSize)
    }
    //设置title的颜色
    fun setCustTitleTextColor(@ColorRes custTitleTextColor : Int ){
        toolbar_cust_title.setTextColor(ContextCompat.getColor(this , custTitleTextColor))
    }
    //设置应用栏 ---菜单
    fun setActionMenu(@MenuRes  resMenuId:Int){
        base_toolbar.menu.clear()
        base_toolbar.inflateMenu(resMenuId)
    }







}