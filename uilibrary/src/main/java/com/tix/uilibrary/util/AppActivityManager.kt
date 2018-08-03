package com.tix.uilibrary.util

import android.support.v4.app.FragmentActivity

/**
 * 管理Activity的桟
 * Created by ztcao on 2018/1/17.
 */
object AppActivityManager {
    var activityList = ArrayList<FragmentActivity>();

    fun add(fragmentActivity: FragmentActivity) {
        activityList.add(fragmentActivity);
    }

    fun remove(fragmentActivity: FragmentActivity) {
        activityList.remove(fragmentActivity);
    }

    fun finishAll() {
        for (activity in activityList) {
            activity.finish()
        }
    }

    fun isEmpty(): Boolean {
        return activityList == null || activityList.size == 0
    }
}