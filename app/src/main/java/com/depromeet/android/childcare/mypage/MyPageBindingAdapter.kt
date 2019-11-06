package com.depromeet.android.childcare.mypage

import android.graphics.Color
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.depromeet.android.childcare.R
import com.depromeet.android.childcare.util.getBoldText
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor

@BindingAdapter("bind_all_text", "bind_bold_text", requireAll = true)
fun AppCompatTextView.setTextWithBold(allText: String, boldText: String) {
    text = getBoldText(allText, boldText)
}

@BindingAdapter("bind_move_animation")
fun LottieAnimationView.setMoveAnimation(animResId: Int) {
    val animation: Animation = AnimationUtils.loadAnimation(context, animResId)
    startAnimation(animation)
}

@BindingAdapter("bind_month", "bind_bar_data", requireAll = true)
fun AvgBarChartView.setBarData(months: List<String>, avgConsumptions: List<Float>) {
    val entries = mutableListOf<BarEntry>()
    if (months.size != 6 && avgConsumptions.size != 6) {
        return
    }

    for (index in months.indices) {
        entries.add(BarEntry(index.toFloat(), avgConsumptions[index]))
    }

    val set = BarDataSet(entries, "Bar DataSet").apply {
        val startColor = Color.WHITE
        val endColor = ContextCompat.getColor(context, R.color.colorSky)
        val colors = listOf(
            GradientColor(startColor, endColor),
            GradientColor(startColor, endColor),
            GradientColor(startColor, endColor),
            GradientColor(startColor, endColor),
            GradientColor(startColor, endColor),
            GradientColor(startColor, endColor)
        )
        gradientColors = colors
        setDrawValues(false)
    }

    val dataSets = mutableListOf<IBarDataSet>(set)
    dataSets.add(set)

    val barData = BarData(dataSets).apply {
        this.barWidth = 0.08f
        data = this
    }

    with(xAxis) {
        axisMinimum = -0.04f
        axisMaximum = barData.xMax + 0.04f
        labelCount = months.size
        valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return months[value.toInt() % months.size] + "월"
            }
        }
    }

    animateY(1000)
    invalidate()
}

@BindingAdapter("bind_month", "bind_line_data", requireAll = true)
fun AvgLineChartView.setLineData(months: List<String>, avgConsumptions: List<Float>) {
    val entries = mutableListOf<Entry>()
    if (months.size != 6 && avgConsumptions.size != 6) {
        return
    }

    for (index in months.indices) {
        entries.add(Entry(index.toFloat(), avgConsumptions[index]))
    }

    val lineSet = LineDataSet(entries, "Line DataSet").apply {
        color = ContextCompat.getColor(context, R.color.colorSky)
        setCircleColor(ContextCompat.getColor(context, R.color.colorBlue))
        lineWidth = 0.5f
        circleRadius = 2f
        setDrawCircleHole(false)
        mode = LineDataSet.Mode.LINEAR
        setDrawValues(false)
        axisDependency = YAxis.AxisDependency.LEFT
    }

    val dataSets = LineData().apply {
        addDataSet(lineSet)
        data = this
    }

    with(xAxis) {
        axisMinimum = -0.04f
        axisMaximum = dataSets.xMax + 0.04f
        labelCount = months.size
    }

    val animation: Animation = AnimationUtils.loadAnimation(context, R.anim.mypage_linechart_alpha)
    setAnimation(animation)
    invalidate()
}

@BindingAdapter("bind_month", "bind_line_avg_data", "bind_line_data", requireAll = true)
fun AvgLineChartView.setLineAvgData(months: List<String>, avgConsumption: Float, avgConsumptions: List<Float>) { // Y 범위가 달라져 구래프 위치가 이상해짐... 따라서 dummy로 같은 데이터를 넣음
    val entries = mutableListOf<Entry>()
    val dummyEntries = mutableListOf<Entry>()
    if (months.size != 6) {
        return
    }

    for (index in months.indices) {
        entries.add(Entry(index.toFloat(), avgConsumption))
        dummyEntries.add(Entry(index.toFloat(), avgConsumptions[index]))
    }

    val lineSet = LineDataSet(entries, "Line DataSet").apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        lineWidth = 0.5f
        setDrawCircles(false)
        mode = LineDataSet.Mode.LINEAR
        enableDashedLine(3f, 3f, 0f)
        setDrawValues(false)
        axisDependency = YAxis.AxisDependency.LEFT
    }
    val dummyLineSet = LineDataSet(dummyEntries, "Dummy Line DataSet").apply {
        color = Color.TRANSPARENT
        mode = LineDataSet.Mode.LINEAR
        setDrawCircles(false)
        setDrawValues(false)
        axisDependency = YAxis.AxisDependency.LEFT
    }

    val dataSets = LineData().apply {
        addDataSet(dummyLineSet)
        addDataSet(lineSet)
        data = this
    }

    with(xAxis) {
        axisMinimum = -0.04f
        axisMaximum = dataSets.xMax + 0.04f
        labelCount = months.size
    }

    animateX(1000, Easing.EaseInCubic)
    invalidate()
}

@BindingAdapter("bind_category", "bind_bar_data", requireAll = true)
fun CategoryChartView.setBarData(categoryList: List<String>, avgConsumptions: List<Float>) {
    val entries = mutableListOf<BarEntry>()
    if (categoryList.size != 5 && avgConsumptions.size != 5) {
        return
    }

    for (index in categoryList.indices) {
        entries.add(BarEntry(index.toFloat(), avgConsumptions[index]))
    }

    val set = BarDataSet(entries, "Bar DataSet").apply {
        val grayColor = ContextCompat.getColor(context, R.color.colorChartGray)

        val colors = listOf(
            grayColor,
            grayColor,
            grayColor,
            grayColor,
            ContextCompat.getColor(context, R.color.colorSky)
        )
        setColors(colors)
        setDrawValues(false)
    }

    val dataSets = mutableListOf<IBarDataSet>(set)
    dataSets.add(set)

    BarData(dataSets).apply {
        this.barWidth = 0.5f
        data = this
    }

    with(xAxis) {
        labelCount = categoryList.size
        valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return categoryList[value.toInt() % categoryList.size]
            }
        }
    }

    animateY(2000)
    invalidate()
}