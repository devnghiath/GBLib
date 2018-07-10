package vn.thn.groupbase.lib.views.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import vn.thn.groupbase.lib.views.GBViewManager

/**
 * Created by truonghieunghia on 6/27/18.
 */
abstract class GBActivityCommon : AppCompatActivity() {
    lateinit var viewManager: GBViewManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutCommon())
        viewManager = GBViewManager(supportFragmentManager,contentId(),this)
        initCommon()
        init()
    }

    //abstract
    /**
     * layoutCommon
     * return file layout in resource
     */
    abstract fun layoutCommon(): Int

    /**
     * init
     */
    abstract fun init()

    /**
     *initCommon
     */
    abstract fun initCommon()
    /**
     *contentId
     */
    abstract fun contentId(): Int
}