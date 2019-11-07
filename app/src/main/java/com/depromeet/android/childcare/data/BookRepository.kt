package com.depromeet.android.childcare.data

import com.depromeet.android.childcare.model.*
import com.depromeet.android.childcare.network.ServiceApi
import com.depromeet.android.childcare.network.retrofitCallback

class BookRepository(
    private val service: ServiceApi
) : BookDataSource {

    private var myInfo: User? = null
    private var spouseInfo: User? = null

    private val recordTestValue = mutableListOf<Record>()
    private val summaryTestValue = mutableListOf<Summary>()
    private val categoryTextValue = mutableListOf<String>("미등록", "육아용품", "유흥")

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

    override fun connectSpouse(
        myCode: String,
        success: () -> Unit,
        failed: (String?) -> Unit
    ) {
        service.connectCouple(ConnectCoupleRequest(myCode)).enqueue(
            retrofitCallback { response, throwable ->
                // Todo: 나중에 헤더보고 에러처리는 필수
                throwable?.let {
                    failed(it.message)
                    Log.e("Error!!: ", "sfasdfsaf // " + throwable.message)
                }

                response?.let {
                    if(response.code() != 200) {
                        failed(it.message())
                        return@retrofitCallback
                    }

                    it.body()?.let {connectResponse ->

                        // 현재 myinfo 는 딱히 다른데서 안쓰므로 미리 저장해도 될 듯 싶다.
                        myInfo = User(connectResponse.data.me.id,
                            connectResponse.data.me.profileImageUrl,
                            connectResponse.data.me.name,
                            connectResponse.data.me.connectionCode)

                        spouseInfo = User(connectResponse.data.spouse.id,
                            connectResponse.data.spouse.profileImageUrl,
                            connectResponse.data.spouse.name,
                            connectResponse.data.spouse.connectionCode)
                    }

                    // 저장에 실패해도 다시 불러오면 되므로 여기서 success
                    success()
                    return@retrofitCallback
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
            }

            response?.let { it ->
                if(response.code() != 200) {
                    failed(it.message())
                    return@retrofitCallback
                }

                it.body()?.let { myInfoResponse ->
                    myInfo = User(myInfoResponse.data.id,
                        myInfoResponse.data.profileImageUrl,
                        myInfoResponse.data.name,
                        myInfoResponse.data.connectionCode)
                    // Todo: 나중에 추가 필요
                    spouseInfo = null
                    success(myInfo!!, spouseInfo)
                    return@retrofitCallback
                }
            }

            failed("Unkown Error")
        })
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

    override fun getCategories(success: (List<String>) -> Unit, failed: (String, String?) -> Unit) {
        categoryTextValue.run(success)
    }
}