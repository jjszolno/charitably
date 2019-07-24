package com.rootstrap.donations.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

/**
 * Created by jan on 3/29/18.
 */

// FragmentManager

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

// FragmentActivity

fun FragmentActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun FragmentActivity.replaceAddingToBSFragment(fragment: Fragment, frameId: Int, backStackName: String? = null) {
    supportFragmentManager.inTransaction { replace(frameId, fragment).addToBackStack(backStackName) }
}

fun FragmentActivity.replace(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

// Bundle

fun Bundle.getSerializable(arG_PARAM: String, defaultValue: Serializable): Serializable? {
    return getSerializable(arG_PARAM) ?: defaultValue
}

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object : TypeToken<T>() {}.type)
