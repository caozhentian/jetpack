package com.tix.jetpack.model

import com.tix.uilibrary.model.BaseEntity

/**
作者：created by ztcao on 2018/6/27 16 : 44
 */
data class CommentReply(var content:String = "" ,
                        var createTime:String ="" ,
                        var employeeName:String = "",
                        var headUrl:String = ""
                        ) : BaseEntity(){
}