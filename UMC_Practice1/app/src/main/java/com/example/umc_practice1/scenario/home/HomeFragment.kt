package com.example.umc_practice1.scenario.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_practice1.scenario.memo.MemoActivity
import com.example.umc_practice1.scenario.memo.MemoAdapter
import com.example.umc_practice1.databinding.FragmentHomeBinding
import com.example.umc_practice1.scenario.memo.MemoViewModel
import com.example.umc_practice1.util.Pref

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var memoActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: MemoAdapter
    private lateinit var memoViewModel: MemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memoViewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
        Pref.getContext(requireContext())

        initializeViews()
        initializeMemoActivityLauncher()
        initializeAdapter()
        setupAddMemoButton()
        Pref.loadMemos(memoViewModel.currentMemos)
    }

    private fun initializeViews() {
        binding.rvMemo.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initializeMemoActivityLauncher() {
        memoActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val memoString = data?.getStringExtra("memo") ?: ""
                val position = data?.getIntExtra("position", -1) ?: -1
                memoViewModel.addMemo(memoString, position)
                adapter.notifyItemChanged(position)
                Pref.saveMemos(memoViewModel.currentMemos)
            }
        }
    }

    private fun initializeAdapter() {
        adapter = MemoAdapter(memoViewModel, memoActivityLauncher)
        binding.rvMemo.adapter = adapter
    }

    private fun setupAddMemoButton() {
        binding.btnAddMemo.setOnClickListener {
            val intent = Intent(requireContext(), MemoActivity::class.java)
            memoActivityLauncher.launch(intent)
        }
    }
}
