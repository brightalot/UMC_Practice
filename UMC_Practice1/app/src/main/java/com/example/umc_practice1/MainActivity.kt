package com.example.umc_practice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_practice1.data.Memo
import com.example.umc_practice1.data.SettingValue
import com.example.umc_practice1.databinding.ActivityMainBinding
import com.example.umc_practice1.scenario.home.HomeFragment
import com.example.umc_practice1.scenario.mypage.MypageFragment
import com.example.umc_practice1.scenario.setting.SettingFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val memos: ArrayList<Memo> = arrayListOf()
    private val settings = listOf(
        SettingValue("1번째 스위치", false),
        SettingValue("2번째 스위치", false),
        SettingValue("3번째 스위치", false),
        SettingValue("4번째 스위치", false),
        SettingValue("5번째 스위치", false),
        SettingValue("6번째 스위치", false),
        SettingValue("7번째 스위치", false),
        SettingValue("8번째 스위치", false),
        SettingValue("9번째 스위치", false),
        SettingValue("10번째 스위치", false),
        SettingValue("11번째 스위치", false),
        SettingValue("12번째 스위치", false),
        SettingValue("13번째 스위치", false),
        SettingValue("14번째 스위치", false),
        SettingValue("15번째 스위치", false),
        SettingValue("16번째 스위치", false),
        SettingValue("17번째 스위치", false),
        SettingValue("18번째 스위치", false),
        SettingValue("19번째 스위치", false),
        SettingValue("20번째 스위치", false),
        SettingValue("21번째 스위치", false),
        SettingValue("22번째 스위치", false),
    )

    fun addMemo(memoText: String) {
        val memo = Memo(memoText)
        memos.add(memo)
    }

    fun getMemos(): ArrayList<Memo> {
        return memos
    }

    fun getSettings(): List<SettingValue> {
        return settings
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(binding.containerFragment.id, HomeFragment())
            .commitAllowingStateLoss()

        binding.bottomNavigationView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_mypage -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, MypageFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_setting -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, SettingFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_home
        }
    }
}