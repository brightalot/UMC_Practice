package com.example.umc_practice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_practice1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val memos: ArrayList<Memo> = arrayListOf()
    fun addMemo(memoText: String) {
        val memo = Memo(memoText)
        memos.add(memo)
    }

    fun getMemos(): ArrayList<Memo> {
        return memos
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
                    R.id.menu_setting -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, SettingFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_mypage -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.containerFragment.id, MypageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.menu_home
        }
    }
}