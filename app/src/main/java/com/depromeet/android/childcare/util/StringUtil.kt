package com.depromeet.android.childcare.util

import java.text.DecimalFormat

// 숫자에 3자리 단위로 comma 삽입
fun insertComma(num: Int): String = DecimalFormat("#,###").format(num)