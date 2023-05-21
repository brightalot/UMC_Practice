package com.example.umc_practice1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_practice1.databinding.ItemSettingBinding

class SettingSwitchAdapter(private val switchlist: List<SettingSwitch>): RecyclerView.Adapter<SettingSwitchAdapter.SwitchViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SwitchViewHolder {

        return SwitchViewHolder(
            ItemSettingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SwitchViewHolder, position: Int) {
        holder.binding.switchSetting.setOnCheckedChangeListener { buttonView, isChecked ->
            switchlist[position].completed = isChecked }
        holder.bind(switchlist[position])
    }

    override fun getItemCount(): Int {
        return switchlist.size
    }

    class SwitchViewHolder(val binding: ItemSettingBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(switch: SettingSwitch) {
            binding.settingTitleText.text = switch.title
            binding.switchSetting.isChecked = switch.completed
        }
    }


}