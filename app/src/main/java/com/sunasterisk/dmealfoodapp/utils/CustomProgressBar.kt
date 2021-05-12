package com.sunasterisk.dmealfoodapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.res.ResourcesCompat
import com.sunasterisk.dmealfoodapp.R
import kotlinx.android.synthetic.main.custom_progress_bar.view.*

class CustomProgressBar {
    lateinit var dialog: CustomDialog

    @SuppressLint("ResourceAsColor")
    fun showProgressBar(context: Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.custom_progress_bar, null)
        if (title != null) {
            view.textTitle.text = title
        }
        view.cardViewCustom.setCardBackgroundColor(R.color.color_alto)

        setColorFilter(
            view.progressBarLoading.indeterminateDrawable,
            ResourcesCompat.getColor(context.resources, R.color.color_mountain_meadow, null)
        )
        view.textTitle.setTextColor(Color.WHITE)

        dialog = CustomDialog(context)
        dialog.apply {
            setContentView(view)
            show()
        }
        return dialog
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            window?.decorView?.rootView?.setBackgroundResource(R.color.color_alto_29)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}
