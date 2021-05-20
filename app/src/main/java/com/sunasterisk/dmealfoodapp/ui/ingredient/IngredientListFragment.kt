package com.sunasterisk.dmealfoodapp.ui.ingredient

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.Ingredient
import com.sunasterisk.dmealfoodapp.data.repository.IngredientRepository
import com.sunasterisk.dmealfoodapp.data.source.local.IngredientLocalDataSource
import com.sunasterisk.dmealfoodapp.data.source.local.dao.IngredientDaoImpl
import com.sunasterisk.dmealfoodapp.data.source.local.db.AppDatabase
import com.sunasterisk.dmealfoodapp.data.source.remote.IngredientRemoteDataSource
import com.sunasterisk.dmealfoodapp.databinding.FragmentIngredientListBinding
import com.sunasterisk.dmealfoodapp.ui.ingredient.adapter.IngredientAdapter
import com.sunasterisk.dmealfoodapp.ui.ingredientdetail.IngredientDetailFragment
import com.sunasterisk.dmealfoodapp.ui.search.SearchFragment
import com.sunasterisk.dmealfoodapp.utils.CustomProgressBar
import com.sunasterisk.dmealfoodapp.utils.FragmentUtil
import com.sunasterisk.dmealfoodapp.utils.showToast

class IngredientListFragment : BaseFragment<FragmentIngredientListBinding>(),
    IngredientContact.View {

    private val ingredientAdapter by lazy {
        IngredientAdapter() {
            FragmentUtil.addFragment(
                parentFragmentManager,
                R.id.fragmentContainer,
                IngredientDetailFragment.getInstance(it)
            )
        }
    }
    private val loadingProgressBar = CustomProgressBar()
    private var presenter: IngredientContact.Presenter? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientListBinding =
        FragmentIngredientListBinding::inflate

    override fun onCreatedView() {
        binding.recyclerViewIngredients.adapter = ingredientAdapter
    }

    override fun initData() {
        presenter = IngredientPresenter(
            this,
            IngredientRepository.getInstance(
                IngredientRemoteDataSource.getInstance(),
                IngredientLocalDataSource.getInstance(
                    IngredientDaoImpl.getInstance(
                        AppDatabase.getInstance(
                            context
                        )
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

    override fun showIngredients(ingredients: List<Ingredient>) {
        ingredientAdapter.updateData(ingredients.toMutableList())
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
