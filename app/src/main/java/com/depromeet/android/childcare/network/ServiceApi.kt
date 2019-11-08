package com.depromeet.android.childcare.network

import com.depromeet.android.childcare.model.request.ConnectCoupleRequest
import com.depromeet.android.childcare.model.request.CreateRecordRequest
import com.depromeet.android.childcare.model.request.LoginRequest
import com.depromeet.android.childcare.model.response.ConnectCoupleResponse
import com.depromeet.android.childcare.model.response.CreateRecordResponse
import com.depromeet.android.childcare.model.response.LoginResponse
import com.depromeet.android.childcare.model.response.MyInfoResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ServiceApi {

    // 로그인 API
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

    // 지출 내역 API
    @POST("api/expenditures")
    fun createNewRecord(@Body data: CreateRecordRequest): Call<CreateRecordResponse>

    @PUT("api/expenditures/{expenditureId}")
    fun editRecord(
        @Path("expenditureId") id: Int,
        @Body data: CreateRecordRequest
    ): Call<CreateRecordResponse>

    @POST("api/expenditures/{expenditureId}/upload-image")
    fun uploadImage(
        @Path("expenditureId") id: Int,
        @Part filePart: MultipartBody.Part
    ): Call<CreateRecordRequest>
}