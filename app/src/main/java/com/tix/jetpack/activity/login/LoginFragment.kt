package com.tix.jetpack.activity.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import com.tix.jetpack.R
import com.tix.jetpack.activity.main.MainActivity
import com.tix.jetpack.model.User
import com.tix.jetpack.repository.UserRepository
import com.tix.uilibrary.fragment.BaseFragment
import kotlinx.android.synthetic.main.frag_login.*

/**
作者：created by ztcao on 2018/8/29 14 : 24
 */
class LoginFragment:BaseFragment() {
    override fun getLayout() = R.layout.frag_login

    override fun initView() {
        super.initView()
        btn_login.setOnClickListener {
            var loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
            loginViewModel.userName = edtUserName.editableText.toString()
            loginViewModel.password = edtPassword.editableText.toString()
            submitData(loginViewModel.getMainObservableData(),{loginSucess(it)})
        }
    }

    private fun loginSucess(user:User){
        UserRepository.setCurUser( user)
        var intent = Intent(context , MainActivity::class.java)
        startActivity(intent)
    }
}