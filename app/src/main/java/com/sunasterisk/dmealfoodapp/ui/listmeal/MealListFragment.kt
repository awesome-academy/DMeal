package com.sunasterisk.dmealfoodapp.ui.listmeal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.model.MealCategory
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.local.MealLocalDataSource
import com.sunasterisk.dmealfoodapp.data.source.local.dao.MealDaoImpl
import com.sunasterisk.dmealfoodapp.data.source.local.db.AppDatabase
import com.sunasterisk.dmealfoodapp.data.source.remote.MealRemoteDataSource
import com.sunasterisk.dmealfoodapp.databinding.FragmentMealListBinding
import com.sunasterisk.dmealfoodapp.ui.detailmeal.MealDetailActivity
import com.sunasterisk.dmealfoodapp.ui.listmeal.adapter.MealListAdapter
import com.sunasterisk.dmealfoodapp.ui.search.SearchFragment
import com.sunasterisk.dmealfoodapp.utils.CustomProgressBar
import com.sunasterisk.dmealfoodapp.utils.FragmentUtil
import com.sunasterisk.dmealfoodapp.utils.showToast


class MealListFragment : BaseFragment<FragmentMealListBinding>(), MealListContact.View {

    private val mealListAdapter = MealListAdapter(this::itemMealClick)
    private val loadingProgressBar = CustomProgressBar()
    private var presenter: MealListContact.Presenter? = null


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMealListBinding =
        FragmentMealListBinding::inflate

    override fun onCreatedView() {
        binding.recyclerViewMeal.adapter = mealListAdapter
    }

    override fun initData() {
        presenter =
            MealListPresenter(
                this,
                MealRepository.getInstance(
                    MealRemoteDataSource.getInstance(),
                    MealLocalDataSource.getInstance(
                        MealDaoImpl.getInstance(
                            AppDatabase.getInstance(context)
                        )
                    )
                )
            )
        presenter?.start()
        getTransferredData()
    }

    override fun initActions() {
        binding.buttonSearch.setOnClickListener {
            FragmentUtil.addFragment(
                parentFragmentManager,
                R.id.fragmentContainer,
                SearchFragment()
            )
        }
        binding.buttonBack.setOnClickListener {
            FragmentUtil.backPress(parentFragmentManager, MealListFragment())
        }
    }

    override fun showListMeal(listMeal: List<Meal>) {
        mealListAdapter.updateData(listMeal.toMutableList())
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

    private fun getTransferredData() {
        arguments?.getParcelable<MealCategory>(MEAL_CATEGORY)?.apply {
            binding.textTitle.text = name
            presenter?.getListMealByCategory(this)
        }
    }

    private fun itemMealClick(meal: Meal) {
        startActivity(MealDetailActivity.getInstance(context, meal))
    }

    companion object {
        const val MEAL_CATEGORY = "MEAL_CATEGORY"

        fun getInstance(mealCategory: Any) = MealListFragment().apply {
            arguments = bundleOf(MEAL_CATEGORY to mealCategory)
        }
    }
}
