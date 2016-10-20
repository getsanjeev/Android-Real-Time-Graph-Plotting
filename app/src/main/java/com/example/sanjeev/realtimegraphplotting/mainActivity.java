package com.example.sanjeev.realtimegraphplotting;
import com.example.sanjeev.realtimegraphplotting.R;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class mainActivity extends AppCompatActivity implements OnChartGestureListener,
        OnChartValueSelectedListener {

    private LineChart mChart_1;
    private LineChart mChart_2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // To make full screen layout
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.graph);
        mChart_1 = (LineChart) findViewById(R.id.linechart1);
        mChart_1.setOnChartGestureListener(this);
        mChart_1.setOnChartValueSelectedListener(this);
        mChart_1.setDrawGridBackground(false);
        // add data
        setData();
        // get the legend (only possible after setting data)
        Legend l = mChart_1.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        // no description text
        // enable touch gestures
        mChart_1.setTouchEnabled(true);
        // enable scaling and dragging
        mChart_1.setDragEnabled(true);
        mChart_1.setScaleEnabled(true);
    }

    private ArrayList<String> setXAxisValues()
    {
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add(0,"A");
        xVals.add(1,"B");
        xVals.add(2,"C");
        xVals.add(3,"D");
        xVals.add(4,"E");
        xVals.add(5,"F");
        xVals.add(6,"G");
        xVals.add(7,"H");
        xVals.add(8,"I");
        xVals.add(9,"J");
        xVals.add(10,"K");

        return xVals;
    }

    private ArrayList<Entry> setYAxisValues(int value, int x){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        int j;
        for(int i = 0;i<11;i++)
        {
            j = i+5;
            yVals.add(new Entry(value,j ));
            if (i%2 ==0)
            {
                value = value+10;
                //x = x+10;
            }
            value = value + 10;
        }
        return yVals;
    }

    private void setData()
    {
        LineDataSet set1;
        LineDataSet set2;
        ArrayList<String> xVals = setXAxisValues();
        ArrayList<Entry> yVals = setYAxisValues(0,0);
        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "DataSet 1");
        set1.setFillAlpha(110);

        xVals = setXAxisValues();
        yVals = setYAxisValues(10,0);
        set2 = new LineDataSet(yVals, "DataSet 2");

        set2.setFillAlpha(90);

        // set the line to be drawn like this "- - - - - -"
        set1.setColor(Color.BLUE);
        set2.setColor(Color.GREEN);
        set1.setCircleColor(Color.BLUE);
        set2.setCircleColor(Color.GREEN);
        set1.setLineWidth(1f);
        set2.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set2.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set2.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set2.setValueTextSize(9f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);
        // set data
        mChart_1.setData(data);
    }


    @Override
    public void onChartGestureStart(MotionEvent me,
                                    ChartTouchListener.ChartGesture
                                            lastPerformedGesture)
    {

        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me,
                                  ChartTouchListener.ChartGesture
                                          lastPerformedGesture)
    {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);
        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            mChart_1.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me)
    {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me)
    {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me)
    {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2,
                             float velocityX, float velocityY)
    {
        Log.i("Fling", "Chart flinged. VeloX: "
                + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY)
    {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY)
    {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + mChart_1.getLowestVisibleXIndex()
                + ", high: " + mChart_1.getHighestVisibleXIndex());

        Log.i("MIN MAX", "xmin: " + mChart_1.getXChartMin()
                + ", xmax: " + mChart_1.getXChartMax()
                + ", ymin: " + mChart_1.getYChartMin()
                + ", ymax: " + mChart_1.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}