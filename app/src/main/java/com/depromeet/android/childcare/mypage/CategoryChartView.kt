package com.depromeet.android.childcare.mypage

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.depromeet.android.childcare.R
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

class CategoryChartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): HorizontalBarChart(context, attrs, defStyleAttr) {

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

        CategoryChartViewRenderer(this, this.animator, this.viewPortHandler).apply {
            mRadius = 10f
            this@CategoryChartView.renderer = this
        }
    }

    fun setDataAndStyle(categoryList: List<String>, avgConsumptions: List<Float>) {
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
    }
}