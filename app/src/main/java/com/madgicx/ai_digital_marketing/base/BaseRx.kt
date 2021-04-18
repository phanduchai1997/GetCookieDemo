package com.madgicx.ai_digital_marketing.base

import com.blankj.utilcode.util.Utils
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BaseRx<RX> {

    fun create(baseRxCallBack: BaseRxCallBack<RX>): Disposable {
        return Observable.create<RX> { emitter ->
            baseRxCallBack.onSubscribe(emitter)
            emitter.onComplete()
        }
            .subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread())
            .subscribe(
                { data: RX -> Utils.runOnUiThread { baseRxCallBack.onResponse(data) } },
                { throwable: Throwable -> Utils.runOnUiThread { baseRxCallBack.onError(throwable) } })
    }

    fun callApi(baseRxCallBack: BaseRxCallBackCallApi<RX>): Disposable {
        return baseRxCallBack.onFun()
            .subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(
                { data: RX -> Utils.runOnUiThread { baseRxCallBack.onResponse(data) } },
                { throwable: Throwable -> Utils.runOnUiThread { baseRxCallBack.onError(throwable) } })
    }

    interface BaseRxCallBackCallApi<RX> {

        fun onResponse(result: RX)

        fun onFun(): Observable<RX>

        fun onError(e: Throwable)
    }

    interface BaseRxCallBack<RX> {

        fun onSubscribe(emitter: ObservableEmitter<RX>)

        fun onResponse(result: RX)

        fun onError(e: Throwable)
    }
}