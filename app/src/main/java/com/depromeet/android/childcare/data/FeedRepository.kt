package com.depromeet.android.childcare.data

import com.depromeet.android.childcare.model.PaymentType
import com.depromeet.android.childcare.model.Record
import com.depromeet.android.childcare.model.User

class FeedRepository: FeedDataSource {

    val list = mutableListOf<Record>()

    init {
        for (i in 1..15) {
            Record(
                i,
                User(999, "https://avatars3.githubusercontent.com/u/18240792?s=200&v=4", "디프만"),
                "PAYMENT",
                "1566931645000",
                "라꾸라꾸 유모차",
                5355534,
                "용품",
                PaymentType.CARD,
                "https://www.shutterstock.com/ko/blog/wp-content/uploads/sites/17/2019/03/91.jpg",
                "유튜브, 인스타그램, 페이스북 그리고 네이버 세상엔 스마트폰으로 볼 것만도 차고 넘치는 그런 시대다. 그런데 그렇게 봐도 왜 그렇게 맘은 허전한지 알수가 없다. 회사일, 시험, 면접, 앞에는 넘어야 할 일들 투성이고, 시간관리 앱은 도통 몇개 를 설치해야 나만의 시간을 만들 수 있는걸까?"
            ).run {
                list.add(this)
            }
        }
    }

    override fun getAllFeeds(
        success: (MutableList<Record>) -> Unit,
        failed: () -> Unit
    ) {
        success(list)
    }
}