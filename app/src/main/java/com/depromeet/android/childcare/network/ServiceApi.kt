package com.depromeet.android.childcare.network

import com.depromeet.android.childcare.model.request.ConnectCoupleRequest
import com.depromeet.android.childcare.model.request.LoginRequest
import com.depromeet.android.childcare.model.response.ConnectCoupleResponse
import com.depromeet.android.childcare.model.response.GetExpendituresResponse
import com.depromeet.android.childcare.model.response.LoginResponse
import com.depromeet.android.childcare.model.response.MyInfoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceApi {

    @POST("api/members/login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @POST("api/members/me/connect")
    fun connectCouple(
        @Body connectCoupleRequest: ConnectCoupleRequest
    ): Call<ConnectCoupleResponse>

    @GET("api/members/me")
    fun getMyInfo(): Call<MyInfoResponse>

    @GET("api/expenditures")
    fun getExpendituresAll(): Call<GetExpendituresResponse>
}