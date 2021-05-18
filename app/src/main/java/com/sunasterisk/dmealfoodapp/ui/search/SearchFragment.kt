package com.sunasterisk.dmealfoodapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.databinding.FragmentSearchBinding
import com.sunasterisk.dmealfoodapp.databinding.LayoutToolBarScreenChildBinding
import com.sunasterisk.dmealfoodapp.utils.gone
import com.sunasterisk.dmealfoodapp.utils.show

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    override fun onCreatedView() {
    }

    override fun initData() {
    }

    override fun initActions() {
    }
}
