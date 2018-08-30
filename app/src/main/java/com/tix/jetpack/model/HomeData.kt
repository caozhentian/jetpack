package com.tix.jetpack.model

/**
作者：created by ztcao on 2018/8/29 15 : 38
 */
data class HomeData(var unReadNumber :Int , //未读消息数量
                    var courseList :MutableList<Course>,//推荐课程列表
                    var informationList :MutableList<Information> ,//咨询列表
                    var commentOrderVo :MutableList<CommentOrderVo>) {
}