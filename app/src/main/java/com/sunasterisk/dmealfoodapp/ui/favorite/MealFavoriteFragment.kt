package com.sunasterisk.dmealfoodapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.databinding.FragmentMealFavoriteBinding

class MealFavoriteFragment : BaseFragment<FragmentMealFavoriteBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMealFavoriteBinding =
        FragmentMealFavoriteBinding::inflate

    override fun onCreatedView() {
    }

    override fun initData() {
    }

    override fun initActions() {
    }

}
