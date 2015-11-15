package com.molarity.molarity.mass;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.molarity.molarity.CalculateActivity;
import com.molarity.molarity.Config;
import com.molarity.molarity.CustomListAdapter;
import com.molarity.molarity.DB.Data;
import com.molarity.molarity.R;
import com.molarity.molarity.weight.GeneseeWeight;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MassActivity extends CalculateActivity {

    private TextView concentrationLabel;
    private EditText concentrationValue;
    private Spinner concentrationUnit;

    private TextView formulaWeightLabel;
    private EditText formulaWeightValue;
    private Spinner formulaWeightUnit;

    private TextView volumeLabel;
    private EditText volumeValue;
    private Spinner volumeUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void buildCosmeticView() {
        super.buildCosmeticView();

        backButton.setBackgroundResource(R.drawable.mass_back_button);
        topbar.setBackgroundResource(R.drawable.mass_top_bar);

        concentrationLabel.setText(R.string.concentration_label);
        concentrationUnit.setBackgroundResource(R.drawable.mass_dropdown);

        formulaWeightLabel.setText(R.string.formula_weight_label);
        formulaWeightUnit.setVisibility(View.INVISIBLE);

        volumeLabel.setText(R.string.volume_label);
        volumeUnit.setBackgroundResource(R.drawable.mass_dropdown);

        calculateButton.setBackgroundResource(R.drawable.mass_calculate_button);

        geneseeFormulaWeightButton.setBackgroundResource(R.drawable.mass_popup_button);

        saveFormulaButton.setBackgroundResource(R.drawable.mass_save_button);
        savedFormulaButton.setBackgroundResource(R.drawable.mass_saved_button);

        clearFieldButton.setBackgroundResource(R.drawable.mass_clear_button );

        //list view
        listTopBorder.setBackgroundResource(R.drawable.mass_navbar);
        listHeaderBottomBar.setBackgroundResource(R.drawable.mass_navbar);
        closeButton.setBackgroundResource(R.drawable.mass_popup_close_button);

        //keyboard
        keyboardTopLayout.setBackgroundResource(R.drawable.mass_navbar);
    }

    @Override
    public void setWeightValue(String value) {
        secondFieldValue.setText(value);
        super.setWeightValue(null);
    }

    @Override
    public void initData() {
        super.initData();

        firstFieldAdapter = ArrayAdapter.createFromResource(this,
                R.array.concentration_unit_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        firstFieldAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        thirdFieldAdapter = ArrayAdapter.createFromResource(this,
                R.array.volume_unit_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        thirdFieldAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        GeneseeWeight geneseeWeight = new GeneseeWeight();
        weightData.codes.add(geneseeWeight.codes.get(0));
        weightData.descriptions.add(geneseeWeight.descriptions.get(0));
        weightData.weights.add(geneseeWeight.weights.get(0));
    }

    @Override
    public void initView() {
        super.initView();

        concentrationLabel = firstFieldLabel;
        concentrationValue = firstFieldValue;
        concentrationUnit = firstFieldUnit;

        formulaWeightLabel = secondFieldLabel;
        formulaWeightValue = secondFieldValue;
        formulaWeightUnit = secondFieldUnit;

        volumeLabel = thirdFieldLabel;
        volumeValue = thirdFieldValue;
        volumeUnit = thirdFieldUnit;
    }

    @Override
    public void saveFormulaWeight(String description) {
        Data massTable = new Data(Config.table_name, Config.Mass, formulaWeightValue.getText().toString(), description, formulaWeightValue.getText().toString());

        if (Data.hasValue(massTable)) {
            Toast.makeText(this.getApplicationContext(), getResources().getText(R.string.value_dupulicated), Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<Data> arrayData = new ArrayList<Data>();
        arrayData.add(massTable);

        Data.addDatas(arrayData);

        Toast.makeText(this.getApplicationContext(), getResources().getText(R.string.save_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDefaultFormulaWeight() {
        weightData.clear();

        GeneseeWeight geneseeWeight = new GeneseeWeight();
        for (String str: geneseeWeight.codes) {
            weightData.codes.add(str);
        }

        for (String str: geneseeWeight.descriptions) {
            weightData.descriptions.add(str);
        }

        for (String str: geneseeWeight.weights) {
            weightData.weights.add(str);
        }

        customListAdapteradapter.setDeleteButtonVisible(false);

        customListAdapteradapter.notifyDataSetChanged();

        super.showDefaultFormulaWeight();
    }

    @Override
    public void showSavedFormulaWeight() {
        ArrayList<Data> formulaWeightData = Data.getAllRecord(Config.Mass);

        weightData.clear();
        for (Data data: formulaWeightData) {
            weightData.categories.add(Config.Mass);
            weightData.codes.add( data.code );
            weightData.descriptions.add( data.description );
            weightData.weights.add(data.weight);
        }

        customListAdapteradapter.setDeleteButtonVisible(true);

        customListAdapteradapter.notifyDataSetChanged();

        super.showSavedFormulaWeight();
    }

    @Override
    public void secondFieldValueIsEmpty() {
        saveFormulaButton.setEnabled(false);
    }

    @Override
    public void secondFieldValueIsNotEmpty() {
        saveFormulaButton.setEnabled(true);
    }


    public void calculateValue() {
        super.calculateValue();

        if (!canCalculate()) {
            return;
        }

        double conc = Double.parseDouble(concentrationValue.getText().toString());
        double unit = firstUnit-3;
        double molar = conc * Math.pow(10, unit);

        double fw = Double.parseDouble(formulaWeightValue.getText().toString());

        double volume =  Double.parseDouble(volumeValue.getText().toString());
        double liters = volume * Math.pow(10, thirdUnit);

        double moles = liters*molar;
        double value = moles*fw;

        float factor = (float) Math.pow(10, 4);

        String unitString = "";
        double finalResult = 0;

        if (value < 1e-9) {
            finalResult = Math.round((value/1e-12)*factor)/factor;
            unitString = getResources().getString(R.string.nanogram_title);
        } else  if (value < 1e-6) {
            finalResult = Math.round((value/1e-9) * factor)/factor;
            unitString = getResources().getString(R.string.microgram_title);
        } else if (value < 1e-3) {
            finalResult = Math.round((value / (1e-6)) * factor)/factor;
            unitString = getResources().getString(R.string.milligram_title);
        } else if (value < 1) {
            finalResult = Math.round((value / (1e-3)) * factor)/factor;
            unitString = getResources().getString(R.string.gram_title);
        } else {
            finalResult = Math.round(value * factor)/factor;
            unitString = getResources().getString(R.string.kilogram_title);
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.0000");
        resultNumberLabel.setText(decimalFormat.format(finalResult));
        resultUnitLabel.setText(unitString);

    }

    @Override
    public void selectedFirstUnit(int index) {
        switch (index) {
            case 0:
                firstUnit = Config.Molar;
                break;
            case 1:
                firstUnit = Config.MilliMolar;
                break;
            case 2:
                firstUnit = Config.MicroMolar;
                break;
            case 3:
                firstUnit = Config.NanoMolar;
                break;
            case 4:
                firstUnit = Config.PicoMolar;
                break;
            case 5:
                firstUnit = Config.FentoMolar;
                break;
        }
        Log.v("Molarity", "firstUnit=" + Integer.toString(firstUnit));
    }

    @Override
    public void selectedThirdUnit(int index) {
        switch (index) {
            case 0:
                thirdUnit = Config.Liter;
                break;
            case 1:
                thirdUnit = Config.MilliLiter;
                break;
            case 2:
                thirdUnit = Config.MicroLiter;
                break;
        }
        Log.v("Molarity", "firstUnit=" + Integer.toString(thirdUnit));
    }
}
