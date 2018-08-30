package com.tix.jetpack.net;

import com.tix.jetpack.model.User;
import com.tix.jetpack.repository.UserRepository;
import com.tix.net.interceptor.BaseDynamicInterceptor;

import java.util.TreeMap;

/**
 * Created by ztcao on 2018/1/29.
 */

public class CommParamInterceptor extends BaseDynamicInterceptor<CommParamInterceptor> {
    @Override
    public TreeMap<String, String> dynamic(TreeMap<String, String> dynamicMap) {
        User curUser = UserRepository.INSTANCE.getCurUser() ;
        if(curUser != null) {
            dynamicMap.put("employeeId", Integer.toString(curUser.getTid()));
            //Token  必填 如果没有值 ，必须传空
            dynamicMap.put("token", curUser.getToken());
        }
        return dynamicMap;
    }
}
