package com.mufiid.quizwsp.api

import com.mufiid.quizwsp.responses.CityResponse
import com.mufiid.quizwsp.responses.CostResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface IApiService {

    @GET("city")
    fun getCity(
        @Header("key") key: String?
    ): Observable<CityResponse>

    @FormUrlEncoded
    @POST("cost")
    fun getCost(
        @Header("key") key: String?,
        @Field("origin") originId: Int?,
        @Field("destination") destinationId: Int?,
        @Field("weight") weight: Int?,
        @Field("courier") courier: String?
    ): Observable<CostResponse>
}