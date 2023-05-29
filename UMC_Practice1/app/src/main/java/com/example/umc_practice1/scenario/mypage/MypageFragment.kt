package com.example.umc_practice1.scenario.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.umc_practice1.databinding.FragmentMypageBinding
import com.google.android.material.tabs.TabLayoutMediator

class MypageFragment : Fragment() {
    private lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fragment view 생성이 된 후 viewPager2 setting
        val viewPageAdapter = ViewPagerAdapter(this)
        binding.vpMypage.adapter = viewPageAdapter

        val tabTitleArray = arrayOf(
            "One",
            "Two",
            "Three",
        )
        TabLayoutMediator(binding.tabMypage, binding.vpMypage) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }
}