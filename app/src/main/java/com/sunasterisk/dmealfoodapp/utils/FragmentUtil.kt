package com.sunasterisk.dmealfoodapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

object FragmentUtil {

    fun addFragment(
        fragmentManager: FragmentManager,
        layoutId: Int,
        fragment: Fragment,
        tag: String? = null
    ) {
        fragmentManager.beginTransaction()
            .apply {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                add(layoutId, fragment, tag)
                commit()
            }
    }
}
