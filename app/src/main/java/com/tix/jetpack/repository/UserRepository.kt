package com.tix.jetpack.repository

import com.tix.jetpack.model.User
import com.tix.net.RetrofitManager
import io.reactivex.Observable

/**
作者：created by ztcao on 2018/8/29 14 : 11
 */
object UserRepository {

    private var user:User? = null

    fun login(userName:String , password:String): Observable<User> {
        var paramMap = mapOf("mobileOrNo" to userName ,"password" to password,"imei" to "asdfde09090")
        return RetrofitManager.getInstance().post("nologin/login" ,paramMap ,User::class.java)
    }

    fun getCurUser():User?{
        return user
    }

    fun setCurUser(curUser:User){
        user = curUser
    }
}