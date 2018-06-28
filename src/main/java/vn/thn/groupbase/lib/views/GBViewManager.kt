package vn.thn.groupbase.lib.views

import android.content.Context
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.inputmethod.InputMethodManager
import vn.thn.groupbase.lib.utils.GBUtils
import vn.thn.groupbase.lib.views.fragment.GBCommonFragment
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * Created by truonghieunghia on 6/27/18.
 */
class GBViewManager {
    var mContentId = 0
    var mFragmentManager: FragmentManager
    var mFragmentActivity: FragmentActivity

    constructor(fragmentManager: FragmentManager, @IdRes contentId: Int = 0, activity: FragmentActivity) {
        this.mFragmentManager = fragmentManager
        this.mFragmentActivity = activity
        this.mContentId = contentId
    }

    /**
     * create instance Fragment from class
     */
    private fun <T : Fragment> getView(fragment: KClass<T>, data: Bundle? = null): T? {
        try {
            val view = fragment.createInstance()
            if (data != null) {
                view.arguments = data
            }
            return view
        } catch (e: Exception) {
            return null
        }
    }

    fun hideKeyboard() {
        val imm = mFragmentActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (mFragmentActivity.currentFocus != null) {
            imm.hideSoftInputFromWindow(mFragmentActivity.currentFocus!!.windowToken, 0)
        }
    }

    /**
     * pushView
     */
    fun pushView(fragment: KClass<out Fragment>, data: Bundle? = null, tag: String? = null, @IdRes contentId: Int = 0) {
        hideKeyboard()
        var tagName = tag
        val view = getView(fragment, data)
        if ( view != null) {
            val ft = mFragmentManager.beginTransaction()
            if (view is GBCommonFragment){
                view.setAnimationCustom(ft)
                if (view.fragmentName() != null){
                    tagName = view.fragmentName()
                }
            } else{
                tagName = view.javaClass.simpleName!!
            }

            if (!GBUtils.isEmpty(tag)) {
                tagName = tag
            }

            if (contentId == 0) {
                ft.replace(mContentId, view, tagName)
            } else {
                ft.replace(contentId, view, tagName)
            }

            ft.addToBackStack(tagName)
            ft.commitAllowingStateLoss()
        }
    }

    /**
     * pushView
     */
    fun pushView(fragment: Fragment, data: Bundle? = null, tag: String? = null, @IdRes contentId: Int = 0) {
        hideKeyboard()
        var tagName = tag
        if(data!=null){
            fragment.arguments = data
        }
        val ft = mFragmentManager.beginTransaction()
        if (fragment is GBCommonFragment){
            fragment.setAnimationCustom(ft)
            if (fragment.fragmentName() != null){
                tagName = fragment.fragmentName()
            }
        } else{
            tagName = fragment.javaClass.simpleName!!
        }

        if (!GBUtils.isEmpty(tag)) {
            tagName = tag
        }

        if (contentId == 0) {
            ft.replace(mContentId, fragment, tagName)
        } else {
            ft.replace(contentId, fragment, tagName)
        }
        ft.addToBackStack(tagName)
        ft.commitAllowingStateLoss()
    }


    /**
     * addView
     */
    fun addView(fragment: Fragment, data: Bundle? = null, tag: String? = null, @IdRes contentId: Int = 0) {
        hideKeyboard()
        var tagName = tag
        if (data!=null){
            fragment.arguments = data
        }
        val ft = mFragmentManager.beginTransaction()
        if (fragment is GBCommonFragment){
            fragment.setAnimationCustom(ft)
            if (fragment.fragmentName() != null){
                tagName = fragment.fragmentName()
            }
        } else{
            tagName = fragment.javaClass.simpleName!!
        }

        if (!GBUtils.isEmpty(tag)) {
            tagName = tag
        }


        if (contentId == 0) {
            ft.replace(mContentId, fragment,tagName)
        } else {
            ft.replace(contentId, fragment,tagName)
        }
        ft.commitAllowingStateLoss()
        //            mFragmentManager.executePendingTransactions();
    }

    /**
     * addView
     */
    fun addView(fragment: KClass<out Fragment>, data: Bundle? = null, tag: String? = null, @IdRes contentId: Int = 0) {
        hideKeyboard()
        var tagName = tag
        val view = getView(fragment, data)
        if (view != null) {
            val ft = mFragmentManager.beginTransaction()
            if (view is GBCommonFragment){
                view.setAnimationCustom(ft)
                if (view.fragmentName() != null){
                    tagName = view.fragmentName()
                }
            } else{
                tagName = view.javaClass.simpleName!!
            }

            if (!GBUtils.isEmpty(tag)) {
                tagName = tag
            }

            if (contentId == 0) {
                ft.replace(mContentId, view,tagName)
            } else {
                ft.replace(contentId, view,tagName)
            }
            ft.commitAllowingStateLoss()
            //            mFragmentManager.executePendingTransactions();
        }
    }


    /**
     * getViewCurrent
     */
    fun getViewCurrent(@IdRes contentId: Int = 0): Fragment? {
        if (contentId == 0) {
            return mFragmentManager.findFragmentById(mContentId)
        } else {
            return mFragmentManager.findFragmentById(contentId)
        }

    }

    /**
     * pushViewToRoot
     */
    fun pushViewToRoot(fragment: KClass<out Fragment>, data: Bundle?, tag: String?, @IdRes contentId: Int = 0) {
        hideKeyboard()
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        var tagName = tag
        val view = getView(fragment, data)
        if ( view != null) {
            val ft = mFragmentManager.beginTransaction()
            if (view is GBCommonFragment){
                view.setAnimationCustom(ft)
                if (view.fragmentName() != null){
                    tagName = view.fragmentName()
                }
            } else{
                tagName = view.javaClass.simpleName!!
            }

            if (!GBUtils.isEmpty(tag)) {
                tagName = tag
            }

            if (contentId == 0) {
                ft.replace(mContentId, view, tagName)
            } else {
                ft.replace(contentId, view, tagName)
            }
            ft.commitAllowingStateLoss()
            //            mFragmentManager.executePendingTransactions();
        }
    }

    /**
     * pushViewToRoot
     */
    fun pushViewToRoot(fragment: Fragment, data: Bundle?, tag: String?, @IdRes contentId: Int = 0) {
        hideKeyboard()
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        var tagName = tag
        if (data!=null){
            fragment.arguments = data
        }
        val ft = mFragmentManager.beginTransaction()
        if (fragment is GBCommonFragment){
            fragment.setAnimationCustom(ft)
            if (fragment.fragmentName() != null){
                tagName = fragment.fragmentName()
            }
        } else{
            tagName = fragment.javaClass.simpleName!!
        }

        if (!GBUtils.isEmpty(tag)) {
            tagName = tag
        }
        if (contentId == 0) {
            ft.replace(mContentId, fragment, tagName)
        } else {
            ft.replace(contentId, fragment, tagName)
        }
        ft.commitAllowingStateLoss()
        //mFragmentManager.executePendingTransactions();
    }
}