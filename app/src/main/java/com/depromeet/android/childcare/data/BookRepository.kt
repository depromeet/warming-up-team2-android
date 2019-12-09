package com.depromeet.android.childcare.data

import android.net.Uri
import com.depromeet.android.childcare.model.*
import com.depromeet.android.childcare.model.request.ConnectCoupleRequest
import com.depromeet.android.childcare.model.request.CreateRecordRequest
import com.depromeet.android.childcare.model.response.CreateRecordResponse
import com.depromeet.android.childcare.network.ServiceApi
import com.depromeet.android.childcare.network.retrofitCallback
import com.depromeet.android.childcare.util.convertToString
import com.depromeet.android.childcare.util.toDate
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.*


class BookRepository(
    private val service: ServiceApi
) : BookDataSource {

    private var myInfo: User? = null
    private var spouseInfo: User? = null
    private val recordTestValue = mutableListOf<Record>()
    private val summaryTestValue = mutableListOf<Summary>()
    private val categoryTextValue = mutableListOf<String>("미등록", "육아용품", "유흥")

    init {
        for (i in 1..12) {
            for (j in 1..10) {
                recordTestValue.add(
                    Record(
                        i,
                        User(999, "https://avatars3.githubusercontent.com/u/18240792?s=200&v=4", "디프만", "A123456"),
                        RecordType.PAYMENT,
                        "2019-$i-$j",
                        "라꾸라꾸 유모차",
                        5355534,
                        "용품",
                        PaymentType.CARD,
                        "https://raw.githubusercontent.com/filippella/Sample-API-Files/master/images/avatars/avatar_johndoe.png",
                        "유튜브, 인스타그램, 페이스북 그리고 네이버 세상엔 스마트폰으로 볼 것만도 차고 넘치는 그런 시대다."
                    )
                )
            }
        }
        for (i in 5..10) {
            summaryTestValue.add(Summary(2019, i, 10000 * i, 1, 7))
        }
    }

    override fun connectSpouse(
        myCode: String,
        success: (User?) -> Unit,
        failed: (String?) -> Unit
    ) {
        service.connectCouple(ConnectCoupleRequest(myCode)).enqueue(
            retrofitCallback { response, throwable ->
                // Todo: 나중에 헤더보고 에러처리는 필수
                throwable?.let {
                    failed(it.message)
                    return@retrofitCallback
                }

                response?.let {
                    if (response.code() != 200) {
                        failed(it.message())
                        return@retrofitCallback
                    }

                    it.body()?.let { connectResponse ->

                        // 현재 myinfo 는 딱히 다른데서 안쓰므로 미리 저장해도 될 듯 싶다.
                        myInfo = connectResponse.meToUser()
                        spouseInfo = connectResponse.spouseToUser()
                        success(spouseInfo)
                        return@retrofitCallback
                    }
                }
                failed("Unkown Error")
            })
    }

    override fun getMyInfo(
        success: (User, User?) -> Unit,
        failed: (String?) -> Unit
    ) {

        // 이미 저장되어 있다면 있는걸 리턴
        if (myInfo != null && spouseInfo != null) {
            success(myInfo!!, spouseInfo)
            return
        }

        service.getMyInfo().enqueue(retrofitCallback { response, throwable ->
            throwable?.let {
                failed(throwable.message)
                return@retrofitCallback
            }

            response?.let { it ->
                if (response.code() != 200) {
                    failed(it.message())
                    return@retrofitCallback
                }

                it.body()?.let { myInfoResponse ->
                    myInfo = myInfoResponse.meToUser()
                    spouseInfo = myInfoResponse.spouseToUser()
                    success(myInfo!!, spouseInfo)
                    return@retrofitCallback
                }
            }

            failed("Unkown Error")
        })
    }

    override fun getAllRecords(success: (List<Record>) -> Unit, failed: (String, String?) -> Unit) {
        service.getExpendituresAll().enqueue(retrofitCallback { response, throwable ->
            throwable?.let {
                failed("Error", throwable.message)
                return@retrofitCallback
            }

            response?.let { it ->
                if (response.code() != 200) {
                    failed("Error", it.message())
                    return@retrofitCallback
                }

                it.body()?.let { getExpendituresResponse ->
                    success(getExpendituresResponse.toRecords())
                    return@retrofitCallback
                }
            }

            failed("Unkown Error", "Unkown Error")
        })
    }

    override fun getRecordsByMonth(month: Int, success: (List<Record>) -> Unit, failed: (String, String?) -> Unit) {
        recordTestValue.filter { it.date.contains("-$month-") }.run(success)
    }

    override fun getRecordsByMonthAndCategory(
        month: Int,
        category: String,
        success: (List<Record>) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        recordTestValue.filter { it.category == category && it.date.contains("-$month-") }.run(success)
    }

    override fun getSummaries(success: (List<Summary>) -> Unit, failed: (String, String?) -> Unit) {
        summaryTestValue.run(success)
    }

    override fun getCategories(success: (List<String>) -> Unit, failed: (String, String?) -> Unit) {
        categoryTextValue.run(success)
    }

    override fun getExpenditureStatistics(
        success: (List<String>, List<Float>, Float) -> Unit,
        failed: (String?) -> Unit
    ) {
        service.getExpendituresStatistic().enqueue(retrofitCallback { response, throwable ->
            throwable?.let {
                failed(throwable.message)
                return@retrofitCallback
            }

            response?.let { it ->
                if (response.code() != 200) {
                    failed(it.message())
                    return@retrofitCallback
                }

                it.body()?.let { expenditureStatistics ->

                    val months = mutableListOf<String>()
                    val consumptions = mutableListOf<Float>()
                    var avgValue = 0f
                    expenditureStatistics.data.monthlyTotalExpenditures.map {
                        it.key.toDate("yyyy-mm")?.let { convertedDate ->
                            months.add(convertedDate.convertToString("mm"))
                        }
                        consumptions.add(it.value)
                        avgValue += it.value
                    }
                    avgValue = avgValue.div(months.size)

                    if (months.size in 1..5) {
                        val needsSize = 6 - months.size
                        val lastMonth = months[months.size - 1]
                        for (i in 1 until needsSize + 1) {
                            var addedMonth: String = (lastMonth.toInt() + i).toString()
                            if (addedMonth.toInt() > 12) {
                                addedMonth = (addedMonth.toInt() - 12).toString()
                                if (addedMonth.toInt() < 10) {
                                    addedMonth = "0$addedMonth"
                                }
                            }

                            months.add(addedMonth)
                            consumptions.add(0f)
                        }
                    } else if (months.size == 0) {
                        for (i in 0 until 6) {
                            var addedMonth = (Date().convertToString("mm").toInt() + 1).toString()
                            if (addedMonth.toInt() > 12) {
                                addedMonth = (addedMonth.toInt() - 12).toString()
                                if (addedMonth.toInt() < 10) {
                                    addedMonth = "0$addedMonth"
                                }
                            }
                            months.add(addedMonth)
                            consumptions.add(0f)
                        }
                    }

                    success(months, consumptions, avgValue)

                    return@retrofitCallback
                }
            }

            failed("Unkown Error")
        })
    }

    override fun getCategoriesStatistics(
        success: (List<String>, List<Float>, String, Float) -> Unit,
        failed: (String?) -> Unit
    ) {
        service.getCategoriesStatistic().enqueue(retrofitCallback { response, throwable ->
            throwable?.let {
                failed(throwable.message)
                return@retrofitCallback
            }

            response?.let { it ->
                if (response.code() != 200) {
                    failed(it.message())
                    return@retrofitCallback
                }

                it.body()?.let { categoriesStatistics ->
                    val (categories, consumptions, mostCategoryData) = categoriesStatistics.toStatisticsData()
                    success(categories, consumptions, mostCategoryData.first, mostCategoryData.second)
                    return@retrofitCallback
                }
            }

            failed("Unkown Error")
        })
    }

    override fun createNewRecord(
        data: CreateRecordRequest,
        success: (CreateRecordResponse) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.createNewRecord(data).enqueue(retrofitCallback { response, throwable ->
            response?.body()?.let {
                success(it)
            }

            throwable?.let {
                failed("레코드 추가 오류 발생", throwable.message)
            }
        })
    }

    override fun editRecord(
        id: Int,
        data: CreateRecordRequest,
        success: (CreateRecordResponse) -> Unit,
        failed: (String, String?) -> Unit
    ) {
        service.createNewRecord(data).enqueue(retrofitCallback { response, throwable ->
            response?.body()?.let {
                success(it)
            }

            throwable?.let {
                failed("레코드 수정 오류 발생", throwable.message)
            }
        })
    }

    override fun uploadImage(
        id: Int,
        imageUri: Uri,
        success: () -> Unit,
        failed: (String, String?) -> Unit
    ) {
        val file = File(imageUri.path!!)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        service.uploadImage(id, body).enqueue(retrofitCallback { response, throwable ->
            response?.let {
                success()
            }

            throwable?.let {
                throwable.printStackTrace()
                failed("사진 업로드 오류", throwable.message)
            }
        })
    }
}