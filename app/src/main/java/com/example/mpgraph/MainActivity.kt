package com.example.mpgraph

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.green
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import com.example.mpgraph.databinding.ActivityMainBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate


class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding?=null
    private val dayData = mutableListOf<Entry>()
    private  var CHART_LABEL = "DAY_CHART"
    private var _lineDataSet = MutableLiveData(LineDataSet(dayData, CHART_LABEL))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding!!.text.setOnClickListener {
            var intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        setLineChartData()
    }

    fun setLineChartData() {

        val linevalues = ArrayList<Entry>()
        linevalues.add(Entry(20f, 0.0F))
        linevalues.add(Entry(30f, 3.0F))
        linevalues.add(Entry(40f, 2.0F))
        linevalues.add(Entry(50f, 1.0F))
        linevalues.add(Entry(60f, 8.0F))
        linevalues.add(Entry(70f, 10.0F))
        linevalues.add(Entry(80f, 1.0F))
        linevalues.add(Entry(90f, 2.0F))
        linevalues.add(Entry(100f, 5.0F))
        linevalues.add(Entry(110f, 1.0F))
        linevalues.add(Entry(120f, 20.0F))
        linevalues.add(Entry(130f, 40.0F))
        linevalues.add(Entry(140f, 50.0F))

        val linedataset = LineDataSet(linevalues, "First")
        //We add features to our chart
      //  linedataset.color = resources.getColor(R.color.purple_200)


        linedataset.color = ContextCompat.getColor(this, R.color.green)
        linedataset.valueTextColor = ContextCompat.getColor(this, R.color.black_75)
        linedataset.setDrawValues(false)
        linedataset.lineWidth = 3f
        linedataset.isHighlightEnabled = true
        linedataset.setDrawHighlightIndicators(false)
        linedataset.setDrawCircles(true)

        linedataset.circleRadius = 2f
       // linedataset.setDrawFilled(true)
        linedataset.valueTextSize = 20F

        //linedataset.fillColor = resources.getColor(R.color.black)
        linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        linedataset.setDrawFilled(true)
        linedataset.fillDrawable=ContextCompat.getDrawable(this,R.drawable.bg_spark_line)

        //linedataset.mode= LineDataSet.Mode.CUBIC_BEZIER
        //We connect our data to the UI Screen
        val data = LineData(linedataset)

        binding?.dayChart!!.data = data
        binding?.dayChart!!.xAxis.apply {
            position=XAxis.XAxisPosition.BOTTOM
        }
        binding?.dayChart!!.axisRight.isEnabled=false

      /*  binding?.dayChart!!.xAxis.apply {
            axisMinimum = 0f
            axisMaximum = 23f
            isGranularityEnabled = true
            granularity = 4f
            setDrawGridLines(false)
            setDrawAxisLine(false)
            position = XAxis.XAxisPosition.BOTTOM

           // valueFormatter = TimeValueFormatter()
          //  typeface = ResourcesCompat.getFont(context, R.font.barlow_semi_condensed_regular)
            textColor = ContextCompat.getColor(baseContext, R.color.black_75)
        }

       */

        binding?.dayChart!!.setOnChartValueSelectedListener(object : OnChartValueSelectedListener
        {
            override fun onValueSelected(e: Entry, h: Highlight?) {
                val x = e.x.toString()
                val y = e.y
                val selectedXAxisCount = x.substringBefore(".") //this value is float so use substringbefore method
                // another method shown below
                val nonFloat=binding?.dayChart!!.getXAxis().getValueFormatter().getFormattedValue(e.x)
                //if you are display any string in x axis you will get this
                Toast.makeText(baseContext, "X Data->" + x + "y data->" + y,Toast.LENGTH_LONG).show()
                Log.e("Data","Data x->"+x+"yaxis->"+y)
            }

            override fun onNothingSelected() {}
        })
        binding?.dayChart!!.getXAxis().setDrawGridLines(false);
        binding?.dayChart!!.getAxisLeft().setDrawGridLines(false);
        binding?.dayChart!!.getAxisRight().setDrawGridLines(false);
        binding?.dayChart!!.setBackgroundColor(resources.getColor(R.color.white))
        binding?.dayChart!!.animateXY(2000, 2000, Easing.EaseInCubic)

    }
}