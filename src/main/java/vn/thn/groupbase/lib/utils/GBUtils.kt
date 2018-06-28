package vn.thn.groupbase.lib.utils

import android.text.TextUtils

/**
 * Created by truonghieunghia on 6/27/18.
 */
object GBUtils {
    fun isEmpty(objects: Any?): Boolean {
        return if (objects is String) {
            TextUtils.isEmpty(String::class.java.cast(objects))
        } else {
            return objects == null
        }
    }
}