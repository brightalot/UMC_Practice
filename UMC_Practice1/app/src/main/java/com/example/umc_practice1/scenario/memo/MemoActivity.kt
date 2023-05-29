package com.example.umc_practice1.scenario.memo

import android.content.Intent
import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.umc_practice1.databinding.ActivityMemoBinding
import androidx.activity.viewModels

class MemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoBinding

    private val viewModel : MemoViewModel by viewModels()

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
    private lateinit var memoData: String
    override fun onPause() {
        super.onPause()
        memoData = binding.etAddMemo.text.toString()
    }

    override fun onRestart() {
        super.onRestart()

        var builder = AlertDialog.Builder(this)
        builder.setTitle("Memo")
            .setMessage("다시 작성하시겠습니까?")
            .setPositiveButton("네", DialogInterface.OnClickListener {
                    dialog, which -> binding.etAddMemo.text.clear()
            })
            .setNegativeButton("아니오", DialogInterface.OnClickListener {
                    dialog, which ->  memoData= ""
            })
            .create()
            .show()
    }
}