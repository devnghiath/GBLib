package vn.thn.groupbase.lib.views.listeners

import vn.thn.groupbase.lib.views.dialogs.GBDialogFragment

/**
 * Created by truonghieunghia on 6/28/18.
 */
interface GBDialogListener {
    fun<T:GBDialogFragment> onProcessContent(dialog: T)
}