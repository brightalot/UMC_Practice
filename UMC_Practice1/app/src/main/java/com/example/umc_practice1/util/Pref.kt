package com.example.umc_practice1.util

import android.content.Context

object Pref {
    private var _ctx : Context? = null

    const val sharedPrefKey : String = "test_repo"
    const val memoKey : String = "memo_repo"

    val sharedPref = _ctx?.getSharedPreferences(sharedPrefKey ,Context.MODE_PRIVATE)


    fun getContext (ctx : Context) {
        _ctx = ctx
    }

    fun inputString(data : String) = with (sharedPref?.edit()) {
        this?.putString(memoKey, data)
    }

    fun getString(prefKey : String ) : String? =  sharedPref?.getString(memoKey, "{}")

}