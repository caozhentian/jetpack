package com.tix.jetpack.model

import com.google.gson.annotations.SerializedName
import com.tix.uilibrary.model.BaseEntity

/**
 *  课程
作者：created by ztcao on 2018/5/4 15 : 22
 */
data class Course(var name:String = "",
                  var label:String = "" ,
                  var createTime:String = "",
                  var description:String = "",
                  var videoUrl:String = "" ,
                  var audioUrl:String = "",
                  var content:String  = "",
                  var fileUrl:String = "" ,//attachment/userHeader/att1529656918966Gg3KW5.doc,attachment/userHeader/att1529656933262Cu3X51.xls
                  var comments: List<Comment> = arrayListOf(),//关联的一组评论
                  @SerializedName("cover")var urlBackground:String  = "",//课程背景图
                  var hasExercise:Int = 0,//是否有练习题 0:否 1:是,
                  var isCollected:Int = 0 ,//是否收藏 0:否 1:是
                  var fileName:String = "" ,//XGMZZS
                  var studyRecordId :String = "" , //对应的当前学习记录的ID
                  var hasPoint :Int = 0 ,//是否已经积分 //0 没有 ，  大于0 有
                  var audioTime :String = "" , //服务器端返回的 音频的总时间
                  var homeCover:String = ""

) : BaseEntity(){

    fun getAudioTotalTime():Long{
        if(audioTime.isNullOrEmpty()){
            return 0
        }
        return audioTime.toLong()
    }

    fun isExercise():Boolean{
        return hasExercise == 1
    }

    fun isCollected():Boolean{
        return isCollected == 1
    }

    fun collected(){
        isCollected = 1
    }

    fun unCollect(){
        isCollected = 0
    }

    //课程是否已经积分
    fun isHasScore():Boolean{
        return hasPoint > 0
    }
}