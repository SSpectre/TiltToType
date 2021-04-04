package com.eecs4443.tilttotype;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageButton;

public class ActionKey extends AppCompatImageButton implements Key{
    private KeyboardActivity activity;
    private Action action;

    public ActionKey(Context context) {
        super(context);
        initialize();
    }

    public ActionKey(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ActionKey(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @Override
    public void initialize() {
        activity = (KeyboardActivity)getContext();
        switch (getId())
        {
            case R.id.keyBackspace:
                action = Action.BACKSPACE;
                break;
            case R.id.keyEnter:
                action = Action.ENTER;
                break;
            default:
                action = Action.OTHER;
                break;
        }
    }

    @Override
    public void keyAction()
    {
        switch (action)
        {
            case BACKSPACE:
                if (activity.sb.length() > 0)
                    activity.sb.deleteCharAt(activity.sb.length() - 1);
                break;
            case ENTER:
                break;
            default:
                break;
        }
    }

    public enum Action
    {
        OTHER,
        BACKSPACE,
        ENTER
    }
}
