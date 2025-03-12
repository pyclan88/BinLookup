package com.example.binlist.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.binlist.R
import com.example.binlist.databinding.ActivityRootBinding
import com.google.android.material.tabs.TabLayoutMediator

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding
    private lateinit var tabMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = RootPagerAdapter(this)

        tabMediator = TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.tab_bin_check)
                1 -> tab.text = getString(R.string.tab_request_history)
            }
        }
        tabMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        tabMediator.detach()
    }
}