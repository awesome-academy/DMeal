package com.sunasterisk.dmealfoodapp.ui.setting

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.sunasterisk.dmealfoodapp.R
import com.sunasterisk.dmealfoodapp.base.BaseFragment
import com.sunasterisk.dmealfoodapp.databinding.FragmentSettingBinding
import com.sunasterisk.dmealfoodapp.ui.main.MainActivity
import com.sunasterisk.dmealfoodapp.ui.search.SearchFragment
import com.sunasterisk.dmealfoodapp.utils.FragmentUtil
import com.sunasterisk.dmealfoodapp.utils.Notification
import com.sunasterisk.dmealfoodapp.utils.showToast

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    private var checkStateSwitch: Boolean? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingBinding =
        FragmentSettingBinding::inflate

    override fun onCreatedView() {
    }

    override fun initData() {
        val preferences: SharedPreferences? =
            context?.getSharedPreferences(PREF_SETTING, Context.MODE_PRIVATE)
        checkStateSwitch = preferences?.getBoolean(PREF_SWITCH, false)
    }

    override fun initActions() = with(binding) {
        switchNotification.isChecked = checkStateSwitch == true
        switchNotification.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Notification.enable(requireContext())
            } else {
                Notification.disable(requireContext())
            }
            val preferences: SharedPreferences? =
                requireContext().getSharedPreferences(PREF_SETTING, Context.MODE_PRIVATE)
            val editor = preferences?.edit()
            editor?.putBoolean(PREF_SWITCH, isChecked)
            editor?.apply()

        }
        buttonSearch.setOnClickListener {
            FragmentUtil.addFragment(
                parentFragmentManager,
                R.id.fragmentContainer,
                SearchFragment()
            )
        }
        textAboutMe.setOnClickListener {
            context?.let {
                AlertDialog.Builder(it).setView(R.layout.layout_custom_dialog).show()
            }
        }
    }

    companion object {
        const val PREF_SETTING = "PREF_SETTING"
        const val PREF_SWITCH = "PREF_SWITCH"
    }
}
