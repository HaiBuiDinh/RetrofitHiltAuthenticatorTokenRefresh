package com.ohno.retrofithilt2.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ohno.retrofithilt2.R

object PageUtils {

    fun replaceFragment(activity: FragmentActivity, fragment: Fragment) {
        activity.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, fragment)
            .commit()
    }
}