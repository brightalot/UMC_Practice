package com.example.umc_practice1.scenario.memo

import androidx.lifecycle.ViewModel
import com.example.umc_practice1.data.Memo

class MemoViewModel : ViewModel() {
    val currentMemos = arrayListOf<Memo>()

    fun addMemo(memoText: String, memoIndex: Int) {
        val _currentMemos = currentMemos
        if(memoIndex == -1) {
            val memo = Memo(memoText)
            _currentMemos.add(memo)
        } else {
            updateMemo(memoText, memoIndex)
        }
    }

    fun updateMemo(memoText: String, position: Int) {
        val _currentMemos = currentMemos
        if (position < _currentMemos.size) {
            _currentMemos[position].text = memoText
        }
    }

    fun deleteMemo(position: Int) {
        val _currentMemos = currentMemos
        if (position < _currentMemos.size) {
            _currentMemos.removeAt(position)
        }
    }
}
