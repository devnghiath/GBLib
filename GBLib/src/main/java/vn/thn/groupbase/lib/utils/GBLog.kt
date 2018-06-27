package vn.thn.groupbase.lib.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by truonghieunghia on 6/27/18.
 */
object GBLog {
    fun error(tags: String, msg: String, isDebug: Boolean = false, prefix: String = "GB_") {
        var tag = tags
        var message = msg
        if (isDebug) {
            if (GBUtils.isEmpty(tag)) {
                tag = "error"
            }
            if (GBUtils.isEmpty(message)) {
                message = ""
            }
            android.util.Log.e(prefix + tag, message)
        }
    }

    fun info(tags: String, msg: String, isDebug: Boolean = false, prefix: String = "GB_") {
        var tag = tags
        var message = msg
        if (isDebug) {
            if (GBUtils.isEmpty(tag)) {
                tag = "info"
            }
            if (GBUtils.isEmpty(message)) {
                message = ""
            }
            android.util.Log.i(prefix + tag, message)
        }
    }

    fun showToast(context: Context, sms: String, isDebug: Boolean = false, prefix: String = "GB_") {
        if (isDebug) {
            Toast.makeText(context, prefix + sms, Toast.LENGTH_SHORT).show()
        }
    }
}