package com.sunasterisk.dmealfoodapp.ui.ingredientdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.local.MealLocalDataSource
import com.sunasterisk.dmealfoodapp.data.source.local.dao.MealDaoImpl
import com.sunasterisk.dmealfoodapp.data.source.local.db.AppDatabase
import com.sunasterisk.dmealfoodapp.data.source.remote.MealRemoteDataSource
import com.sunasterisk.dmealfoodapp.databinding.FragmentIngredientDetailBinding
import com.sunasterisk.dmealfoodapp.ui.detailmeal.MealDetailActivity
import com.sunasterisk.dmealfoodapp.ui.listmeal.MealListFragment
import com.sunasterisk.dmealfoodapp.ui.listmeal.adapter.MealListAdapter
import com.sunasterisk.dmealfoodapp.ui.search.SearchFragment
import com.sunasterisk.dmealfoodapp.utils.CustomProgressBar
import com.sunasterisk.dmealfoodapp.utils.FragmentUtil
import com.sunasterisk.dmealfoodapp.utils.showToast

class IngredientDetailFragment : BaseFragment<FragmentIngredientDetailBinding>(),
    IngredientDetailContact.View {

    private val mealListAdapter by lazy {
        MealListAdapter {
            startActivity(MealDetailActivity.getInstance(context, it))
        }
    }
    private val loadingProgressBar = CustomProgressBar()
    private var presenter: IngredientDetailContact.Presenter? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientDetailBinding =
        FragmentIngredientDetailBinding::inflate

    override fun onCreatedView() {
        binding.recyclerViewMeals.adapter = mealListAdapter
    }

    override fun initData() {
        presenter = IngredientDetailPresenter(
            this, MealRepository.getInstance(
                MealRemoteDataSource.getInstance(), MealLocalDataSource.getInstance(
                    MealDaoImpl.getInstance(
                        AppDatabase.getInstance(context)
                    )
                )
            )
        )
        getTransferredData()
    }

    override fun initActions() = with(binding) {
        buttonSearch.setOnClickListener {
            FragmentUtil.addFragment(
                parentFragmentManager,
                R.id.fragmentContainer,
                SearchFragment()
            )
        }
        buttonBack.setOnClickListener {
            FragmentUtil.backPress(parentFragmentManager, MealListFragment())
        }
    }

    override fun showMeals(meals: List<Meal>) {
        mealListAdapter.updateData(meals.toMutableList())
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
        arguments?.getParcelable<Ingredient>(BUNDLE_INGREDIENT)?.apply {
            binding.textTitle.text = name
            binding.textContent.text = description
            presenter?.getMealsByIngredient(this)
        }
    }

    companion object {
        private const val BUNDLE_INGREDIENT = "BUNDLE_INGREDIENT"

        fun getInstance(ingredient: Ingredient) = IngredientDetailFragment().apply {
            arguments = bundleOf(BUNDLE_INGREDIENT to ingredient)
        }
    }
}
