package com.example.assignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment.dao.IDao
import com.example.assignment.model.NewYorkResponse
import com.example.assignment.model.ResponseEntity
import com.example.assignment.model.RxApiResponse
import com.example.assignment.network.IGenericApi
import com.example.assignment.utils.applySchedulerWithRetryRxJava3
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainRepository @Inject constructor(private val mIGenericApi: IGenericApi) : BaseRepository() {

    @Inject
    lateinit var mIDao: IDao

    fun callApi(): LiveData<RxApiResponse<NewYorkResponse>> {
        val mutableLiveData = MutableLiveData<RxApiResponse<NewYorkResponse>>()
        mCompositeDisposable.add(
            mIGenericApi.getPopularStories("GWdwS2FFVIfjviTZ52HnVklIAP1UmdAv")
                .applySchedulerWithRetryRxJava3(retryFunctionRxJava3(mutableLiveData)).subscribe({
                    mutableLiveData.value = RxApiResponse.create(it)
                    mCompositeDisposable.add(
                        Completable.fromAction {
                            it.results?.let { it1 ->
                                mIDao.insertAllData(
                                    ResponseEntity(list = it1)
                                )
                            }
                        }.subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread()).subscribe({}, {})
                    )
                }, {
                    val subject = PublishSubject.create<Int>()
                    mutableLiveData.postValue(RxApiResponse.create(it, subject))
                })
        )
        return mutableLiveData
    }

    fun getResponseFromDataBase(): LiveData<ResponseEntity> {
        return mIDao.getAllData(1111111111)
    }
}