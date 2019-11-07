package com.depromeet.android.childcare.mypage

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.depromeet.android.childcare.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

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

    fun setDataAndStyle(months: List<String>, avgConsumptions: List<Float>) {
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
    }

    fun setAvgDataAndStyle(months: List<String>, avgConsumption: Float, avgConsumptions: List<Float>) {
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
    }
}