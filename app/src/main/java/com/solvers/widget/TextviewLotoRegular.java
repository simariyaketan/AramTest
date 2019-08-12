package com.solvers.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TextviewLotoRegular  extends AppCompatTextView {
    public TextviewLotoRegular(Context context) {
        super(context);
        init();
    }

    public TextviewLotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextviewLotoRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Regular.ttf");
            setTypeface(tf);
        }
    }
}