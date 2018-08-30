package com.tix.jetpack.model

import com.google.gson.annotations.SerializedName
import com.tix.uilibrary.model.BaseEntity

/**
 * 资讯模型
作者：created by ztcao on 2018/5/4 15 : 25
 */
data class Information(var name:String = "",
                       @SerializedName("deploytime")var uploadDate:String ="" ,
                       var content:String="" ,
                       @SerializedName("photo")var listUrl:String="",
                       var description:String="" ,
                       var outsideUrl:String="") : BaseEntity(){
}