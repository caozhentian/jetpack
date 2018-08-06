package com.tix.uilibrary.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ajguan.library.EasyRefreshLayout
import com.ajguan.library.LoadModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tix.uilibrary.R
import com.tix.uilibrary.model.PageInfo
import com.trello.rxlifecycle2.android.FragmentEvent
import io.reactivex.Observable
import java.util.*

/**支持分页查询Fragment
 * Created by ztcao on 2018/8/6
 */
open abstract class BaseListFrament<T> : BaseFragment() {

    var nextPage: Int = 0;

    //数据的容器
    protected var datas = ArrayList<T>()

    var easylayout :EasyRefreshLayout? = null ;
    //列表RecyclerView
    var dataRecyleView: RecyclerView? = null

    //Adapter
    var datadapter: BaseQuickAdapter<T, BaseViewHolder>? = null

    //默认实现 支持LinearLayoutManager
    open fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    abstract fun initAdater(): BaseQuickAdapter<T, BaseViewHolder>

    private fun initRecyclerView() {
        easylayout = rootView.findViewById(R.id.easylayout)
        dataRecyleView = rootView.findViewById(R.id.rc_data);
        dataRecyleView?.layoutManager = getLayoutManager()
        datadapter = initAdater()
        dataRecyleView?.adapter = datadapter
        datadapter?.setOnItemClickListener { _, _, position -> itemClick(datas[position]) }
    }

    protected open fun itemClick(data: T){}

    //下拉刷新数据
    fun refreshData(datasSource: List<T>) {
        datas.clear();
        datas.addAll(datasSource)
        datadapter?.notifyDataSetChanged()
        easylayout?.refreshComplete()
    }

    fun loadMoreData(datasSource: List<T>) {
        datas.addAll(datasSource)
        datadapter?.notifyDataSetChanged()
        easylayout?.loadMoreComplete()
    }

    override fun getLayout() = R.layout.frag_list_data

    override fun initView() {
        super.initView()
        initRecyclerView()
        initUpDown()
    }

    private fun initUpDown() {
        easylayout?.addEasyEvent(object : EasyRefreshLayout.EasyEvent {
            override fun onLoadMore() {
                nextPage++
                loadDataByPage()
            }
            override fun onRefreshing(){
                nextPage = 0
                loadDataByPage()
            } })
    }

    override  fun loadData() {
        easylayout?.autoRefresh()
    }
    //开始指定page页的加载数据
    fun loadDataByPage() {
        var observable = getObservable(PageInfo(nextPage,getPageSize() , keyword = getKeyword()))
        observable.compose(bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribe({
                    if(nextPage == 0){
                        refreshData(it)
                    }
                    else{
                        loadMoreData(it)
                    }
                }){
                    if( nextPage > 0){
                        nextPage--
                    }
                }
    }

    //子类实现此方法
    abstract fun getObservable(pageInfo: PageInfo):Observable<List<T>>
    //默认关键字的实现
    protected  open fun getKeyword() = ""
    //禁用下拉加载更多
    fun disableLoadMoreData() {
        easylayout?.loadMoreModel = LoadModel.NONE
    }
    //禁用上拉刷新
    fun disableLoadData(){
        easylayout?.isEnablePullToRefresh = false
    }
    protected open fun getPageSize():Int{
        return PageInfo.PAGE_DEFAULT_SIZE
    }
    //是否使用默认分割线
    protected open fun isEnableDivider():Boolean = true
    //是否显示ll_list_search_header布局
    protected open fun isShowSearchHeader():Boolean = false

}