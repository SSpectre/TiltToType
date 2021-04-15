package com.eecs4443.tilttotype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SetupActivity extends Activity {
    String[] participantCode = {"P99", "P01", "P02", "P03", "P04", "P05", "P06", "P07", "P08", "P09", "P10", "P11",
            "P12", "P13", "P14", "P15", "P16", "P17", "P18", "P19", "P20", "P21", "P22", "P23", "P24", "P25"};
    String[] sessionCode = {"S99", "S01", "S02", "S03", "S04", "S05", "S06", "S07", "S08", "S09", "S10", "S11", "S12",
            "S13", "S14", "S15", "S16", "S17", "S18", "S19", "S20", "S21", "S22", "S23", "S24", "S25"};
    String[] groupCode = {"G99", "G01", "G02", "G03", "G04", "G05", "G06", "G07", "G08", "G09", "G10", "G11", "G12",
            "G13", "G14", "G15", "G16", "G17", "G18", "G19", "G20", "G21", "G22", "G23", "G24", "G25"};

    private Spinner spinParticipantCode, spinSessionCode, spinGroupCode;
    private RadioGroup modeSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_layout);

        // get references to widgets in setup dialog
        spinParticipantCode = (Spinner)findViewById(R.id.participantSpin);
        spinSessionCode = (Spinner)findViewById(R.id.sessionSpin);
        spinGroupCode = (Spinner)findViewById(R.id.groupSpin);
        modeSelect = findViewById(R.id.modeRadio);

        // initialise spinner adapters
        ArrayAdapter<CharSequence> adapterPC = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, participantCode);
        spinParticipantCode.setAdapter(adapterPC);

        ArrayAdapter<CharSequence> adapterSC = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, sessionCode);
        spinSessionCode.setAdapter(adapterSC);

        ArrayAdapter<CharSequence> adapterGC = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, groupCode);
        spinGroupCode.setAdapter(adapterGC);
    }

    public void onClick(View v)
    {
        String part = participantCode[spinParticipantCode.getSelectedItemPosition()];
        String sess = sessionCode[spinSessionCode.getSelectedItemPosition()];
        String group = groupCode[spinGroupCode.getSelectedItemPosition()];
        boolean tiltMode = false;
        if (modeSelect.getCheckedRadioButtonId() == R.id.tiltMode)
            tiltMode = true;

        Bundle b = new Bundle();
        b.putString("participantCode", part);
        b.putString("sessionCode", sess);
        b.putString("groupCode", group);
        b.putBoolean("tiltMode", tiltMode);

        Intent i = new Intent(getApplicationContext(), KeyboardActivity.class);
        i.putExtras(b);
        startActivity(i);
    }
}
