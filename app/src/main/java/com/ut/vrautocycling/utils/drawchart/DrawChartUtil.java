package com.ut.vrautocycling.utils.drawchart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.ViewGroup;

import com.ut.vrautocycling.utils.drawchart.baseutils.ChartFactory;
import com.ut.vrautocycling.utils.drawchart.baseutils.GraphicalView;
import com.ut.vrautocycling.utils.drawchart.baseutils.chart.PointStyle;
import com.ut.vrautocycling.utils.drawchart.baseutils.model.XYMultipleSeriesDataset;
import com.ut.vrautocycling.utils.drawchart.baseutils.model.XYSeries;
import com.ut.vrautocycling.utils.drawchart.baseutils.renderer.XYMultipleSeriesRenderer;
import com.ut.vrautocycling.utils.drawchart.baseutils.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by black on 2016/6/21 0021.
 */
public class DrawChartUtil {
    private final static String TAG = DrawChartUtil.class.getSimpleName();
    private ViewGroup mContentView;
    private Context mContext;
    private List<XYSeries> mXYseriesList;
    private XYMultipleSeriesDataset mDataset;
    private GraphicalView chart;
    private XYMultipleSeriesRenderer renderer;
    private String mTitle = "";
    private int[] mColor = new int[]{Color.GREEN, Color.WHITE, Color.BLUE, Color.RED, Color.YELLOW};
    private int mColorIndex;
    private int XAxisMax;
    private int YAxisMax;
    private int mXLableCount;
    private int mYLableCount;
    private float mXLbleMaxValue;
    private float mYLbleMaxValue;
    private ArrayList<int[]> mColorsList;


    public DrawChartUtil(Context context) {
        mContext = context;
    }

    public void SetContentView(ViewGroup viewGroup) {
        mContentView = viewGroup;
        mContentView.setBackgroundColor(Color.TRANSPARENT);
    }


    public void setColors(ArrayList<int[]> colors) {
        mColorsList = colors;
    }

    /**
     * 设置x轴和y轴的点数
     *
     * @param xMax
     * @param yMax
     */
    public void setXAxisMaxAndYAxisMax(int xMax, int yMax) {
        XAxisMax = xMax;
        YAxisMax = yMax;
    }

    /**
     * 设置 x轴和y轴的标签数
     *
     * @param xLableCount
     * @param yLableCount
     */
    public void setXLableCountAndYLableCount(int xLableCount, int yLableCount) {
        mXLableCount = xLableCount;
        mYLableCount = yLableCount;
    }

    /**
     * 设置x轴和y轴的标签最大值
     *
     * @param xLableMaxValue
     * @param yLableMaxValue
     */
    public void SetXLbleMaxValueAndXLableMaxValue(float xLableMaxValue, float yLableMaxValue) {
        mXLbleMaxValue = xLableMaxValue;
        mYLbleMaxValue = yLableMaxValue;
    }


    public void SetTitle(String title) {
        mTitle = title;
    }


    public XYMultipleSeriesRenderer buildRenderer(ArrayList<int[]> mColorsList) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setChartSettings(renderer, "X", "Y", 0, XAxisMax, 0, YAxisMax, Color.WHITE, Color.WHITE);
        for (int i = 0; i < mColorsList.size(); i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(mColorsList.get(i)[0]);
            r.setPointStyle(PointStyle.POINT);
//            r.setPointStrokeWidth();
            r.setLineWidth(2f);

            XYSeriesRenderer.FillOutsideLine fi1 = new XYSeriesRenderer.FillOutsideLine(XYSeriesRenderer.FillOutsideLine.Type.BOUNDS_ALL);
            fi1.setColor(mColorsList.get(i)[1]);
            r.addFillOutsideLine(fi1);

            renderer.addSeriesRenderer(r);
        }
        for (int i = 0; i <= mXLableCount; i++) {
//            renderer.removeXTextLabel(xMax/10*i);
            if (mXLableCount==0)
                continue;
            renderer.addXTextLabel(XAxisMax / mXLableCount * i, "" + (int) mXLbleMaxValue / mXLableCount * i);
        }
        for (int i = 0; i <= mYLableCount; i++) {
//            renderer.removeXTextLabel(xMax/10*i);
            if (mYLableCount==0)
                continue;
            renderer.addYTextLabel(YAxisMax / mYLableCount * i, "" + (int) mYLbleMaxValue / mYLableCount * i);
        }

        return renderer;
    }

    //设置表格的格式
    private void setChartSettings(XYMultipleSeriesRenderer renderer, String xTitle, String yTitle,
                                  double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor) {
        //有关对图表的渲染可参看api文档
        renderer.setChartTitle(mTitle);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);//坐标轴颜色
        renderer.setLabelsColor(labelsColor);//标题颜色
        renderer.setAxisTitleTextSize(0);//坐标轴标题字体大小
        renderer.setChartTitleTextSize(0);//图表标题字体大小
        renderer.setLabelsTextSize(10);//
        renderer.setXLabels(mXLableCount);
        renderer.setYLabels(mYLableCount);
        renderer.setShowLabels(true, false);//是否显示标签
        renderer.setShowCustomTextGridX(true);//纵向自定义格栅
//        renderer.setShowCustomTextGridY(true);//横向自定义格栅
        renderer.setShowGridY(false);//纵向默认格栅
        renderer.setShowGridX(true);//横向默认格栅
        renderer.setShowTickMarks(true);
        renderer.setGridLineWidth(1f);//设置网格线的宽度
        renderer.setGridColor(0xCC505956);//设置网格线颜色
        renderer.setXTitle("");
        renderer.setYTitle("");
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        renderer.setPointSize(0.1f);//设置数据点的大小
        renderer.setShowLegend(false);//是否设置图例
        renderer.setPanEnabled(false);
        renderer.setBarSpacing(0.01d);
        renderer.setBarWidth(0.01f);
        // ** 左 ** 右
        renderer.setMargins(new int[]{0, 10, 10, 10});
        renderer.setClickEnabled(false);//是否允许点击，设置为true时，缩放失效
        renderer.setDisplayValues(false);
        renderer.setMarginsColor(Color.WHITE);

    }


    public void InitView(XYMultipleSeriesRenderer renderer) {

        mXYseriesList = new ArrayList<>();
        mDataset = new XYMultipleSeriesDataset();
        for (int i = 0; i < renderer.getSeriesRenderers().length; i++) {
            XYSeries series = new XYSeries(mTitle);
            mXYseriesList.add(series);
            mDataset.addSeries(series);
        }
        chart = ChartFactory.getLineChartView(mContext, mDataset, renderer);
        chart.setBackgroundColor(Color.TRANSPARENT);//设置图表背景透明
        mContentView.addView(chart, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));
    }

    /**
     * UI更新多条线的同一X轴位置的Y值
     *
     * @param addPoiont
     */
    public void updateChart(int... addPoiont) {
        if (addPoiont == null || mXYseriesList.get(0).getItemCount() > XAxisMax) {
            Log.e(TAG, "传入的点的个数不对");
            return;
        }

        //更新数据集中旧的点集
        for (int i = 0; i < mXYseriesList.size(); i++) {
            if (i < addPoiont.length) {
                mDataset.removeSeries(mXYseriesList.get(i));
                mXYseriesList.get(i).add(mXYseriesList.get(i).getItemCount(), addPoiont[i]);
                mDataset.addSeries(mXYseriesList.get(i));
            } else {
                mDataset.removeSeries(mXYseriesList.get(i));
                mXYseriesList.get(i).add(mXYseriesList.get(i).getItemCount(), 0);
                mDataset.addSeries(mXYseriesList.get(i));
            }
        }


        //视图更新，没有这一步，曲线不会呈现动态
        //如果在非UI主线程中，需要调用postInvalidate()，具体参考api
        chart.invalidate();
    }

    /**
     * 非UI更新多条线的同一X轴位置的Y值
     *
     * @param addPoiont
     */
    public void updateChartOnThread(int... addPoiont) {
        if (addPoiont == null || addPoiont.length < mXYseriesList.size() || mXYseriesList.get(0).getItemCount() > XAxisMax) {
            Log.e(TAG, "传入的点的个数不对");
            return;
        }

        //更新数据集中旧的点集
        for (int i = 0; i < mXYseriesList.size(); i++) {
            if (i < addPoiont.length) {
                mDataset.removeSeries(mXYseriesList.get(i));
                mXYseriesList.get(i).add(mXYseriesList.get(i).getItemCount(), addPoiont[i]);
                mDataset.addSeries(mXYseriesList.get(i));
            } else {
                mDataset.removeSeries(mXYseriesList.get(i));
                mXYseriesList.get(i).add(mXYseriesList.get(i).getItemCount(), 0);
                mDataset.addSeries(mXYseriesList.get(i));
            }
        }

        //视图更新，没有这一步，曲线不会呈现动态
        //如果在非UI主线程中，需要调用postInvalidate()，具体参考api
        chart.postInvalidate();
    }


    //更新新点
    public void updateChartLast(int... addPoiont) {
        if (addPoiont == null || addPoiont.length < mXYseriesList.size() || mXYseriesList.get(0).getItemCount() > XAxisMax) {
            Log.e(TAG, "传入的点的个数不对");
            return;
        }
        //更新数据集中旧的点集
        for (int i = 0; i < mXYseriesList.size(); i++) {
            if (i < addPoiont.length) {
                mDataset.removeSeries(mXYseriesList.get(i));
                mXYseriesList.get(i).add(mXYseriesList.get(i).getItemCount(), addPoiont[i]);
                mDataset.addSeries(mXYseriesList.get(i));
            } else {
                mDataset.removeSeries(mXYseriesList.get(i));
                mXYseriesList.get(i).add(mXYseriesList.get(i).getItemCount(), 0);
                mDataset.addSeries(mXYseriesList.get(i));
            }
        }
        //视图更新，没有这一步，曲线不会呈现动态
        //如果在非UI主线程中，需要调用postInvalidate()，具体参考api
        chart.invalidate();
    }

    //更新新点
    public void updateChartLastOnThread(int... addPoiont) {
        if (addPoiont == null || addPoiont.length < mXYseriesList.size() || mXYseriesList.get(0).getItemCount() > XAxisMax) {
            Log.e(TAG, "传入的点的个数不对");
            return;
        }
        //更新数据集中旧的点集
        for (int i = 0; i < mXYseriesList.size(); i++) {
            if (i < addPoiont.length) {
                mDataset.removeSeries(mXYseriesList.get(i));
                mXYseriesList.get(i).add(mXYseriesList.get(i).getItemCount(), addPoiont[i]);
                mDataset.addSeries(mXYseriesList.get(i));
            } else {
                mDataset.removeSeries(mXYseriesList.get(i));
                mXYseriesList.get(i).add(mXYseriesList.get(i).getItemCount(), 0);
                mDataset.addSeries(mXYseriesList.get(i));
            }
        }
        //视图更新，没有这一步，曲线不会呈现动态
        //如果在非UI主线程中，需要调用postInvalidate()，具体参考api
        chart.postInvalidate();
    }

    /**
     * 根据Y值集合画出多条线
     *
     * @param list
     */
    public void updateChart(ArrayList<int[]> list) {

        //移除数据集中旧的点集
        mDataset.clear();
        //判断当前点集中到底有多少点，因为屏幕总共只能容纳count个，所以当点数超过count时，长度永远是count
        int length = list.size();

        if (length >= XAxisMax) {
            length = XAxisMax;
        }
        //点集先清空，为了做成新的点集而准备
        for (int i = 0; i < mXYseriesList.size(); i++) {
            mXYseriesList.get(i).clear();
            for (int k = 0; k < length; k++) {
                if (i < list.get(k).length) {
                    mXYseriesList.get(i).add(k, list.get(k)[i]);
                } else {
                    mXYseriesList.get(i).add(k, 0);
                }
            }
            mDataset.addSeries(mXYseriesList.get(i));
        }

        //视图更新，没有这一步，曲线不会呈现动态
        //如果在非UI主线程中，需要调用postInvalidate()，具体参考api
        chart.invalidate();
    }

    public void updateChartOnThread(ArrayList<int[]> list) {

        //移除数据集中旧的点集
        mDataset.clear();
        //判断当前点集中到底有多少点，因为屏幕总共只能容纳count个，所以当点数超过count时，长度永远是count
        int length = list.size();

        if (length >= XAxisMax) {
            length = XAxisMax;
        }
        //点集先清空，为了做成新的点集而准备
        for (int i = 0; i < mXYseriesList.size(); i++) {
            mXYseriesList.get(i).clear();
            for (int k = 0; k < length; k++) {
                if (i < list.get(k).length) {
                    mXYseriesList.get(i).add(k, list.get(k)[i]);
                } else {
                    mXYseriesList.get(i).add(k, 0);
                }
            }
            mDataset.addSeries(mXYseriesList.get(i));
        }

        //视图更新，没有这一步，曲线不会呈现动态
        //如果在非UI主线程中，需要调用postInvalidate()，具体参考api
        chart.postInvalidate();
    }

}

