package com.example.umc_practice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import com.example.umc_practice1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val memos: ArrayList<Memo> = arrayListOf()
    private val settings = listOf(
        SettingSwitch("1번째 스위치", false),
        SettingSwitch("2번째 스위치", false),
        SettingSwitch("3번째 스위치", false),
        SettingSwitch("4번째 스위치", false),
        SettingSwitch("5번째 스위치", false),
        SettingSwitch("6번째 스위치", false),
        SettingSwitch("7번째 스위치", false),
        SettingSwitch("8번째 스위치", false),
        SettingSwitch("9번째 스위치", false),
        SettingSwitch("10번째 스위치", false),
        SettingSwitch("11번째 스위치", false),
        SettingSwitch("12번째 스위치", false),
        SettingSwitch("13번째 스위치", false),
        SettingSwitch("14번째 스위치", false),
        SettingSwitch("15번째 스위치", false),
        SettingSwitch("16번째 스위치", false),
        SettingSwitch("17번째 스위치", false),
        SettingSwitch("18번째 스위치", false),
        SettingSwitch("19번째 스위치", false),
        SettingSwitch("20번째 스위치", false),
        SettingSwitch("21번째 스위치", false),
        SettingSwitch("22번째 스위치", false),
    )

    fun addMemo(memoText: String) {
        val memo = Memo(memoText)
        memos.add(memo)
    }

    fun getMemos(): ArrayList<Memo> {
        return memos
    }

    fun getSettings(): List<SettingSwitch> {
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