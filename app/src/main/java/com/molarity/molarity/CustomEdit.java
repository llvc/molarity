package com.molarity.molarity;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by a1 on 10/16/2015.
 */
public class CustomEdit extends EditText {
    public CustomEdit(Context context) {
        super(context);
    }

    @Override
    public void onSelectionChanged(int start, int end) {

        CharSequence text = getText();
        if (text != null) {
            if (start != text.length() || end != text.length()) {
                setSelection(text.length(), text.length());
                return;
            }
        }

        super.onSelectionChanged(start, end);
    }
}
