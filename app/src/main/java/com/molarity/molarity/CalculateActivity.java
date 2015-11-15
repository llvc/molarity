package com.molarity.molarity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.molarity.molarity.DB.Data;
import com.molarity.molarity.weight.FormulaWeight;

public class CalculateActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener, TextWatcher, View.OnFocusChangeListener {
    public ImageButton backButton;
    public ImageView topbar;

    public TextView firstFieldLabel;
    public EditText firstFieldValue;
    public Spinner firstFieldUnit;
    public int firstUnit;
    public ArrayAdapter<CharSequence> firstFieldAdapter;

    public TextView secondFieldLabel;
    public EditText secondFieldValue;
    public Spinner secondFieldUnit;
    public int secondUnit;
    public ArrayAdapter<CharSequence> secondFieldAdapter;

    public TextView thirdFieldLabel;
    public EditText thirdFieldValue;
    public Spinner thirdFieldUnit;
    public int thirdUnit;
    public ArrayAdapter<CharSequence> thirdFieldAdapter;

    public ImageButton calculateButton;

    public ImageButton geneseeFormulaWeightButton;

    public ImageButton saveFormulaButton;
    public ImageButton savedFormulaButton;

    public TextView resultNumberLabel;
    public TextView resultUnitLabel;

    public ImageButton clearFieldButton;

    //Formula Weight ListView
    public CustomListAdapter customListAdapteradapter;

    public LinearLayout listTopSpaceLayout;
    public LinearLayout weightListLayout;

    public ImageView listTopBorder;
    public ListView formulaWeightListView;
    public TextView codeLabel;
    public TextView descriptionLabel;
    public ImageButton closeButton;
    public ImageView listHeaderBottomBar;
    public LinearLayout listviewLayout;

    public FormulaWeight weightData;

    //keyboard
    public LinearLayout keyboardLayout;

    public LinearLayout keyboardTopLayout;
    public Button prevButton;
    public Button nextButton;

    public LinearLayout keyMainLayout;
    public EditText currentEditText;
    public Button oneButton;
    public Button twoButton;
    public Button threeButton;
    public Button fourButton;
    public Button fiveButton;
    public Button sixButton;
    public Button sevenButton;
    public Button eightButton;
    public Button nineButton;
    public Button zeroButton;
    public Button dotButton;
    public Button deleteButton;

    public int inputType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        initData();

        initView();
        buildCosmeticView();

        setupAction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    *   calculate the value
     */
    public void calculateValue() {
        hideKeyboard();
    }

    public boolean canCalculate() {
        if (firstFieldValue.getText().toString().isEmpty() || secondFieldValue.getText().toString().isEmpty() || thirdFieldValue.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.all_fields_required, Toast.LENGTH_SHORT).show();

            return false;
        }

        return true;
    }

    /*
    *   show genesee list
     */
    public void showGeneseeList() {

    }

    /*
    *   save weight or concentration
     */
    public void saveWeightOrCencentration() {

    }

    public void enableSaveButton() {

    }

    private View.OnTouchListener otl = new View.OnTouchListener() {
        public boolean onTouch (View v, MotionEvent event) {
            EditText myEdit = (EditText) v;
            int inType = myEdit.getInputType(); // backup the input type
            myEdit.setInputType(InputType.TYPE_NULL); // disable soft input
            myEdit.onTouchEvent(event); // call native handler
            myEdit.setInputType(inType); // restore input type

            showKeyboard();

            return true; // the listener has consumed the event
        }
    };

    /*
    * build cosmetic view
     */
    public void buildCosmeticView( ) {
        minimizeListView();

        codeLabel.setText(R.string.genesee_cat);
        descriptionLabel.setText(R.string.description);

        saveFormulaButton.setEnabled(false);

        //keyboard
        keyboardTopLayout.setAlpha(0.9f);
        hideKeyboard();
    }

    public void initData() {
        firstFieldAdapter = null;
        firstUnit = 0;
        secondFieldAdapter = null;
        secondUnit = 0;
        thirdFieldAdapter = null;
        thirdUnit = 0;

        weightData = new FormulaWeight();
        customListAdapteradapter = null;
    }

    public void initView() {
        //main view
        backButton = (ImageButton) findViewById(R.id.backButton);
        topbar = (ImageView) findViewById(R.id.topbar);

        firstFieldLabel = (TextView) findViewById(R.id.firstFieldLabel);
        firstFieldValue = (EditText) findViewById(R.id.firstFieldValue);
        firstFieldUnit = (Spinner) findViewById(R.id.firstFieldUnit);

        secondFieldLabel = (TextView) findViewById(R.id.secondFieldLabel);
        secondFieldValue = (EditText) findViewById(R.id.secondFieldValue);
        secondFieldUnit = (Spinner) findViewById(R.id.secondFieldUnit);

        thirdFieldLabel = (TextView) findViewById(R.id.thirdFieldLabel);
        thirdFieldValue = (EditText) findViewById(R.id.thirdFieldValue);
        thirdFieldUnit = (Spinner) findViewById(R.id.thirdFieldUnit);

        calculateButton = (ImageButton) findViewById(R.id.calculatButton);

        geneseeFormulaWeightButton = (ImageButton) findViewById(R.id.geneseeFormulaWeightButton);

        saveFormulaButton = (ImageButton) findViewById(R.id.saveFormulaButton);
        savedFormulaButton = (ImageButton) findViewById(R.id.savedFormulaButton);

        resultNumberLabel = (TextView) findViewById(R.id.resultNumberLabel);
        resultUnitLabel = (TextView) findViewById(R.id.resultUnitLabel);

        clearFieldButton = (ImageButton) findViewById(R.id.clearFieldButton);

        //list view
        listTopSpaceLayout = (LinearLayout) findViewById(R.id.listTopSpace);

        weightListLayout = (LinearLayout) findViewById(R.id.listLayout);

        formulaWeightListView = (ListView) findViewById(R.id.formulaWeightListView);

        listTopBorder = (ImageView) findViewById(R.id.listTopBorder);
        codeLabel = (TextView) findViewById(R.id.codeLabel);
        descriptionLabel = (TextView) findViewById(R.id.descriptionLabel);
        closeButton = (ImageButton) findViewById(R.id.closeButton);
        listHeaderBottomBar = (ImageView) findViewById(R.id.listHeaderBottomBar);

        listviewLayout = (LinearLayout) findViewById(R.id.listviewLayout);

        //keyboard
        keyboardLayout = (LinearLayout) findViewById(R.id.keyboardLayout);

        keyboardTopLayout = (LinearLayout) findViewById(R.id.keytopLayout);
        prevButton = (Button) findViewById(R.id.prevButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        keyMainLayout = (LinearLayout) findViewById(R.id.keyMainLayout);
        oneButton = (Button) findViewById(R.id.oneButton);
        twoButton = (Button) findViewById(R.id.twoButton);
        threeButton = (Button) findViewById(R.id.threeButton);
        fourButton = (Button) findViewById(R.id.fourButton);
        fiveButton = (Button) findViewById(R.id.fiveButton);
        sixButton = (Button) findViewById(R.id.sixButton);
        sevenButton = (Button) findViewById(R.id.sevenButton);
        eightButton = (Button) findViewById(R.id.eightButton);
        nineButton = (Button) findViewById(R.id.nineButton);
        zeroButton = (Button) findViewById(R.id.zeroButton);
        dotButton = (Button) findViewById(R.id.dotButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        currentEditText = null;

        inputType = firstFieldValue.getInputType();
    }

    public static int getDPI(int size, DisplayMetrics metrics){
        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
    }

    /*
    *   setup action
     */
    public void setupAction() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //main view
        backButton.setOnClickListener(this);

        firstFieldValue.addTextChangedListener(this);
        firstFieldValue.setOnFocusChangeListener(this);
        firstFieldValue.setOnTouchListener(otl);
        firstFieldValue.clearFocus();

        firstFieldUnit.setOnItemSelectedListener(this);
        firstFieldUnit.setAdapter(firstFieldAdapter);

        secondFieldValue.addTextChangedListener(this);
        secondFieldValue.setOnFocusChangeListener(this);
        secondFieldValue.setOnTouchListener(otl);
        secondFieldUnit.setOnItemSelectedListener(this);
        secondFieldUnit.setAdapter(secondFieldAdapter) ;

        thirdFieldValue.addTextChangedListener(this);
        thirdFieldValue.setOnFocusChangeListener(this);
        thirdFieldValue.setOnTouchListener(otl);
        thirdFieldValue.clearFocus();
        thirdFieldUnit.setOnItemSelectedListener(this);
        thirdFieldUnit.setAdapter(thirdFieldAdapter);

        calculateButton.setOnClickListener(this);

        geneseeFormulaWeightButton.setOnClickListener(this);

        closeButton.setOnClickListener(this);

        clearFieldButton.setOnClickListener(this);

        setListAdapter();

        saveFormulaButton.setOnClickListener(this);
        savedFormulaButton.setOnClickListener(this);

        //keyboard
        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        dotButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    public void setListAdapter() {
        customListAdapteradapter = new CustomListAdapter(this, weightData.codes, weightData.descriptions, true);
        formulaWeightListView.setAdapter(customListAdapteradapter);

        formulaWeightListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CustomListAdapter parentAdapter = (CustomListAdapter) parent.getAdapter();
                if (parentAdapter.getDeleteButtonVisibleStatus())
                    Log.v("Molarity", "++++++++" + position + "++++++");
                else
                    showRenameDialog(position);

                return true;
            }
        });

        formulaWeightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setWeightValue(weightData.weights.get(position));
            }
        });
    }

    public void setWeightValue(String value) {
        listviewLayout.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View obj) {
        if (obj == backButton) {
            finish();
        } else if(obj == geneseeFormulaWeightButton) {
            showDefaultFormulaWeight();
        } else if (obj == closeButton) {
            listviewLayout.setVisibility(View.GONE);
        } else if(obj == saveFormulaButton) {
            showSaveDialog();
        } else if(obj == savedFormulaButton) {
            showSavedFormulaWeight();
        } else if (obj == calculateButton) {
            calculateValue();
        } else if (obj == prevButton) {
            if (currentEditText == secondFieldValue) {
                firstFieldValue.requestFocus();
            } else if (currentEditText == thirdFieldValue) {
                secondFieldValue.requestFocus();
            }
        } else if (obj == nextButton) {
            if (currentEditText == firstFieldValue) {
                secondFieldValue.requestFocus();
            } else if (currentEditText == secondFieldValue) {
                thirdFieldValue.requestFocus();
            } else if (currentEditText == thirdFieldValue) {
                hideKeyboard();
            }
        } else if (obj == oneButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_1, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_1, 0));
        } else if (obj == twoButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_2, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_2, 0));;
        } else if (obj == threeButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_3, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_3, 0));
        } else if (obj == fourButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_4, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_4, 0));
        } else if (obj == fiveButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_5, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_5, 0));
        } else if (obj == sixButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_6, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_6, 0));
        } else if (obj == sevenButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_7, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_7, 0));
        } else if (obj == eightButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_8, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_8, 0));
        } else if (obj == nineButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_9, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_9, 0));
        } else if (obj == zeroButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_0, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_0, 0));
        } else if (obj == dotButton) {
            if (currentEditText == null)
                return;

            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_NUMPAD_DOT, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_NUMPAD_DOT, 0));
        } else if (obj == deleteButton) {
            if (currentEditText == null)
                return;
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_DEL, 0));
            currentEditText.dispatchKeyEvent(new KeyEvent(0, 0, KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_DEL, 0));
        } else if (clearFieldButton == obj) {
            firstFieldValue.setText("");
            secondFieldValue.setText("");
            thirdFieldValue.setText("");
        }
    }

    public void showDefaultFormulaWeight() {
        showListView();
        hideKeyboard();
    }

    public void showSavedFormulaWeight() {
        showListView();
        hideKeyboard();
    }

    public void hideKeyboard(){
        keyboardLayout.setVisibility(View.GONE);
    }

    public void showKeyboard() {
        if (keyboardLayout.getVisibility() == View.GONE)
            keyboardLayout.setVisibility(View.VISIBLE);
    }

    public void showSaveDialog() {
        hideKeyboard();

        firstFieldUnit.requestFocus();

        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.input_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                hideSoftKeyboard(currentEditText);
                                hideSoftKeyboard(userInput);

                                saveFormulaWeight(userInput.getText().toString());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                hideSoftKeyboard(currentEditText);
                                hideSoftKeyboard(userInput);

                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void showRenameDialog(final int index) {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.input_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final TextView subtitle = (TextView) promptsView.findViewById(R.id.subtitle);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

        subtitle.setText(this.getResources().getString(R.string.rename_subtitle));

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                hideSoftKeyboard(currentEditText);
                                hideSoftKeyboard(userInput);

                                renameDescription(userInput.getText().toString(), index);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                hideSoftKeyboard(currentEditText);
                                hideSoftKeyboard(userInput);

                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void renameDescription(String modifiedDescription, int index) {
        if (weightData == null)
            return;

        Data newData = new Data(Config.table_name, weightData.categories.get(index), weightData.codes.get(index), modifiedDescription, weightData.codes.get(index));
        if (Data.hasValue(newData)) {
            Toast.makeText(this.getApplicationContext(), getResources().getText(R.string.value_dupulicated), Toast.LENGTH_SHORT).show();
            return;
        }

        int result = Data.updateWeight(weightData.categories.get(index), weightData.codes.get(index), weightData.descriptions.get(index), modifiedDescription);

        if (result < 0) {
            Toast.makeText(this.getApplicationContext(), getResources().getText(R.string.rename_fail), Toast.LENGTH_SHORT).show();
            return;
        }

        weightData.descriptions.set(index, modifiedDescription);

        customListAdapteradapter.notifyDataSetChanged();
    }

    public void saveFormulaWeight(String description) {

    }

    public void showListView() {
        if (listTopSpaceLayout == null || weightListLayout == null)
            return;

//        if (listviewLayout.getVisibility() == View.GONE)
//            listviewLayout.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams topLayoutParams = (LinearLayout.LayoutParams) listTopSpaceLayout.getLayoutParams();
        topLayoutParams.height = (int) getResources().getDimension(R.dimen.list_topspace_height);
        topLayoutParams.weight = 0;

        LinearLayout.LayoutParams listLayoutParams = (LinearLayout.LayoutParams) weightListLayout.getLayoutParams();

        DisplayMetrics metrics;
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        listLayoutParams.weight = 1;

        listTopSpaceLayout.setLayoutParams(topLayoutParams);
        weightListLayout.setLayoutParams(listLayoutParams);
    }

    public void minimizeListView() {
        if (listTopSpaceLayout == null || weightListLayout == null)
            return;

//        listviewLayout.setVisibility(View.VISIBLE);

        LinearLayout.LayoutParams topLayoutParams = (LinearLayout.LayoutParams) listTopSpaceLayout.getLayoutParams();
        topLayoutParams.weight = 1;

        LinearLayout.LayoutParams listLayoutParams = (LinearLayout.LayoutParams) weightListLayout.getLayoutParams();

        DisplayMetrics metrics;
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        listLayoutParams.height = (int) getResources().getDimension(R.dimen.list_view_min_height);
        listLayoutParams.weight = 0;
    }

    public void closeListView() {
        listviewLayout.setVisibility(View.GONE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == firstFieldUnit) {
            selectedFirstUnit(position);
        } else if (parent == secondFieldUnit) {
            selectedSecondUnit(position);
        } else if (parent == thirdFieldUnit) {
            selectedThirdUnit(position);
        }
    }

    public void selectedFirstUnit(int index) {

    }

    public void selectedSecondUnit(int index) {

    }

    public void selectedThirdUnit(int index) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void firstFieldValueIsEmpty() {

    }

    public void firstFieldValueIsNotEmpty() {

    }

    public void secondFieldValueIsEmpty() {

    }

    public void secondFieldValueIsNotEmpty() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (firstFieldValue.getText().toString().length() > 0) {
            firstFieldValueIsNotEmpty();
        } else {
            firstFieldValueIsEmpty();
        }

        if (secondFieldValue.getText().toString().length() > 0) {
            secondFieldValueIsNotEmpty();
        } else {
            secondFieldValueIsEmpty();
        }
    }

    public void deleteFormulaWeight(int index) {
        if (weightData == null)
            return;

        Data.deleteWeight(weightData.categories.get(index), weightData.codes.get(index), weightData.descriptions.get(index));

        weightData.remove(index);

        customListAdapteradapter.notifyDataSetChanged();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            Log.v("Molarity", "has focuse");
            currentEditText = (EditText) v;

            hideSoftKeyboard(v);

//            if (inputType == currentEditText.getInputType()) {
//                Log.v("Molarity", "=================");
//            } else {
//                Log.v("Molarity", "=========++++++++++++========");
//            }

            int inType = currentEditText.getInputType(); // backup the input type
            currentEditText.setInputType(InputType.TYPE_NULL); // disable soft input
            currentEditText.onWindowFocusChanged(hasFocus);
            currentEditText.setInputType(inType); // restore input type

            if (currentEditText != thirdFieldValue) {
                nextButton.setText(R.string.next_button);
            } else {
                nextButton.setText(R.string.done_button);
            }
        } else {
            Log.v("Molarity", "loose focuse");
        }
    }

    @Override
    public void onBackPressed() {
        Log.v("Molarity", "back pressed");
        if (keyboardLayout.getVisibility() == View.VISIBLE) {
            hideKeyboard();
        } else {
            super.onBackPressed();
        }
    }

    public void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)  getBaseContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
