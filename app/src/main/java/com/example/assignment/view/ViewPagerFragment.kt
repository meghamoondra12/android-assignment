package com.example.assignment.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.assignment.adapter.BreedListAdapter
import com.example.assignment.databinding.FragmentViewPagerBinding
import com.example.assignment.model.BreadResponse
import com.example.assignment.model.NewYorkResponse
import com.example.assignment.model.RxApiSuccessResponse
import com.example.assignment.qualifiers.ViewModelInjection
import com.example.assignment.viewmodel.MainViewmodel2
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ViewPagerFragment : Fragment() {

    @Inject
    @ViewModelInjection
    lateinit var mMainViewmodel2: MainViewmodel2

    private var _fragmentBinding: FragmentViewPagerBinding? = null

    private val mFragmentBinding get() = _fragmentBinding!!

    var isGridView: Boolean = true
        set(value) {
            field = value
            val layoutManager = getListViewGridLayoutManager()
            mFragmentBinding.rvList.layoutManager = layoutManager
        }

    companion object {
        private const val BREED_ID = "BREED_ID"
        fun newInstance(breedId: Int): ViewPagerFragment {
            return ViewPagerFragment().apply {
                arguments = Bundle().apply {
                    putInt(BREED_ID, breedId)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentBinding = FragmentViewPagerBinding.inflate(layoutInflater)
        return mFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseUI()
    }

    private fun initialiseUI() {
        mMainViewmodel2.getData(arguments?.getInt(BREED_ID) ?: 0).observe(this, Observer {
            if (it.first?.isApiCallSuccess == true) {
                val response = it.first as? RxApiSuccessResponse<List<BreadResponse>?>
                response?.body?.let { list ->
                    with(mFragmentBinding) {
                        rvList.layoutManager = LinearLayoutManager(context)
                        rvList.adapter = BreedListAdapter(list)
                    }
                }
            } else if (null != it.second) {
                it.second?.list?.let { list ->
                    with(mFragmentBinding) {
                        rvList.layoutManager =
                            LinearLayoutManager(context)
                        rvList.adapter = BreedListAdapter(list)
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentBinding = null
    }

    private fun getListViewGridLayoutManager(): GridLayoutManager {
        val maxSpanCount = if (isGridView) 2 else 1
        return GridLayoutManager(context, maxSpanCount)
    }

}