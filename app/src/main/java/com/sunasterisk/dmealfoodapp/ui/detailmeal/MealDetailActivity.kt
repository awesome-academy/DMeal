package com.sunasterisk.dmealfoodapp.ui.detailmeal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.sunasterisk.dmealfoodapp.BuildConfig
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.data.model.Meal
import com.sunasterisk.dmealfoodapp.data.model.MealDetail
import com.sunasterisk.dmealfoodapp.data.repository.MealRepository
import com.sunasterisk.dmealfoodapp.data.source.local.MealLocalDataSource
import com.sunasterisk.dmealfoodapp.data.source.local.dao.MealDaoImpl
import com.sunasterisk.dmealfoodapp.data.source.local.db.AppDatabase
import com.sunasterisk.dmealfoodapp.data.source.remote.MealRemoteDataSource
import com.sunasterisk.dmealfoodapp.databinding.ActivityMealDetailBinding
import com.sunasterisk.dmealfoodapp.utils.*

class MealDetailActivity : YouTubeBaseActivity(), MealDetailContact.View {

    private lateinit var binding: ActivityMealDetailBinding
    private var presenter: MealDetailPresenter? = null
    private var meal: Meal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initActions()
    }

    override fun showMealDetailByMeal(mealDetails: List<MealDetail>) {
        mealDetails.first {
            binding.apply {
                textNameMeal.text = it.name
                textDescription.text = it.instruction
                textIngredientSpecific.text = it.ingredient
                showVideo(getIdVideo(it.youtube))
            }
            true
        }
    }

    override fun showMealFavorite(isFavorite: Int) {
        val favorite = isFavorite >= 1
        binding.buttonFavorite.isVisible = favorite
        binding.buttonUnFavorite.isVisible = !favorite
    }

    override fun isInsertedMealFavorite(long: Long) {
        showMessage(resources.getString(R.string.msg_insert_success))
    }

    override fun isDeletedMealFavorite(boolean: Boolean) {
        showMessage(resources.getString(R.string.msg_delete_success))
    }

    override fun showMessage(data: Any) {
        showToast(data.toString())
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    private fun initActions() = with(binding) {
        buttonBack.setOnClickListener {
            onBackPressed()
            finish()
        }
        buttonFavorite.setOnClickListener {
            buttonUnFavorite.show()
            it.gone()
            meal?.let { meal -> presenter?.deleteMealFavorite(meal.id) }
        }
        buttonUnFavorite.setOnClickListener {
            buttonFavorite.show()
            it.gone()
            meal?.let { meal -> presenter?.insertMealFavorite(meal) }
        }
    }

    private fun initData() {
        meal = intent.getParcelableExtra(MEAL) as Meal?
        presenter = MealDetailPresenter(
            this,
            MealRepository.getInstance(
                MealRemoteDataSource.getInstance(),
                MealLocalDataSource.getInstance(
                    MealDaoImpl.getInstance(
                        AppDatabase.getInstance(this)
                    )
                )
            )
        )
        meal?.let { meal ->
            presenter?.apply {
                getMealDetailByMeal(meal)
                getMealFavorite(meal)
            }
        }
    }

    private fun showVideo(link: String) {
        binding.viewVideo.initialize(BuildConfig.KEY_YOUTUBE,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    youtubePlayer: YouTubePlayer?,
                    restored: Boolean
                ) {
                    if (!restored) {
                        youtubePlayer?.apply {
                            cueVideo(link)
                            play()
                        }
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider?,
                    result: YouTubeInitializationResult?
                ) {
                }
            })
    }

    private fun getIdVideo(linkVideo: String?) = linkVideo.toString()
        .split(Constants.BASE_URL_VIDEO)[1]

    companion object {
        const val MEAL = "MEAL"

        fun getInstance(context: Context?, meal: Meal) =
            Intent(context, MealDetailActivity::class.java).apply {
                putExtra(MEAL, meal)
            }
    }
}
