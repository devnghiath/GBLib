package vn.thn.groupbase.lib.utils

import android.content.Context

/**
 * Created by truonghieunghia on 7/11/18.
 */
class GBSharedPreferences {
    private val mContext: Context
    private var REF_KEY = "";

    constructor(context: Context, refKey: String) {
        this.mContext = context
        this.REF_KEY = refKey
    }

    fun setString(key: String, value: String) {
        mContext.getSharedPreferences(REF_KEY, Context.MODE_PRIVATE).edit().putString(key, value).commit()
    }

    fun getString(key: String): String {
        return mContext.getSharedPreferences(REF_KEY, Context.MODE_PRIVATE).getString(key, "")
    }

    fun getInt(key: String): Int {
        return mContext.getSharedPreferences(REF_KEY, Context.MODE_PRIVATE).getInt(key, 0)
    }

    fun setInt(key: String, value: Int) {
        mContext.getSharedPreferences(REF_KEY, Context.MODE_PRIVATE).edit().putInt(key, value).commit()
    }

    fun setBoolean(key: String, value: Boolean) {
        mContext.getSharedPreferences(REF_KEY, Context.MODE_PRIVATE).edit().putBoolean(key, value).commit()
    }

    fun getBoolean(key: String): Boolean? {
        return mContext.getSharedPreferences(REF_KEY, Context.MODE_PRIVATE).getBoolean(key, false)
    }
}