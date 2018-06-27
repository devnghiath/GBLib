package vn.thn.groupbase.lib.net

/**
 * Created by truonghieunghia on 6/27/18.
 */
interface GBRequestCallBack {
    /**
     *
     * @param response
     * @param objRequest
     */
    abstract fun <T : GBRequest> onResponse(response: GBResponse, request: T)

    /**
     *
     * @param errorRequest
     * @param objRequest
     */
    abstract fun <T : GBRequest> onRequestError(errorRequest: GBRequestError, request: T)
}