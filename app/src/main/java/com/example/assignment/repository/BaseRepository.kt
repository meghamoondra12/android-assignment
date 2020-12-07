package com.example.assignment.repository

import androidx.lifecycle.MutableLiveData
import com.example.assignment.model.RxApiResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.subjects.PublishSubject


abstract class BaseRepository {

    protected val mCompositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    /**
     * Use this retry function in case for making API calls via live data pattern
     */
    protected fun <T> retryFunctionRxJava3(
        liveData: MutableLiveData<RxApiResponse<T>>
    ): io.reactivex.rxjava3.functions.Function<Flowable<out Throwable>, Flowable<*>> {
        return Function { observable ->
            observable.flatMap {
                val subject = PublishSubject.create<Int>()
                liveData.postValue(RxApiResponse.create<T>(it, subject))
                subject.toFlowable(BackpressureStrategy.LATEST)
            }
        }
    }

    fun cancelAllApiCalls() {
        mCompositeDisposable.clear()
    }
}