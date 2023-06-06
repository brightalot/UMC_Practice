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
            //caz's comment
            // 메모 별로 아이디를 설정 하는것 나쁘지 않네요! 하지만 , 추후에는 데이터 Class 도 저장이 가능하기 때문에 , Json Object 나 다른 형태도 저장해보시는 것도 실습해봅시다!
            // 이 부분은 제가 드린 사전과제에서 구현해 보시는게 좋을것 같습니다
            editor.putString("memo_$i", memos[i].text)
        }
        editor.apply()
    }
}
