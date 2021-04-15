package com.eecs4443.tilttotype;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

public class CharacterKey extends AppCompatButton implements Key {
    private KeyboardActivity activity;
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
        activity = (KeyboardActivity)getContext();
        character = getText();
    }

    @Override
    public void keyAction()
    {
        activity.typedBuffer.append(character);
    }
}
