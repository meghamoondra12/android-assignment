package com.example.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment.model.NewYorkResponse
import com.example.assignment.model.ResponseEntity
import com.example.assignment.model.RxApiResponse
import com.example.assignment.repository.MainRepository
import java.util.HashMap
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mMainRepository: MainRepository) : ViewModel() {

    private fun getResponse() = mMainRepository.callApi()

    private fun getDatabaseResponse() = mMainRepository.getResponseFromDataBase()

    fun getData(): LiveData<Pair<RxApiResponse<NewYorkResponse>?, ResponseEntity?>> {
        return zipResponse(getResponse(), getDatabaseResponse())
    }

    private fun zipResponse(
        first: LiveData<RxApiResponse<NewYorkResponse>>,
        second: LiveData<ResponseEntity>
    ): LiveData<Pair<RxApiResponse<NewYorkResponse>?, ResponseEntity?>> {

        val mediatorLiveData =
            MediatorLiveData<Pair<RxApiResponse<NewYorkResponse>?, ResponseEntity?>>()
        var isFirstEmitted = false
        var isSecondEmitted = false
        var firstValue: RxApiResponse<NewYorkResponse>? = null
        var secondValue: ResponseEntity? = null
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