package com.example.umc_practice1.scenario.memo

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView.*
import com.example.umc_practice1.data.Memo
import com.example.umc_practice1.databinding.ItemMemoBinding
import com.example.umc_practice1.util.Pref

//caz's comment
// Adapter에서 ViewModel 을 사용하려고 시도한 것은 나쁘지 않습니다. Adpater도 결국 View의 일부이기 떄문에 MemoViewModel 을 넘겨주어서 viewModel 이 처리한 결과를
// Adapter 가 받아볼 수 있도록 하는것
// 하지만 동일한 ViewModel 을 사용하게 된다면, memoAdapter 는 MemoActivity 에서 보여주게 되는 하위 오브젝트입니다.
// memoViewModel 을 MemoAdapter 또한 알게 되기 때문에 ViewModel 의 생명주기가 Activity 와 Adapter 둘과 엮이게 됩니다.
// 필요하다면 Adapter를 위한 ViewModel 을 하나 더 생성하거나,
// 그때 말씀드린 람다, 고차함수를 사용한 callback 처리를 통해서 로직을 구현해보도록 합시다!
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