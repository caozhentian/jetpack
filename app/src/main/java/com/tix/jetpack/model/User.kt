package com.tix.jetpack.model
import com.google.gson.annotations.SerializedName
import com.tix.uilibrary.model.BaseEntity

/**
 * "tid":7,"employeeNo":"","name":"曹","gender":2,
 * "cellNumber":"13186075290","operativeCenter":1,
 * "operativeCenterName":null,"departmentId":2,
 * "departmentName":null
作者：created by ztcao on 2018/5/4 13 : 59
 */
data class User(
        @SerializedName("name")var userName :String  = "",
        @SerializedName("cellNumber")var telphone :String  = "",
        @SerializedName("employeeNo")var employeeNo :String = "",
        @SerializedName("operativeCenterName")var operativeCenterName :String  = "",
        @SerializedName("departmentName")var departmentName :String = "",
        @SerializedName("token")var token :String  = "",
        @SerializedName("headUrl")var headUrl :String  = "", //头像
        @SerializedName("point")var point :Int = 0, //积分
        @SerializedName("positionName")var postion :String = "" ,// 职位
        var gender:Int = 1  ,//1 ,女 2 男
        var isReceivePushmessage:Int = 1 ,
        var scoreRank:Int = 1 //积分排名
) : BaseEntity() {

    fun getGenderStr():String{
        return when(gender){
            1 -> "女"
            else -> "男"
        }
    }

    fun isEnableJPush():Boolean{
        return isReceivePushmessage == 1
    }
}