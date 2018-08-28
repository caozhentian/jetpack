/*
 * Copyright (C) 2017
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

package com.tix.net.func;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.tix.net.model.ApiResult;
import com.tix.net.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;


/**
 * ResponseBody 转换为ApiResult<T>
 */
public class ApiResultFunc<T> implements Function<ResponseBody, ApiResult<T>> {
    protected Type type;
    protected Gson gson;

    public ApiResultFunc(Type type) {
        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .create();
        this.type = type;
    }

    @Override
    public ApiResult<T> apply(@NonNull ResponseBody responseBody) throws Exception {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(-1);
        apiResult.setMsg("");
        //默认Apiresult
        try {
            final String json = responseBody.string();
            final Class<T> clazz = Utils.getClass(type, 0);
            if (clazz.equals(String.class)) {
                final ApiResult result = parseApiResult(json, apiResult);
                if (result != null) {
                    apiResult = result;
                    apiResult.setData((T) json);
                } else {
                    apiResult.setMsg("json is null");
                }
            } else {
                final ApiResult result = parseApiResult(json, apiResult);
                if (result != null) {
                    apiResult = result;
                    if (apiResult.getData() != null) {
                        T data = gson.fromJson(apiResult.getData().toString(), clazz);
                        apiResult.setData(data);
                    } else {
                        apiResult.setMsg("ApiResult's data is null");
                    }
                } else {
                    apiResult.setMsg("json is null");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            apiResult.setMsg(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            apiResult.setMsg(e.getMessage());
        } finally {
            responseBody.close();
        }

        return apiResult;
    }

    private ApiResult parseApiResult(String json, ApiResult apiResult) throws JSONException {
        if (TextUtils.isEmpty(json))
            return null;
        JSONObject jsonObject = new JSONObject(json);
        try {
            SerializedName codeAnnotation = apiResult.getClass().getDeclaredField(ApiResult.FILDE_CODE_NAME).getAnnotation(SerializedName.class);
            String code = codeAnnotation.value() ;
            if (jsonObject.has(code)) {
                apiResult.setCode(jsonObject.getInt(code));
            }
            SerializedName dataAnnotation = apiResult.getClass().getDeclaredField(ApiResult.FILDE_DATA_NAME).getAnnotation(SerializedName.class);
            String data = dataAnnotation.value() ;
            if (jsonObject.has(data)) {
                apiResult.setData(jsonObject.getString(data));
            }
            SerializedName msgAnnotation = apiResult.getClass().getDeclaredField(ApiResult.FILDE_MSG_NAME).getAnnotation(SerializedName.class);
            String msg = msgAnnotation.value() ;
            if (jsonObject.has(msg)) {
                apiResult.setMsg(jsonObject.getString(msg));
            }
            return apiResult;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw  new IllegalArgumentException("ApiResult字段上 必须存在SerializedName注解");
        }
    }
}
