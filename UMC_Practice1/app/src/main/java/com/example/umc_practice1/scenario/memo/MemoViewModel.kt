package com.example.umc_practice1.scenario.memo

import androidx.lifecycle.ViewModel
import com.example.umc_practice1.data.Memo

class MemoViewModel : ViewModel() {
    // caz's comment
    // 데이터를 ViewModel 에서 사용하려고 한 시도는 좋습니다! 하지만 저희는 데이터를 View에 상호작용하도록 전달해야 합니다.
    // 그럴때는 저희는 LiveData 혹은 Flow<T> 를 주로 사용합니다. 단순히 데이터의 저장만으로는 UI 에 어떻게 갱신해야 할 지 UI 는 알 수 없습니다.
    // 한번 LiveData 를 직접 사용해 보시는 것을 추천 드립니다.
    // LiveData 를 사용시에는 캡슐화의 개념또한 공부해 보시는 것을 권장 드립니다!
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
