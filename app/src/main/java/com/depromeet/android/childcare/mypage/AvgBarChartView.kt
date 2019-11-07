package com.depromeet.android.childcare.mypage

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.depromeet.android.childcare.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor

class AvgBarChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): BarChart(context, attrs, defStyleAttr) {

    init {
        description.isEnabled = false
        setBackgroundColor(Color.argb(0, 250, 250, 250))
        setDrawGridBackground(false)
        setDrawBarShadow(false)
        setTouchEnabled(false)
        setScaleEnabled(false)
        isDragEnabled = false
        isHighlightFullBarEnabled = false

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
            textColor = Color.BLACK
            setDrawGridLines(false)
            setDrawAxisLine(false)
        }
    }

    fun setDataAndStyle(months: List<String>, avgConsumptions: List<Float>) {
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
            this.barWidth = 0.06f
            data = this
        }

        with(xAxis) {
            axisMinimum = -0.02f
            axisMaximum = barData.xMax + 0.02f
            labelCount = months.size
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return months[value.toInt() % months.size] + "월"
                }
            }
        }
    }
}