package com.example.binlist.ui.root

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.binlist.ui.bincheck.BinCheckFragment
import com.example.binlist.ui.history.HistoryFragment

class RootPagerAdapter(activity: RootActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BinCheckFragment.newInstance()
            else -> HistoryFragment.newInstance()
        }
    }

}