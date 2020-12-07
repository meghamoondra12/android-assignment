package com.example.assignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment.dao.IBaseDao
import com.example.assignment.model.*
import com.example.assignment.network.IBaseApi
import com.example.assignment.utils.applySchedulerWithRetryRxJava3
import com.example.assignment.view.MainActivity2.Companion.BREED_ONE_ID
import com.example.assignment.view.MainActivity2.Companion.BREED_THREE_ID
import com.example.assignment.view.MainActivity2.Companion.BREED_TWO_ID
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class Main2Repository @Inject constructor(private val mIBaseApi: IBaseApi) : BaseRepository() {

    @Inject
    lateinit var mIBaseDao: IBaseDao

    fun callApi(breedId: Int): LiveData<RxApiResponse<List<BreadResponse>?>> {
        val mutableLiveData = MutableLiveData<RxApiResponse<List<BreadResponse>?>>()
        mCompositeDisposable.add(
            mIBaseApi.getBreads(breedId, 0, 10)
                .applySchedulerWithRetryRxJava3(retryFunctionRxJava3(mutableLiveData)).subscribe({
                    mutableLiveData.value = RxApiResponse.create(it)
                    mCompositeDisposable.add(
                        Completable.fromAction {
                            it?.let { it1 ->
                                mIBaseDao.insertAllData(
                                    BaseEntity(getBreedDataBaseId(breedId), it1)
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

    fun getResponseFromDataBase(breedId: Int): LiveData<BaseEntity> {
        return mIBaseDao.getAllData(getBreedDataBaseId(breedId))
    }

    private fun getBreedDataBaseId(breedId: Int) = when (breedId) {
        0 -> {
            BREED_ONE_ID
        }
        1 -> {
            BREED_TWO_ID
        }
        2 -> {
            BREED_THREE_ID
        }
        else -> {
            BREED_ONE_ID
        }
    }
}