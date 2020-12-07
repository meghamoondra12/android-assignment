package com.example.assignment.model

import io.reactivex.subjects.PublishSubject

class RxBaseErrorResponse<T>(val throwable: Throwable?, val publishSubject: PublishSubject<Int>)