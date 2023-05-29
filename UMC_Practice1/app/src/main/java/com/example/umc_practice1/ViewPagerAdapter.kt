package com.example.umc_practice1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

// 3개의 화면을 구성한다고 가정
// OneFragment, TwoFragment, ThreeFragment
class ViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    // 페이지 갯수 설정
    override fun getItemCount(): Int = 3

    // 불러올 Fragment 정의
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OneFragment()
            1 -> TwoFragment()
            2 -> ThreeFragment()
            else -> OneFragment()
        }
    }
}