package vn.thn.groupbase.lib.net

import android.support.v4.app.FragmentActivity
import android.text.TextUtils
import okhttp3.*
import vn.thn.groupbase.lib.utils.GBUtils
import java.io.IOException

/**
 * Created by truonghieunghia on 6/27/18.
 */
abstract class GBRequest : Callback {

    private var mClient: OkHttpClient
    private var mParameters: HashMap<String, String> = HashMap()
    private var mHeader: HashMap<String, String> = HashMap()
    private var mRequestName: String = ""
    private var mMethod: String = "GET"
    var mRequestListener: GBRequestCallBack? = null
    var mContext: FragmentActivity? = null
    var responseType: GBResponseType = GBResponseType.JSON
    var request = this
    var mediaType = "application/json"
        get() = field
        set(value) {
            field = value
        }

    init {
        mClient = OkHttpClient()
    }

    /**
     * constructor
     */
    constructor(requestName: String, requestListener: GBRequestCallBack? = null, context: FragmentActivity? = null, responseType: GBResponseType = GBResponseType.JSON) {
        this.mRequestName = requestName
        this.mRequestListener = requestListener
        this.mContext = context
        this.responseType = responseType
        request = this
    }

    /**
     * addQueryParameter
     */
    fun addQueryParameter(parameterName: String, parameterValue: String) {
        this.mParameters.put(parameterName, parameterValue);
    }

    /**
     * removeQueryParameter
     */
    fun removeQueryParameter(parameterName: String) {
        this.mParameters.remove(parameterName);
    }

    /**
     * removeHeader
     */
    fun removeHeader(headerName: String) {
        this.mHeader.remove(headerName);
    }

    /**
     * addHeader
     */
    fun addHeader(headerName: String, headerValue: String) {
        this.mHeader.put(headerName, headerValue);
    }

    /**
     * GBRequest
     */
    fun get(): GBRequest {
        mMethod = "GET"
        return this
    }

    /**
     * GBRequest
     */
    fun post(): GBRequest {
        mMethod = "POST"
        return this
    }

    /**
     * makeUrl
     */
    protected fun makeUrl(): String {
        val urlBuilder = HttpUrl.parse(getDomain() + getVersion() + (if (TextUtils.isEmpty(getPath()) == true) "" else getPath()) + mRequestName)!!.newBuilder()
        for (key in mParameters.keys) {
            urlBuilder.addEncodedQueryParameter(key, mParameters[key])
        }
        return urlBuilder.build().toString()
    }

    /**
     * execute
     */
    fun execute() {
        var builder = Request.Builder()
        builder = builder.url(makeUrl())
        if (mHeader.size > 0) {
            for (key in mHeader.keys) {
                if (!GBUtils.isEmpty(mHeader[key])){
                    builder = builder.addHeader(key, mHeader[key]!!)
                }

            }
        }
        if (mMethod.equals("POST", true)) {
            var body: RequestBody?
            if (!GBUtils.isEmpty(getBodyPost())) {
                body = RequestBody.create(MediaType.parse(mediaType), getBodyPost())
            } else {
                if (mParameters.size > 0) {
                    var formBody = FormBody.Builder()
                    for (key in this.mParameters.keys) {
                        if (!GBUtils.isEmpty(mParameters[key])) {
                            formBody.add(key, mParameters[key]!!)
                        }
                    }
                    formBody.build()
                    body = formBody.build()
                } else {
                    body = RequestBody.create(MediaType.parse(mediaType), "")
                }
            }
            builder.method("POST", body)
        } else {
            builder.method("GET", null)
        }
        var request: Request = builder.build()
        mClient.newCall(request).enqueue(this)
    }

    //implemented
    override fun onFailure(call: Call?, e: IOException?) {
        if (mRequestListener != null) {
            if (mContext != null) {
                mContext!!.runOnUiThread { mRequestListener!!.onRequestError(GBRequestError.NETWORK_NOT_CONNECT, request) }
            } else {
                mRequestListener!!.onRequestError(GBRequestError.NETWORK_NOT_CONNECT, this)
            }
        }
    }

    override fun onResponse(call: Call?, response: Response?) {
        val body = response!!.body()?.string()
        if (mRequestListener != null) {
            if (mContext != null) {
                mContext!!.runOnUiThread { mRequestListener!!.onResponse(GBResponse(body!!, responseType), request) }
            } else {
                mRequestListener!!.onResponse(GBResponse(body!!, responseType), this)
            }
        }
    }

    //abstract
    abstract fun getDomain(): String

    abstract fun getVersion(): String

    abstract fun getPath(): String?

    abstract fun getBodyPost(): String
}