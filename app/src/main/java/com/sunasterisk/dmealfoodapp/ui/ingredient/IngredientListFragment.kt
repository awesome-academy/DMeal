package com.sunasterisk.dmealfoodapp.ui.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.databinding.FragmentIngredientListBinding

class IngredientListFragment : BaseFragment<FragmentIngredientListBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientListBinding =
        FragmentIngredientListBinding::inflate

    override fun onCreatedView() {
    }

    override fun initData() {
    }

    override fun initActions() {
    }

}
