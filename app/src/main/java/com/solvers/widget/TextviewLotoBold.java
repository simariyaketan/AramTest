package com.solvers.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Create Textview class with set Typeface
 */

public class TextviewLotoBold  extends AppCompatTextView {
    public TextviewLotoBold(Context context) {
        super(context);
        init();
    }

    public TextviewLotoBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextviewLotoBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lato-Bold.ttf");
            setTypeface(tf);
        }
    }
}
