package com.molarity.molarity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a1 on 10/8/2015.
 */
public class CustomListAdapter extends ArrayAdapter<String> implements View.OnClickListener {
    private final Activity context;
    private ArrayList<String> codes;
    private ArrayList<String> descriptions;

    private boolean hideDeleteButton;

    public CustomListAdapter(Activity context, ArrayList<String> codes, ArrayList<String> descriptions, boolean hideDeleteButton) {
        super(context, R.layout.formula_list, codes);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.codes = codes;
        this.descriptions = descriptions;

        this.hideDeleteButton = hideDeleteButton;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.formula_list, null, true);

        TextView codeText = (TextView) rowView.findViewById(R.id.codeItem);
        TextView descriptionText = (TextView) rowView.findViewById(R.id.descriptionItem);
        ImageButton deleteButton = (ImageButton) rowView.findViewById(R.id.deleteButton);
        deleteButton.setId(position);

        deleteButton.setOnClickListener(this);

        codeText.setText(codes.get(position));
        descriptionText.setText(descriptions.get(position));
        if (hideDeleteButton) {
            deleteButton.setVisibility(View.INVISIBLE);
        } else {
            deleteButton.setVisibility(View.VISIBLE);
        }

        if (position%2 == 0)
            rowView.setBackgroundResource( R.color.white_gray );

        return rowView;
    };

    public void setCodes( ArrayList<String> codes ) {
        this.codes = codes;
    }

    public void setDescriptions( ArrayList<String> descriptions ) {
        this.descriptions = descriptions;
    }

    public void setDeleteButtonVisible(boolean hiden) {
        this.hideDeleteButton = !hiden;
    }

    public boolean getDeleteButtonVisibleStatus() {
        return this.hideDeleteButton;
    }

    @Override
    public void onClick(View v) {
        CalculateActivity calcActivity = (CalculateActivity)this.context;
        calcActivity.deleteFormulaWeight(v.getId());
    }
}
