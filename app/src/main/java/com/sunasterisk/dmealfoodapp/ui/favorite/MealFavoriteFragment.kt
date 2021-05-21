package com.sunasterisk.dmealfoodapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.local.MealLocalDataSource
import com.sunasterisk.dmealfoodapp.data.source.local.dao.MealDaoImpl
import com.sunasterisk.dmealfoodapp.data.source.local.db.AppDatabase
import com.sunasterisk.dmealfoodapp.data.source.remote.MealRemoteDataSource
import com.sunasterisk.dmealfoodapp.databinding.FragmentMealFavoriteBinding
import com.sunasterisk.dmealfoodapp.ui.detailmeal.MealDetailActivity
import com.sunasterisk.dmealfoodapp.ui.favorite.adapter.MealFavoriteAdapter
import com.sunasterisk.dmealfoodapp.ui.search.SearchFragment
import com.sunasterisk.dmealfoodapp.utils.CustomProgressBar
import com.sunasterisk.dmealfoodapp.utils.FragmentUtil
import com.sunasterisk.dmealfoodapp.utils.showToast

class MealFavoriteFragment : BaseFragment<FragmentMealFavoriteBinding>(), MealFavoriteContact.View {

    private val mealFavoriteAdapter by lazy {
        MealFavoriteAdapter {
            startActivity(MealDetailActivity.getInstance(context, it))
        }
    }
    private val loadingProgressBar = CustomProgressBar()
    private var presenter: MealFavoriteContact.Presenter? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMealFavoriteBinding =
        FragmentMealFavoriteBinding::inflate

    override fun onCreatedView() {
        binding.recyclerFavoriteMeals.adapter = mealFavoriteAdapter
    }

    override fun initData() {
    }

    override fun onStart() {
        super.onStart()
        presenter =
            MealFavoritePresenter(
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

    override fun showMealsFavorite(meals: List<Meal>) {
        mealFavoriteAdapter.updateData(meals.toMutableList())
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
}
