package com.example.umc_practice1.util

import android.content.Context
import android.content.SharedPreferences
import com.example.umc_practice1.data.Memo

object Pref {
    private lateinit var sharedPref: SharedPreferences
    private const val sharedPrefKey: String = "test_repo"

    fun getContext(context: Context) {
        sharedPref = context.getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
    }

    fun loadMemos(memos: ArrayList<Memo>) {
        val memoId = sharedPref.getInt("memo_size", 0)
        memos.clear()
        for (i in 0 until memoId) {
            val memoText = sharedPref.getString("memo_$i", null)
            memoText?.let {
                val memo = Memo(it)
                memos.add(memo)
            }
        }
    }

    fun saveMemos(memos: ArrayList<Memo>) {
        val editor = sharedPref.edit()
        editor.putInt("memo_size", memos.size)
        for (i in memos.indices) {
            editor.putString("memo_$i", memos[i].text)
        }
        editor.apply()
    }
}
