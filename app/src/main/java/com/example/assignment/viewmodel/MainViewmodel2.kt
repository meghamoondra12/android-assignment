package com.example.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.model.*
import com.example.assignment.repository.Main2Repository
import javax.inject.Inject

class MainViewmodel2 @Inject constructor(private val mMain2Repository: Main2Repository) :
    ViewModel() {
    private fun getResponse(breedId: Int) = mMain2Repository.callApi(breedId)

    private fun getDatabaseResponse(breedId: Int) = mMain2Repository.getResponseFromDataBase(breedId)

    fun getData(breedId: Int): LiveData<Pair<RxApiResponse<List<BreadResponse>?>?, BaseEntity?>> {
        return zipResponse(getResponse(breedId), getDatabaseResponse(breedId))
    }

    private fun zipResponse(
        first: LiveData<RxApiResponse<List<BreadResponse>?>>,
        second: LiveData<BaseEntity>
    ): LiveData<Pair<RxApiResponse<List<BreadResponse>?>?, BaseEntity?>> {

        val mediatorLiveData =
            MediatorLiveData<Pair<RxApiResponse<List<BreadResponse>?>?, BaseEntity?>>()
        var isFirstEmitted = false
        var isSecondEmitted = false
        var firstValue: RxApiResponse<List<BreadResponse>?>? = null
        var secondValue: BaseEntity? = null
        mediatorLiveData.addSource(first) {
            isFirstEmitted = true
            firstValue = it
            if (isSecondEmitted) {
                mediatorLiveData.value = Pair(firstValue, secondValue)
            }
        }

        mediatorLiveData.addSource(second) {
            isSecondEmitted = true
            secondValue = it
            if (isFirstEmitted) {
                mediatorLiveData.value = Pair(firstValue, secondValue)
            }
        }
        return mediatorLiveData
    }
}