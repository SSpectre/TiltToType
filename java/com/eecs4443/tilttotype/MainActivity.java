package com.eecs4443.tilttotype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private final int KEYBOARD_ROWS = 5;
    private final int KEYBOARD_COLS = 10;

    private final int SPACE_ROW = 4;
    private final int SPACE_COL = 2;
    private final int SPACE_WIDTH = 5; //number of EXTRA key widths the spacebar takes

    private TextView typedText;
    private View[][] keyboard = new View[KEYBOARD_ROWS][KEYBOARD_COLS];
    private Key focusKey;

    private SensorManager sm;
    private Sensor sensor;
    private float initialPitch;
    private float initialRoll;

    public StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typedText = (TextView)findViewById(R.id.typedText);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        //initialPitch = SensorManager.get

        //get keys from layout and place in array
        TableLayout table = (TableLayout)findViewById(R.id.keyboardLayout);
        for (int i = 0; i < KEYBOARD_ROWS; i++) {
            TableRow tRow = (TableRow) table.getChildAt(i);
            for (int j = 0; j < KEYBOARD_COLS; j++) {
                //account for size of the spacebar
                if (i == SPACE_ROW)
                {
                    if (j > SPACE_COL && j <= SPACE_COL + SPACE_WIDTH)
                        keyboard[i][j] = keyboard[SPACE_ROW][SPACE_COL];
                    else if (j > SPACE_COL + SPACE_WIDTH)
                        keyboard[i][j] = tRow.getChildAt(j - SPACE_WIDTH);
                    else
                        keyboard[i][j] = tRow.getChildAt(j);
                }
                else
                    keyboard[i][j] = tRow.getChildAt(j);
            }
        }

        changeFocus(0, 0);
    }

    private void changeFocus(int x, int y)
    {
        keyboard[x][y].requestFocus();
        focusKey = (Key)keyboard[x][y];
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL); // good enough!
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        // not needed, but we need to provide an implementation anyway
    }

    @Override
    public void onSensorChanged(SensorEvent se)
    {
        Log.i("Debug", String.valueOf(se.sensor.getType()));
        float pitch = se.values[0];
        float roll = se.values[2];
    }

    public void clickKeyboard(View v)
    {
        focusKey.keyAction();
        typedText.setText(sb);
    }
}