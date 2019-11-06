package com.depromeet.android.childcare.mypage

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis

class AvgLineChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): LineChart(context, attrs, defStyleAttr) {

    init {
        description.isEnabled = false
        setBackgroundColor(Color.argb(0, 250, 250, 250))
        setDrawGridBackground(false)
        setTouchEnabled(false)
        setScaleEnabled(false)
        isDragEnabled = false

        with(legend) {
            isEnabled = false
        }

        with(axisRight) {
            setDrawGridLines(false)
            setDrawAxisLine(false)
            setDrawLabels(false)
            axisMinimum = 0f // this replaces setStartAtZero(true)
        }

        with(axisLeft) {
            setDrawGridLines(false)
            setDrawAxisLine(false)
            setDrawLabels(false)
            axisMinimum = 0f // this replaces setStartAtZero(true)
        }

        with(xAxis) {
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1f
            textColor = Color.TRANSPARENT
            setDrawGridLines(false)
            setDrawAxisLine(false)
        }
    }
}