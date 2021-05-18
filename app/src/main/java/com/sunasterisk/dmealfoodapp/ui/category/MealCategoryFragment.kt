package com.sunasterisk.dmealfoodapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.data.repository.MealCategoryRepository
import com.sunasterisk.dmealfoodapp.data.source.remote.MealCategoryRemoteDataSource
import com.sunasterisk.dmealfoodapp.databinding.FragmentMealCategoryBinding
import com.sunasterisk.dmealfoodapp.ui.category.adapter.MealCategoryAdapter
import com.sunasterisk.dmealfoodapp.ui.listmeal.MealListFragment
import com.sunasterisk.dmealfoodapp.ui.search.SearchFragment
import com.sunasterisk.dmealfoodapp.utils.CustomProgressBar
import com.sunasterisk.dmealfoodapp.utils.FragmentUtil
import com.sunasterisk.dmealfoodapp.utils.showToast


class MealCategoryFragment : BaseFragment<FragmentMealCategoryBinding>(),
    MealCategoryContact.View {

    private val mealCategoryAdapter = MealCategoryAdapter(this::itemMealCategoryClick)
    private val loadingProgressBar = CustomProgressBar()
    private var presenter: MealCategoryContact.Presenter? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMealCategoryBinding =
        FragmentMealCategoryBinding::inflate

    override fun onCreatedView() {
        binding.recyclerViewMealCategory.adapter = mealCategoryAdapter
    }

    override fun initData() {
        presenter = MealCategoryPresenter(
            this,
            MealCategoryRepository.getInstance(MealCategoryRemoteDataSource.getInstance())
        )
        presenter?.start()
    }

    override fun initActions() {
        binding.buttonSearch.setOnClickListener {
            FragmentUtil.addFragment(
                parentFragmentManager,
                R.id.fragmentContainer,
                SearchFragment()
            )
        }
    }

    override fun showMealCategories(mealCategories: List<MealCategory>) {
        mealCategoryAdapter.updateData(mealCategories as MutableList<MealCategory>)
    }

    override fun showMessage(data: Any) {
        context?.showToast(data.toString())
    }

    override fun showLoading() {
        context?.let {
            loadingProgressBar.showProgressBar(
                it,
                getString(R.string.msg_please_wait)
            )
        }
    }

    override fun hideLoading() {
        loadingProgressBar.dialog.dismiss()
    }

    private fun itemMealCategoryClick(mealCategory: MealCategory) {
        FragmentUtil.addFragment(
            parentFragmentManager,
            R.id.fragmentContainer,
            MealListFragment.getInstance(mealCategory)
        )
    }
}
