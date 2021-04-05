package com.eecs4443.tilttotype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class SetupActivity extends Activity {
    RadioGroup modeSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_layout);

        modeSelect = findViewById(R.id.modeRadio);
    }

    public void onClick(View v)
    {
        boolean tiltMode = false;
        if (modeSelect.getCheckedRadioButtonId() == R.id.tiltMode)
            tiltMode = true;

        Bundle b = new Bundle();
        b.putBoolean("tiltMode", tiltMode);

        Intent i = new Intent(getApplicationContext(), KeyboardActivity.class);
        i.putExtras(b);
        startActivity(i);
    }
}
