package com.example.assignment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.adapter.ListSetAdapter
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.model.NewYorkResponse
import com.example.assignment.model.RxApiSuccessResponse
import com.example.assignment.qualifiers.ViewModelInjection
import com.example.assignment.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    @ViewModelInjection
    lateinit var viewModel: MainViewModel

    private lateinit var mActivityMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mActivityMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMain.root)
        setRecyclerview()
    }

    private fun setRecyclerview() {
        viewModel.getData().observe(this, Observer {
            if (it.first?.isApiCallSuccess == true) {
                val response = it.first as? RxApiSuccessResponse<NewYorkResponse>
                response?.body?.results?.let { list ->
                    with(mActivityMain) {
                        rvList.layoutManager = LinearLayoutManager(this@MainActivity)
                        rvList.adapter = ListSetAdapter(list)
                    }
                }
            } else if (null != it.second) {
                it.second?.list?.let { list ->
                    with(mActivityMain) {
                        rvList.layoutManager = LinearLayoutManager(this@MainActivity)
                        rvList.adapter = ListSetAdapter(list)
                    }
                }
            }
        })
    }
}