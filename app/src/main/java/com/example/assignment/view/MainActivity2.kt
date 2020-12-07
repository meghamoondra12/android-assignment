package com.example.assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.assignment.R
import com.example.assignment.adapter.ListFragmentAdapter
import com.example.assignment.databinding.ActivityMain2Binding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity2 : AppCompatActivity(), HasAndroidInjector {

    private lateinit var mActivity2Main: ActivityMain2Binding

    private var mViewPagerFragment1: ViewPagerFragment? = null
    private var mViewPagerFragment2: ViewPagerFragment? = null
    private var mViewPagerFragment3: ViewPagerFragment? = null

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    companion object {
        const val BREED_ONE_ID = 11
        const val BREED_TWO_ID = 22
        const val BREED_THREE_ID = 33
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mActivity2Main = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(mActivity2Main.root)
        initLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflator: MenuInflater = menuInflater
        menuInflator.inflate(R.menu.grid_linear_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_linear -> {
                mViewPagerFragment1?.isGridView = false
                mViewPagerFragment2?.isGridView = false
                mViewPagerFragment3?.isGridView = false
                return true
            }
            R.id.action_grid -> {
                mViewPagerFragment1?.isGridView = true
                mViewPagerFragment2?.isGridView = true
                mViewPagerFragment3?.isGridView = true
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initLayout() {
        mActivity2Main.vpPager.offscreenPageLimit = 2
        mActivity2Main.vpPager.adapter = ListFragmentAdapter(
            supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ).apply {
            mViewPagerFragment1 = ViewPagerFragment.newInstance(0)
            mViewPagerFragment1?.let {
                addFragment(
                    it, "Breed 1"
                )
            }
            mViewPagerFragment2 = ViewPagerFragment.newInstance(1)
            mViewPagerFragment2?.let {
                addFragment(
                    it, "Breed 2"
                )
            }
            mViewPagerFragment3 = ViewPagerFragment.newInstance(2)
            mViewPagerFragment3?.let {
                addFragment(
                    it, "Breed 1"
                )
            }
        }
        mActivity2Main.tlLayout.setupWithViewPager(mActivity2Main.vpPager)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}