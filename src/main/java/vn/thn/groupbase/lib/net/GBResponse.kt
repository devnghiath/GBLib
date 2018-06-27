package vn.thn.groupbase.lib.net

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * Created by truonghieunghia on 6/27/18.
 */
open class GBResponse {
    var responseType: GBResponseType
    var dataResponse: String
    var result_code: Int = 0
    var message: String = ""
    init {

    }
    constructor(response: String, responseType: GBResponseType) {
        this.dataResponse = response
        this.responseType = responseType
    }
    fun <T:GBResponse>toResponse(clazz: KClass<out T>): T? {
        val response: GBResponse
        try {
            response = clazz.createInstance()
            response.dataResponse = dataResponse
            response.responseType = responseType
            response.parser()
            return response
        } catch (e: InstantiationException) {
            e.printStackTrace()
            return null
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            return null
        }

    }
    protected open fun parser() {

    }
}