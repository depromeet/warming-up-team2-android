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
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
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

@BindingAdapter("bind_month", "bind_bar_data")
fun AvgBarChartView.setBarData(months: List<String>, avgConsumptions: List<Float>) {
    val entries = mutableListOf<BarEntry>()
    if (months.size != avgConsumptions.size) {
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

    invalidate()
}