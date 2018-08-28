package com.tix.net.interceptor;

import java.util.TreeMap;

/**
 * Created by ztcao on 2018/1/29.
 */

public class CommParamInterceptor extends BaseDynamicInterceptor<CommParamInterceptor> {
    @Override
    public TreeMap<String, String> dynamic(TreeMap<String, String> dynamicMap) {
        dynamicMap.put("employeeId", "8");
        String token = "wertttrts" ;
        //Token  必填 如果没有值 ，必须传空
        dynamicMap.put("token", token);
        return dynamicMap;
    }
}
