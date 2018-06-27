package vn.thn.groupbase.lib.views.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.thn.groupbase.lib.views.GBViewManager
import vn.thn.groupbase.lib.views.activity.GBActivityCommon

/**
 * Created by truonghieunghia on 6/27/18.
 */
abstract class GBCommonFragment : Fragment() {
    lateinit var viewManager: GBViewManager
    protected lateinit var mFragmentView: ViewGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity is GBActivityCommon){
            viewManager = (activity as GBActivityCommon).viewManager
        } else {
            if (activity!=null)
            viewManager = GBViewManager(activity!!.supportFragmentManager,contentId(), activity!!)
        }

        firstInit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentView = inflater.inflate(layoutCommon(), container, false) as ViewGroup
        val viewMain = findViewById<ViewGroup>(viewCommonID());
        if (viewMain != null) {
            val view = inflater.inflate(layoutContent(), container, false)
            viewMain.removeAllViews()
            viewMain.addView(view)
        }
        return mFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }
    fun setVisibility(resId: Int,visibility: Int) {
        mFragmentView.findViewById<View>(resId).visibility = visibility
    }
    fun <T : View> findViewById(resId: Int): T? {
        return mFragmentView.findViewById<T>(resId)
    }

    //abstract
    abstract fun layoutCommon(): Int
    abstract fun viewCommonID(): Int
    abstract fun contentId(): Int
    abstract fun layoutContent(): Int
    abstract fun firstInit()
    abstract fun initView()
    abstract fun setAnimationCustom(animationCustom: FragmentTransaction)
}