package com.example.odayk.mensaapp;


import android.content.Intent;
import android.graphics.Color;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Graph extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        barChart = (BarChart) findViewById(R.id.barchart);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, (int) 40f));
        barEntries.add(new BarEntry(2, (int) 44f));
        barEntries.add(new BarEntry(3, (int) 30f));
        barEntries.add(new BarEntry(4, (int) 36f));

        ArrayList<BarEntry> barEntries1 = new ArrayList<>();

        barEntries1.add(new BarEntry(1, (int) 44f));
        barEntries1.add(new BarEntry(2, (int) 54f));
        barEntries1.add(new BarEntry(3, (int) 60f));
        barEntries1.add(new BarEntry(4, (int) 31f));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Date Set1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet1 = new BarDataSet(barEntries1, "Date Set1");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet, barDataSet1);

        float groupSpace = 0.1f;
        float barSpace = 0.02f;
        float barWidth = 0.43f;

        barChart.setData(data);

        data.setBarWidth(barWidth);
        barChart.groupBars(1, groupSpace, barSpace);


        String[] months = new String[] {"Jan","Feb","Mar","April","May","Jun"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(1);

    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter{
        private String[] mValues;
        public MyXAxisValueFormatter(String[] values){
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }
}
