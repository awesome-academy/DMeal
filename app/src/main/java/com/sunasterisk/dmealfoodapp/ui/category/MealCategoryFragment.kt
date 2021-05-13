package com.sunasterisk.dmealfoodapp.ui.category

import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.data.repository.MealCategoryRepository
import com.sunasterisk.dmealfoodapp.data.source.MealCategoryDataSource
import com.sunasterisk.dmealfoodapp.data.source.MealDataSource
import com.sunasterisk.dmealfoodapp.data.source.remote.MealCategoryRemoteDataSource
import com.sunasterisk.dmealfoodapp.data.source.remote.MealRemoteDataSource
import com.sunasterisk.dmealfoodapp.ui.category.adapter.MealCategoryAdapter
import com.sunasterisk.dmealfoodapp.ui.listmeal.MealListFragment
import com.sunasterisk.dmealfoodapp.ui.search.SearchFragment
import com.sunasterisk.dmealfoodapp.utils.CustomProgressBar
import com.sunasterisk.dmealfoodapp.utils.FragmentUtil
import com.sunasterisk.dmealfoodapp.utils.showToast
import kotlinx.android.synthetic.main.fragment_meal_category.*
import kotlinx.android.synthetic.main.tool_bar_screen_parent.*

class MealCategoryFragment : BaseFragment(R.layout.fragment_meal_category),
    MealCategoryContact.View {

    private val mealCategoryAdapter = MealCategoryAdapter(this::itemMealCategoryClick)
    private val loadingProgressBar = CustomProgressBar()
    private var presenter: MealCategoryContact.Presenter? = null

    override fun onCreatedView() {
        recyclerViewMealCategory.adapter = mealCategoryAdapter
    }

    override fun initData() {
        presenter = MealCategoryPresenter(
            this,
            MealCategoryRepository.getInstance(MealCategoryRemoteDataSource.getInstance())
        )
        presenter?.start()
    }

    override fun initActions() {
        buttonSearch.setOnClickListener {
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
            MealListFragment.getInstance(mealCategory.id)
        )
    }
}
