package com.tix.jetpack.activity.login

import com.tix.jetpack.model.User
import com.tix.jetpack.repository.UserRepository
import com.tix.uilibrary.viewmodel.BaseViewModel
import io.reactivex.Observable

/**
作者：created by ztcao on 2018/7/25 11 : 26
 */
class LoginViewModel: BaseViewModel<User>() {
    var userName:String = ""
    var password:String = ""
    override fun getMainObservableData(): Observable<User> {
        return UserRepository.login(userName , password)
    }
}