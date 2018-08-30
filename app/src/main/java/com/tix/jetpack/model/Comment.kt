package com.tix.jetpack.model

import com.google.gson.annotations.SerializedName
import com.tix.uilibrary.model.BaseEntity

/**
 * 评论对象
作者：created by ztcao on 2018/6/14 15 : 48
 */
data class Comment(var content:String,
                   @SerializedName("deploytime")var publishDate:String,  //发布日期
                   @SerializedName("employeeName")var publishUserName:String, //用户名
                   @SerializedName("headUrl")var publishUserProfile:String,//头像URL
                   var isreply :Int ,//是否回复
                   @SerializedName("commentReplyList")var replyList:List<CommentReply>): BaseEntity(){


    fun isReply():Boolean{
        return isreply == REPLYED
    }

    companion object {
        const val REPLYED    = 1
        const val REPLY_NOT =  0
    }
}