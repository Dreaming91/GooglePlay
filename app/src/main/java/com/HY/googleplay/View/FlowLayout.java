package com.HY.googleplay.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 杂兵 on 2017/7/25.
 */

public class FlowLayout extends ViewGroup {
    private int horizontalsize = 15;
    private int verticalsize = 15;
    private int realwidth;

    public void setHorizontalsize(int horizontalsize) {
        this.horizontalsize = horizontalsize;
    }

    public void setVerticalsize(int verticalsize) {
        this.verticalsize = verticalsize;
    }

    private ArrayList<Row> rowList = new ArrayList<>();

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        rowList.clear();

        int width = MeasureSpec.getSize(widthMeasureSpec);
        realwidth = width - getPaddingLeft() - getPaddingRight();

        Row row = new Row();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.measure(0, 0);
            if (row.viewList.size() == 0) {
                row.addRowView(view);
            } else if (view.getMeasuredWidth() + row.rowWidth + horizontalsize > realwidth) {
                rowList.add(row);
                row = new Row();
                row.addRowView(view);
            } else {
                row.addRowView(view);
            }
            if (i == (getChildCount() - 1)) {
                rowList.add(row);
            }
        }


        int height = getPaddingBottom() + getPaddingTop();
        for (Row r : rowList) {
            height += r.rowHeight;
        }
        height += verticalsize * (rowList.size() - 1);
        setMeasuredDimension(width, height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        int top = getPaddingTop();
        for (int i = 0; i < rowList.size(); i++) {
            Row row = rowList.get(i);


            ArrayList<View> list = row.viewList;

            int avaliableSpace = realwidth - row.rowWidth;
            float perspace = avaliableSpace / row.viewList.size();


            for (int j = 0; j < list.size(); j++) {
                View child = list.get(j);

                if (isfullscreen) {
                    int half = (int) (perspace / 2);
                    child.setPadding(child.getPaddingLeft() + half, child.getPaddingTop()
                            , child.getPaddingRight() + half, child.getPaddingBottom());
                    child.measure(0, 0);
                }


                if (j == 0) {
                                                                                  //这里去掉child
                    child.layout(left, top, left + child.getMeasuredWidth(), top + child.getMeasuredHeight());
                } else {
                    View prechild = list.get(j - 1);

                    //是外边距产生的问题，如果提供的内边距就会缩进
                    int nextleft = prechild.getRight() + horizontalsize;                        //去掉prechild
                    child.layout(nextleft, prechild.getTop(), nextleft + child.getMeasuredWidth(),prechild.getBottom());
                }
            }

            top += row.rowHeight + verticalsize;

        }


    }

    private boolean isfullscreen = true;

    public void setfullscreen(boolean isfullscreen) {
        this.isfullscreen = isfullscreen;
    }

    class Row {
        public ArrayList<View> viewList = new ArrayList<>();
        public int rowHeight;
        public int rowWidth;

        public void addRowView(View view) {
            viewList.add(view);
            if (viewList.size() == 1) {
                rowWidth = view.getMeasuredWidth();
            } else {
                rowWidth += view.getMeasuredWidth() + horizontalsize;
            }
            rowHeight = view.getMeasuredHeight();
        }
    }
}
