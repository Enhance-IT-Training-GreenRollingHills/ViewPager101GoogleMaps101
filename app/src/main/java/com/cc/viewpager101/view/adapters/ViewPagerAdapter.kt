package com.cc.viewpager101.view.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cc.viewpager101.util.LogToConsole
import com.cc.viewpager101.view.CaseLocationsFragment
import com.cc.viewpager101.view.ClosedCaseListFragment
import com.cc.viewpager101.view.MainActivity
import com.cc.viewpager101.view.OpenCaseFragment

class ViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        //LogToConsole.log("createFragment")
        //LogToConsole.log("position : $position")
        return when (position) {
            0 -> ClosedCaseListFragment()
            1 -> OpenCaseFragment()
            else -> CaseLocationsFragment()
        }
    }

}