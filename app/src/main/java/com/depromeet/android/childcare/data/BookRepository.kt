package com.depromeet.android.childcare.data

import com.depromeet.android.childcare.model.*
import com.depromeet.android.childcare.model.request.ConnectCoupleRequest
import com.depromeet.android.childcare.network.ServiceApi
import com.depromeet.android.childcare.network.retrofitCallback
import com.depromeet.android.childcare.util.convertToString
import com.depromeet.android.childcare.util.toDate
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
            recordTestValue.add(
                Record(
                    i,
                    User(999, "https://avatars3.githubusercontent.com/u/18240792?s=200&v=4", "디프만", "A123456"),
                    RecordType.PAYMENT,
                    "2019-$i-4",
                    "라꾸라꾸 유모차",
                    5355534,
                    "용품",
                    PaymentType.CARD,
                    "https://raw.githubusercontent.com/filippella/Sample-API-Files/master/images/avatars/avatar_johndoe.png",
                    "유튜브, 인스타그램, 페이스북 그리고 네이버 세상엔 스마트폰으로 볼 것만도 차고 넘치는 그런 시대다."
                )
            )
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
                        myInfo = User(
                            connectResponse.data.me.id,
                            connectResponse.data.me.profileImageUrl,
                            connectResponse.data.me.name,
                            connectResponse.data.me.connectionCode
                        )

                        spouseInfo = User(
                            connectResponse.data.spouse.id,
                            connectResponse.data.spouse.profileImageUrl,
                            connectResponse.data.spouse.name,
                            connectResponse.data.spouse.connectionCode
                        )

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
                    myInfo = User(
                        myInfoResponse.data.id,
                        myInfoResponse.data.profileImageUrl,
                        myInfoResponse.data.name,
                        myInfoResponse.data.connectionCode
                    )

                    myInfoResponse.data.spouseName?.let {
                        spouseInfo = User(
                            -1,
                            null,
                            myInfoResponse.data.spouseName,
                            ""
                        )
                    }
                    success(myInfo!!, spouseInfo)
                    return@retrofitCallback
                }
            }

            failed("Unkown Error")
        })
    }

    override fun getAllRecords(success: (List<Record>) -> Unit, failed: (String, String?) -> Unit) {
//        recordTestValue.run(success)
        service.getExpendituresAll().enqueue(retrofitCallback {response, throwable ->
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

                    val recordList = getExpendituresResponse.data.map {
                        Record(
                            it.id,
                            User(it.member.id, it.member.profileImageUrl, it.member.name, it.member.connectionCode),
                            RecordType.PAYMENT,
                            it.expendedAt,
                            it.title,
                            it.amountOfMoney,
                            it.category,
                            it.paymentMethod,
                            it.imageUrl,
                            it.description
                        )
                    }

                    success(recordList)

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
        service.getExpendituresStatistic().enqueue(retrofitCallback {response, throwable ->
            throwable?.let {
                failed(throwable.message)
                return@retrofitCallback
            }

            response?.let { it ->
                if (response.code() != 200) {
                    failed(it.message())
                    return@retrofitCallback
                }

                it.body()?.let {expenditureStatistics ->

                    val months = mutableListOf<String>()
                    val consumptions = mutableListOf<Float>()
                    var avgValue = 0f
                    expenditureStatistics.data.monthlyTotalExpenditures.map {
                        it.key.toDate("yyyy-mm")?.let { convetedDate ->
                            months.add(convetedDate.convertToString("mm"))
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

            failed( "Unkown Error")
        })
    }

    override fun getCategoriesStatistics(
        success: (List<String>, List<Float>, String, Float) -> Unit,
        failed: (String?) -> Unit
    ) {
        service.getCategoriesStatistic().enqueue(retrofitCallback {response, throwable ->
            throwable?.let {
                failed(throwable.message)
                return@retrofitCallback
            }

            response?.let { it ->
                if (response.code() != 200) {
                    failed(it.message())
                    return@retrofitCallback
                }

                it.body()?.let {categoriesStatistics ->

                    val sortedCategories = categoriesStatistics.data.categoryMap
                        .toList()
                        .sortedBy { (_, value) -> value }
                        .toMap()

                    val categories = mutableListOf<String>()
                    val consumptions = mutableListOf<Float>()
                    var mostCategory = ""
                    var mostConsumption = 0f
                    sortedCategories.map {
                        categories.add(it.key)
                        consumptions.add(it.value)
                        mostCategory = it.key
                        mostConsumption = it.value
                    }

                    // Todo 데이터가 6개로 잘 오면 삭제하도록 하자
                    if (categories.size < 5) {
                        for (i in 0 until 5 - categories.size) {
                            categories.add(0, "미분류 $i")
                            consumptions.add(0, 0f)
                        }
                    }

                    success(categories, consumptions, mostCategory, mostConsumption)
                    return@retrofitCallback
                }
            }

            failed( "Unkown Error")
        })
    }
}