package com.example.umc_practice1

import android.content.Intent
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_practice1.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var memoIndex = intent.getIntExtra("position", -1)

        var memoSaved = intent.getStringExtra("memo")
        if (memoSaved != null) {binding.etAddMemo.setText(memoSaved)}

        binding.btnSave.setOnClickListener {
            val memo = binding.etAddMemo.text.toString()
            val intent = Intent()
            if(memoIndex != -1) {
                intent.putExtra("memo", memo)
                intent.putExtra("position", memoIndex)
            }
            else {
                intent.putExtra("memo", memo)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}