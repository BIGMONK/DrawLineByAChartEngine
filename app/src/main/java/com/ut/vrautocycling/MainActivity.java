package com.ut.vrautocycling;

import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

import com.ut.vrautocycling.utils.drawchart.DrawChartUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            int[] point = new int[colorsList.size()];
            for (int i = 0; i < point.length; i++) {
                point[i] = mRandom.nextInt(10) + 20 * i;
            }
            drawChartUtil.updateChart(point);
            handler.sendEmptyMessageDelayed(0, 100);
        }
    };
    private DrawChartUtil drawChartUtil;
    private Random mRandom;
    private int lineCount;
    private ArrayList<int[]> colorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        mRandom = new Random();
        WeakReference<ContextWrapper> mContext = new WeakReference<ContextWrapper>(this);
        ContextWrapper context = mContext.get();

        drawChartUtil = new DrawChartUtil(context);

        drawChartUtil.SetContentView(frameLayout);

        drawChartUtil.setXAxisMaxAndYAxisMax(1700, 60);
        drawChartUtil.setXLableCountAndYLableCount(20,6);
        drawChartUtil.SetXLbleMaxValueAndXLableMaxValue(100,0);

//        drawChartUtil.setXAxisMaxAndYAxisMax(4500, 60);
//        drawChartUtil.setXLableCountAndYLableCount(9,3);
//        drawChartUtil.SetXLbleMaxValueAndXLableMaxValue(45f,0);
//
//        drawChartUtil.setXAxisMaxAndYAxisMax(400, 60);
//        drawChartUtil.setXLableCountAndYLableCount(20, 3);
//        drawChartUtil.SetXLbleMaxValueAndXLableMaxValue(100, 0);

//        drawChartUtil.setXAxisMaxAndYAxisMax(1800, 60);
//        drawChartUtil.setXLableCountAndYLableCount(9,3);
//        drawChartUtil.SetXLbleMaxValueAndXLableMaxValue(3f,0);

        int[] line1Colors1 = new int[]{0xFFE9B228, 0x44E9B228};
        int[] line1Colors2 = new int[]{0xFF18F608, 0x4418F608};
        int[] line1Colors3 = new int[]{0xFF2DBCF1, 0x442DBCF1};
        colorsList = new ArrayList<>();
        colorsList.add(line1Colors1);
        colorsList.add(line1Colors2);
        colorsList.add(line1Colors3);
        drawChartUtil.setColors(colorsList);
        drawChartUtil.InitView(drawChartUtil.buildRenderer(colorsList));

//        handler.sendEmptyMessageDelayed(0, 1000);


        ArrayList<int[]> list=new ArrayList<>();

        for (int i = 0; i <1700 ; i++) {
            int[] arr=new int[3];
            for (int j = 0; j <3 ; j++) {
                arr[j]=mRandom.nextInt(10) + 20 * j;
            }
            list.add(arr);
        }
        drawChartUtil.updateChart(list);



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float width = dm.widthPixels;
        float height = dm.heightPixels;
        System.out.println("屏幕宽高" + width + "*" + height + " \tDisplayMetrics" + dm.toString());





    }
}
