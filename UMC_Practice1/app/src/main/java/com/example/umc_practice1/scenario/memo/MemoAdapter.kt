package com.example.umc_practice1.scenario.memo

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView.*
import com.example.umc_practice1.data.Memo
import com.example.umc_practice1.databinding.ItemMemoBinding
import com.example.umc_practice1.util.Pref

class MemoAdapter(
    private val memoViewModel: MemoViewModel,
    private val memoActivityLauncher: ActivityResultLauncher<Intent>)
    : Adapter<MemoAdapter.MemoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        return MemoViewHolder(
            ItemMemoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(memoViewModel.currentMemos[position])
        holder.binding.btnDelete.setOnClickListener {
            memoViewModel.deleteMemo(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
            Pref.saveMemos(memoViewModel.currentMemos)
        }
        holder.binding.tvMemo.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MemoActivity::class.java)
                .putExtra("memo", memoViewModel.currentMemos[holder.adapterPosition].text)
                .putExtra("position", holder.adapterPosition)
            memoActivityLauncher.launch(intent)
        }
    }

    override fun getItemCount(): Int {
        return memoViewModel.currentMemos.size
    }

    class MemoViewHolder(val binding: ItemMemoBinding)
        : ViewHolder(binding.root){
        fun bind(memo: Memo) {
            binding.tvMemo.text = memo.text
        }
    }
}