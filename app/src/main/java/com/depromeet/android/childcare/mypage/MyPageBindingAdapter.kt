package com.depromeet.android.childcare.mypage

import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.util.getBoldText
import com.github.mikephil.charting.animation.Easing

@BindingAdapter("bind_all_text", "bind_bold_text", requireAll = true)
fun AppCompatTextView.setTextWithBold(allText: String, boldText: String?) {
    boldText?.let {
        text = getBoldText(allText, it)
    }
}

@BindingAdapter("bind_animation")
fun View.setMoveAnimation(animResId: Int) {
    val animation: Animation = AnimationUtils.loadAnimation(context, animResId)
    startAnimation(animation)
}

@BindingAdapter("bind_month", "bind_bar_data", requireAll = true)
fun AvgBarChartView.setBarData(months: List<String>, avgConsumptions: List<Float>) {
    setDataAndStyle(months, avgConsumptions)
    animateY(1000)
    invalidate()
}

@BindingAdapter("bind_month", "bind_line_data", requireAll = true)
fun AvgLineChartView.setLineData(months: List<String>, avgConsumptions: List<Float>) {
    setDataAndStyle(months, avgConsumptions)
    AnimationUtils.loadAnimation(context, R.anim.mypage_linechart_alpha).apply {
            animation = this
    }
    invalidate()
}

@BindingAdapter("bind_month", "bind_line_avg_data", "bind_line_data", requireAll = true)
fun AvgLineChartView.setLineAvgData(months: List<String>, avgConsumption: Float, avgConsumptions: List<Float>) { // Y 범위가 달라져 구래프 위치가 이상해짐... 따라서 dummy로 같은 데이터를 넣음
    setAvgDataAndStyle(months, avgConsumption, avgConsumptions)
    animateX(1000, Easing.EaseInCubic)
    invalidate()
}

@BindingAdapter("bind_category", "bind_bar_data", requireAll = true)
fun CategoryChartView.setBarData(categoryList: List<String>, avgConsumptions: List<Float>) {
    setDataAndStyle(categoryList, avgConsumptions)
    Log.e("asdfasdfasf", "sadf +++ $avgConsumptions")
    animateY(1500)
    invalidate()
}