package com.example.umc_practice1.scenario.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.umc_practice1.data.SettingValue
import com.example.umc_practice1.databinding.ItemSettingBinding

class SettingSwitchAdapter(private val switchlist: List<SettingValue>): RecyclerView.Adapter<SettingSwitchAdapter.SwitchViewHolder>() {
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
        fun bind(switch: SettingValue) {
            binding.settingTitleText.text = switch.value
            binding.switchSetting.isChecked = switch.completed
        }
    }


}