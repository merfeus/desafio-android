package com.picpay.desafio.android.extensions

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.picpay.desafio.android.R

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    @IdRes idComponent: Int = R.id.main_container
) {
    supportFragmentManager.beginTransaction()
        .replace(idComponent, fragment)
        .commitNow()
}