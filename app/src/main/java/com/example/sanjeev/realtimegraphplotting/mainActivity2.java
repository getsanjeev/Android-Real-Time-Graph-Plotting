package com.example.sanjeev.realtimegraphplotting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class mainActivity2 extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_2);
        GraphView graph = (GraphView) findViewById(R.id.graph_2);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]
                {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(4.5, 15),
                new DataPoint(5, 6),
                new DataPoint(6, 10),
                        new DataPoint(8, 18)
                //new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }
}
