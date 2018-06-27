package vn.thn.groupbase.tctn.views.fragment

import vn.thn.groupbase.lib.views.fragment.GBCommonFragment
import vn.thn.groupbase.tctn.R

/**
 * Created by truonghieunghia on 6/27/18.
 */
abstract class FragmentBase: GBCommonFragment() {
    override fun layoutCommon(): Int {
        return R.layout.fragment_base
    }

    override fun viewCommonID(): Int {
        return R.id.content
    }

    override fun contentId(): Int {
        return 0;
    }
}