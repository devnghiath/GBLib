package vn.thn.groupbase.lib.views.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import vn.thn.groupbase.lib.views.listeners.GBDialogListener

/**
 * Created by truonghieunghia on 6/28/18.
 */
abstract class GBDialogFragment : DialogFragment() {
    private lateinit var mFragmentView: View
    var layoutId: Int = 0
    var mDialogListener: GBDialogListener? = null
        get() = field
        set(value) {
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            layoutId = arguments!!.getInt("layoutId")
        } else {
            layoutId = layoutCommon();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentView = inflater.inflate(layoutId, container, false)
        isCancelable = false
        if (mDialogListener != null) {
            mDialogListener!!.onProcessContent(this)
        }
        return mFragmentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        if (mDialogListener != null) {
            mDialogListener!!.onProcessContent(this)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun show(manager: FragmentManager, tagName: String) {
        if (manager.findFragmentByTag(tagName) == null) {
            val ft = manager.beginTransaction()
            ft.add(this, tagName)
            ft.commitAllowingStateLoss()
        }
    }

    fun show(manager: FragmentManager) {
        if (manager.findFragmentByTag(dialogName()) == null) {
            val ft = manager.beginTransaction()
            ft.add(this, dialogName())
            ft.commitAllowingStateLoss()
        }

    }

    override fun dismiss() {
        if (fragmentManager != null) {
            dismissAllowingStateLoss()
        } else {
            return
        }
    }

    fun <T : View> findViewById(resId: Int): T? {
        return mFragmentView.findViewById<T>(resId)
    }

    //abstract
    abstract fun initView()

    abstract fun dialogName(): String
    abstract fun layoutCommon(): Int
}