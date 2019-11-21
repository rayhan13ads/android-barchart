package com.example.mychart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Sampler
import android.view.View.X
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.enums.Anchor
import com.anychart.enums.Position
import kotlinx.android.synthetic.main.activity_main.view.*

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.anychart.AnyChart.cartesian
import com.anychart.enums.HoverMode
import com.anychart.enums.TooltipPositionMode
import android.R.attr.animation
import android.content.Intent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    lateinit var chart:AnyChartView
    lateinit var cartesian:Cartesian
    lateinit var datas:MutableList<DataEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniWidget()
        createChart()
    }

    fun iniWidget(){
        chart = findViewById(R.id.any_chart_view)
        chart.setProgressBar(findViewById(R.id.progress_barId))
        cartesian = AnyChart.column()
    }

    fun createChart(){

        datas = ArrayList<DataEntry>()
        datas.add(ValueDataEntry("Rouge", 80540))
        datas.add(ValueDataEntry("Foundation", 94190))
        datas.add(ValueDataEntry("Mascara", 102610))
        datas.add(ValueDataEntry("Lip gloss", 110430))
        datas.add(ValueDataEntry("Lipstick", 128000))
        datas.add(ValueDataEntry("Nail polish", 143760))
        datas.add(ValueDataEntry("Eyebrow pencil", 170670))
        datas.add(ValueDataEntry("Eyeliner", 213210))

        val column = cartesian.column(datas)

        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0.0)
            .offsetY(5.0)
            .format("\${%Value}{groupsSeparator: }")

        cartesian.animation(true)
        cartesian.title("Top 10 Cosmetic Products by Revenue")

        cartesian.yScale().minimum(0.0)

        cartesian.yAxis(0).labels().format("\${%Value}{groupsSeparator: }")

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)

        cartesian.xAxis(0).title("Product")
        cartesian.yAxis(0).title("Revenue")

        chart.setChart(cartesian)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mpchartId -> {
                var intent = Intent(this,MpchartActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
