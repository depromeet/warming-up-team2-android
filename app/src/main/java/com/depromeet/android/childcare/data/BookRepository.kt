package com.depromeet.android.childcare.data

import com.depromeet.android.childcare.model.*
import com.depromeet.android.childcare.network.ServiceApi

class BookRepository(private val service: ServiceApi) : BookDataSource {
    private val recordTestValue = mutableListOf<Record>()
    private val summaryTestValue = mutableListOf<Summary>()

    init {
        for (i in 5..10) {
            recordTestValue.add(
                Record(
                    i,
                    User(999, "https://avatars3.githubusercontent.com/u/18240792?s=200&v=4", "디프만"),
                    RecordType.PAYMENT,
                    "2019-$i-4",
                    "라꾸라꾸 유모차",
                    5355534,
                    "용품",
                    PaymentType.CARD,
                    "https://www.shutterstock.com/ko/blog/wp-content/uploads/sites/17/2019/03/91.jpg",
                    "유튜브, 인스타그램, 페이스북 그리고 네이버 세상엔 스마트폰으로 볼 것만도 차고 넘치는 그런 시대다."
                )
            )
        }
        for (i in 5..10) {
            summaryTestValue.add(Summary(2019, i, 10000 * i, 1, 7))
        }
    }

    override fun getAllRecords(success: (List<Record>) -> Unit, failed: (String, String?) -> Unit) {
        recordTestValue.run(success)
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
}