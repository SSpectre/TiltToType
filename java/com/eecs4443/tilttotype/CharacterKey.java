package com.eecs4443.tilttotype;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatButton;

public class CharacterKey extends AppCompatButton implements Key {
    private MainActivity activity;
    private CharSequence character;

    public CharacterKey(Context context) {
        super(context);
        initialize();
    }

    public CharacterKey(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CharacterKey(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @Override
    public void initialize()
    {
        activity = (MainActivity)getContext();
        character = getText();
    }

    @Override
    public void keyAction()
    {
        activity.sb.append(character);
    }
}
