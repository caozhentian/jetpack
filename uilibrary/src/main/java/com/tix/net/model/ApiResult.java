/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tix.net.model;

import com.google.gson.annotations.SerializedName;

/**
 * <p>描述：提供的默认的标注返回api</p>
 * 作者： ztcao<br>
 * 日期： 2018/8/8 15:58 <br>
 * 版本： v1.0<br>
 */
public class ApiResult<T> {
    //判断code值 默认返回1 代表本次请求成功
    public static final int CODE_OK = 1  ;
    //字段code的变量名称
    public static final String FILDE_CODE_NAME  =  "code" ;
    //字段msg的变量名称
    public static final String FILDE_MSG_NAME   =  "msg" ;
    //字段data的变量名称
    public static final String FILDE_DATA_NAME  = "data" ;

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String msg;
    @SerializedName("object")
    private T data;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return code == CODE_OK;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
