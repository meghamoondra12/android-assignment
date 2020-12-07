package com.example.assignment.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

fun <T> io.reactivex.rxjava3.core.Single<T>.applySchedulerWithRetryRxJava3(
    retryFunction: io.reactivex.rxjava3.functions.Function<io.reactivex.rxjava3.core.Flowable<out Throwable>, io.reactivex.rxjava3.core.Flowable<*>>
): io.reactivex.rxjava3.core.Single<T> = subscribeOn(
    io.reactivex.rxjava3.schedulers.Schedulers.io()
).observeOn(AndroidSchedulers.mainThread()).retryWhen(retryFunction)
