package com.move.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;

/**
 * Created by cxj on 2017/4/20.
 * 这个控件用于显示文本前面或者后面有大小不一标志
 * 比如显示一个价钱,设计上通常都把钱的符号弄的比较小
 * 这个控件就致力于解决的这类的显示
 */
public class RelativeSizeTextView extends AppCompatTextView {

    public RelativeSizeTextView(Context context) {
        this(context, null);
    }

    public RelativeSizeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RelativeSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        // 开始读取自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RelativeSizeTextView);

        startText = a.getString(R.styleable.RelativeSizeTextView_startText);
        endText = a.getString(R.styleable.RelativeSizeTextView_endText);

        startProportion = a.getFloat(R.styleable.RelativeSizeTextView_proportion, 1f);
        endProportion = a.getFloat(R.styleable.RelativeSizeTextView_proportion, 1f);

        startProportion = a.getFloat(R.styleable.RelativeSizeTextView_start_proportion, startProportion);
        endProportion = a.getFloat(R.styleable.RelativeSizeTextView_end_proportion, endProportion);

        startTextColor = a.getColor(R.styleable.RelativeSizeTextView_start_end_text_color, startTextColor);
        endTextColor = a.getColor(R.styleable.RelativeSizeTextView_start_end_text_color, endTextColor);

        startTextColor = a.getColor(R.styleable.RelativeSizeTextView_start_text_color, startTextColor);
        endTextColor = a.getColor(R.styleable.RelativeSizeTextView_end_text_color, endTextColor);

        a.recycle();

        setTagText(getText());

    }

    /**
     * 开头的文本
     */
    private String startText;

    /**
     * 开始文本的颜色
     */
    private int startTextColor = 0;

    /**
     * 结束的文本
     */
    private String endText;

    /**
     * 结束文本的颜色
     */
    private int endTextColor = 0;

    /**
     * 开始的比例
     */
    private float startProportion;

    /**
     * 结束文本的比例
     */
    private float endProportion;

    public void setTagText(CharSequence text) {

        if (!TextUtils.isEmpty(startText)) {
            text = endText + text;
        }

        if (!TextUtils.isEmpty(endText)) {
            text = text + endText;
        }

        SpannableString ss = new SpannableString(text);
        RelativeSizeSpan startSpan = new RelativeSizeSpan(startProportion);
        RelativeSizeSpan endSpan = new RelativeSizeSpan(endProportion);

        if (!TextUtils.isEmpty(startText)) {
            ss.setSpan(startSpan, 0, startText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        if (startTextColor != 0) {
            ForegroundColorSpan fcs = new ForegroundColorSpan(startTextColor);
            ss.setSpan(fcs, 0, startText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        if (!TextUtils.isEmpty(endText)) {
            ss.setSpan(endSpan, text.length() - endText.length(), text.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        if (endTextColor != 0) {
            ForegroundColorSpan fcs = new ForegroundColorSpan(endTextColor);
            ss.setSpan(fcs, text.length() - endText.length(), text.length(),
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        super.setText(ss);
    }

}