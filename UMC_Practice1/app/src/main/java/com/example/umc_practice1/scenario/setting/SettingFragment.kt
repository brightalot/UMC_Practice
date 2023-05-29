package com.example.umc_practice1.scenario.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_practice1.MainActivity
import com.example.umc_practice1.data.SettingValue
import com.example.umc_practice1.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private var settings: List<SettingValue> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = requireActivity() as? MainActivity
        settings = (mainActivity?.getSettings() ?: listOf())
        initializeViews()
    }


    private fun initializeViews() {
        binding.rvSetting.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSetting.adapter = SettingSwitchAdapter(settings)
    }
}
