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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_practice1.MainActivity
import com.example.umc_practice1.scenario.memo.MemoActivity
import com.example.umc_practice1.scenario.memo.MemoAdapter
import com.example.umc_practice1.data.Memo
import com.example.umc_practice1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var memoActivityLauncher: ActivityResultLauncher<Intent>
    private lateinit var adapter: MemoAdapter
    private var memos: ArrayList<Memo> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = requireActivity() as? MainActivity
        memos = (mainActivity?.getMemos() ?: arrayListOf())
        initializeViews()
        initializeMemoActivityLauncher()
        initializeAdapter()
        setupAddMemoButton()
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
                if (position == -1) {
                    val memo = Memo(memoString)
                    memos.add(memo)
                    adapter.notifyDataSetChanged()
                } else {
                    val modifiedMemo = Memo(memoString)
                    memos[position] = modifiedMemo
                    adapter.notifyItemChanged(position)
                }
            }
        }
    }

    private fun initializeAdapter() {
        adapter = MemoAdapter(memos, memoActivityLauncher)
        binding.rvMemo.adapter = adapter
    }

    private fun setupAddMemoButton() {
        binding.btnAddMemo.setOnClickListener {
            val intent = Intent(requireContext(), MemoActivity::class.java)
            memoActivityLauncher.launch(intent)
        }
    }
    private fun saveMemo() {
        val memoText = binding.btnAddMemo.text.toString()
        (activity as? MainActivity)?.addMemo(memoText)
    }
}
