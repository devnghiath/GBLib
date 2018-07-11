package vn.thn.groupbase.lib.utils

import android.app.Application
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

    fun DateNow(format: String = "yyyy-MM-dd"): String {
        val fm = SimpleDateFormat(format)
        val calendar = Calendar.getInstance()
        return fm.format(calendar.time)
    }

    fun convertDate(inputDate: String, inPutFormat: String, outPutFormat: String): String {
        if (GBUtils.isEmpty(inputDate)) return ""
        val infm = SimpleDateFormat(inPutFormat)
        val outfm = SimpleDateFormat(outPutFormat)
        try {
            val date = infm.parse(inputDate)
            return outfm.format(date)
        } catch (e: ParseException) {
            return ""
        }
    }

    fun getVersionName(app: Application): String {
        try {
            return app.getPackageManager()
                    .getPackageInfo(app.getPackageName(), 0).versionName
        } catch (e: Exception) {
            return ""
        }
    }

    fun getOsVersion(): String {
        val release = Build.VERSION.RELEASE
        val sdkVersion = Build.VERSION.SDK_INT
        return "Android SDK: $sdkVersion ($release)"
    }

    fun getDeviceId(app: Application): String {
        return Settings.Secure.getString(app.getContentResolver(),
                Settings.Secure.ANDROID_ID)
    }
}