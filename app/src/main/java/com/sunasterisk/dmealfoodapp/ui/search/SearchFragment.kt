package com.sunasterisk.dmealfoodapp.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.local.MealLocalDataSource
import com.sunasterisk.dmealfoodapp.data.source.local.dao.MealDaoImpl
import com.sunasterisk.dmealfoodapp.data.source.local.db.AppDatabase
import com.sunasterisk.dmealfoodapp.data.source.remote.MealRemoteDataSource
import com.sunasterisk.dmealfoodapp.databinding.FragmentSearchBinding
import com.sunasterisk.dmealfoodapp.ui.detailmeal.MealDetailActivity
import com.sunasterisk.dmealfoodapp.ui.listmeal.adapter.MealListAdapter
import com.sunasterisk.dmealfoodapp.utils.*

class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchContact.View {

    private val searchAdapter = MealListAdapter(this::clickItemMeal)
    private val loadingProgressBar = CustomProgressBar()
    private var searchPresenter: SearchContact.Presenter? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding =
        FragmentSearchBinding::inflate

    override fun onCreatedView() {
        binding.recyclerMeals.adapter = searchAdapter
    }

    override fun initData() {
        searchPresenter =
            SearchPresenter(
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
        searchPresenter?.start()
    }

    override fun initActions() {
        binding.apply {
            editTextSearch.setOnEditorActionListener(onEditorActionListener())
            buttonSearch.setOnClickListener {
                searchListener(editTextSearch)
            }
            buttonClearSearch.setOnClickListener {
                editTextSearch.text = null
                buttonClearSearch.gone()
                buttonSearch.show()
            }
            buttonBack.setOnClickListener {
                FragmentUtil.backPress(parentFragmentManager, SearchFragment())
            }
        }
    }

    override fun showResult(meals: List<Meal>) {
        searchAdapter.updateData(meals.toMutableList())
    }

    override fun showNotFound() {
        binding.imageNotFound.show()
    }

    override fun hideNotFound() {
        binding.imageNotFound.gone()
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

    private fun onEditorActionListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, _, _ ->
            searchListener(binding.editTextSearch)
            true
        }
    }

    private fun searchListener(editText: EditText) {
        val nameMeal = editText.text.toString()
        if (nameMeal.isEmpty()) {
            context?.showToast(getString(R.string.text_fill_name_meal))
        } else {
            closeKeyboard()
            binding.apply {
                buttonSearch.gone()
                buttonClearSearch.show()
            }
            searchPresenter?.getMealsByKeySearch(nameMeal)
        }
    }

    private fun clickItemMeal(meal: Meal) {
        startActivity(MealDetailActivity.getInstance(context, meal))
    }

    private fun closeKeyboard() {
        activity?.currentFocus?.let {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, FLAG_OPERATING)
        }
    }

    companion object {
        const val FLAG_OPERATING = 0
    }
}

