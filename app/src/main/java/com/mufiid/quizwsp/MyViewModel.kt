package com.mufiid.quizwsp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mufiid.quizwsp.api.ApiClient
import com.mufiid.quizwsp.models.City
import com.mufiid.quizwsp.models.Cost
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MyViewModel : ViewModel() {
    private val api = ApiClient.instance()
    private val listCity = MutableLiveData<List<City>>()
    private val listCost = MutableLiveData<List<Cost>>()
    private val token = "309b090c134156518ea9cc94175e2eb3"

    fun setCity() {
        CompositeDisposable().add(
            api.getCity("$token")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listCity.postValue(it.rajaongkir?.results)
                }, {
                    Log.d("ERROR", it.message!!)
                })
        )
    }

    fun checkCost(
        origin: Int?, destination: Int?, weight: Int?, courier: String?
    ) {
        CompositeDisposable().add(
            api.getCost("$token", origin, destination, weight, courier)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listCost.postValue(it.rajaongkir?.results?.get(0)?.costs as List<Cost>?)
                }, {
                    Log.d("ERROR", it.message!!)
                })
        )
    }

    fun getCity(): LiveData<List<City>> {
        return listCity
    }

    fun getCost(): LiveData<List<Cost>> {
        return listCost
    }
}