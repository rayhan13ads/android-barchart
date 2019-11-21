package com.example.mychart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class MpchartActivity : AppCompatActivity() {

    lateinit var barChart:BarChart
    lateinit var ems:ArrayList<MonthSales>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mpchart)
        initwidget()
        setData()
    }

    fun initwidget(){
        barChart = findViewById(R.id.chart1)
    }



    fun setData(){
        var salesDatas = ArrayList<BarEntry>()
        var labels = ArrayList<String>()
        var ems = ArrayList<MonthSales>()


        ems.add(MonthSales("January", 5000))
        ems.add(MonthSales("February", 6000))
        ems.add(MonthSales("March", 8000))
        ems.add(MonthSales("April", 3000))
        ems.add(MonthSales("May", 9000))
        ems.add(MonthSales("June", 4000))
        ems.add(MonthSales("July", 9000))
        ems.add(MonthSales("August", 2000))
        ems.add(MonthSales("September", 1000))
        ems.add(MonthSales("October", 6000))
        ems.add(MonthSales("November", 3000))
        ems.add(MonthSales("December", 7000))



        for (i in ems.indices){
            var p = i.toFloat()
            var month = ems.get(i).month
            var sale = ems.get(i).sale.toFloat()
            salesDatas.add(BarEntry(p,sale))
            labels.add(month)
        }

        val barDataSet  = BarDataSet(salesDatas, "Month seals")
        val description = Description()
        description.text = "Months"
        barChart.description = description
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.asList()
        val barData = BarData(barDataSet)
        barChart.data = barData

       var xAxis = barChart.xAxis
        var indexLabels = IndexAxisValueFormatter(labels)
        xAxis.valueFormatter = indexLabels
        xAxis.position =XAxis.XAxisPosition.TOP
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f
        xAxis.labelCount = labels.size
        xAxis.labelRotationAngle = 270f
        barChart.animateY(2000)
        barChart.invalidate()
    }



}
