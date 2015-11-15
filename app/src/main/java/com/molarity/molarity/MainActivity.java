package com.molarity.molarity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.molarity.molarity.dilute.DiluteActivity;
import com.molarity.molarity.mass.MassActivity;
import com.molarity.molarity.molarity.MolarityActivity;
import com.molarity.molarity.moreapp.MoreappActivity;
import com.molarity.molarity.volume.VolumeActivity;


public class MainActivity extends Activity implements View.OnClickListener {

    private ImageButton massButton;
    private ImageButton volumeButton;
    private ImageButton molarityButton;
    private ImageButton diluteButton;

    private TextView moreAppButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setupAction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void initView() {
        massButton = (ImageButton)findViewById(R.id.mass_from_volume);
        volumeButton = (ImageButton) findViewById(R.id.volume_from_mass);
        molarityButton = (ImageButton) findViewById(R.id.molarity_from_mass);
        diluteButton = (ImageButton) findViewById(R.id.dilute);

        moreAppButton = (TextView) findViewById(R.id.more_app);
    }

    public void setupAction() {
        massButton.setOnClickListener(this);
        volumeButton.setOnClickListener(this);
        molarityButton.setOnClickListener(this);
        diluteButton.setOnClickListener(this);

        moreAppButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View button) {
        Intent intent = null;

        if (button == massButton) {
            intent = new Intent(this, MassActivity.class);

        } else if (button == volumeButton) {
            intent = new Intent(this, VolumeActivity.class);
        } else if (button == molarityButton) {
            intent = new Intent(this, MolarityActivity.class);
        } else if (button == diluteButton) {
            intent = new Intent(this, DiluteActivity.class);
        } else if (button == moreAppButton) {
            intent = new Intent(this, MoreappActivity.class);
        }

        if (intent != null)
            startActivity(intent);
    }
}
