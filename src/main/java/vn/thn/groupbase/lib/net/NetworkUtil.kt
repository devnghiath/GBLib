package vn.thn.groupbase.lib.net

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by truonghieunghia on 2/14/17.
 */

object NetworkUtil {
    var TYPE_WIFI = 1
    var TYPE_MOBILE = 2
    var TYPE_NOT_CONNECTED = 0

    @SuppressLint("MissingPermission")
    fun getConnectivityStatus(context: Context): Int {
        val cm = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        if (null != activeNetwork) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI

            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE
        }
        return TYPE_NOT_CONNECTED
    }

    @SuppressLint("MissingPermission")
    fun isNetworkConnected(mContext: Context): Boolean {
        if (NetworkUtil.getConnectivityStatus(mContext) == TYPE_NOT_CONNECTED) {
            return false
        } else {
            val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return if (netInfo == null) {
                false
            } else {
                netInfo.isConnected && netInfo.isAvailable
            }
        }
    }
}
