package com.example.assignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ListFragmentAdapter(fragmentManager: FragmentManager, behaviour: Int) :
    FragmentStatePagerAdapter(fragmentManager, behaviour) {

    private val mFragmentList: MutableList<Fragment> by lazy { ArrayList<Fragment>() }
    private val mFragmentTitleList: MutableList<String> by lazy { ArrayList<String>() }

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount() = mFragmentList.size

    override fun getPageTitle(position: Int) = mFragmentTitleList.get(position)

    fun indexOf(title: String) = mFragmentTitleList.indexOf(title)

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }
}