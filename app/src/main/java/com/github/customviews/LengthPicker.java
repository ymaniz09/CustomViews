package com.github.customviews;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class LengthPicker extends ConstraintLayout {

    private static final String KEY_SUPER_STATE = "com.github.customviews.LengthPicker.SuperState";
    private static final String KEY_NUMBER_INCHES = "com.github.customviews.LengthPicker.NumInches";
    private Button mPlusButton;
    private Button mMinusButton;
    private TextView mTextView;
    private int mNumInches = 0;

    public LengthPicker(Context context) {
        super(context);
        init();
    }

    public LengthPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LengthPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SUPER_STATE, super.onSaveInstanceState());
        bundle.putInt(KEY_NUMBER_INCHES, mNumInches);

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mNumInches = bundle.getInt(KEY_NUMBER_INCHES);

            super.onRestoreInstanceState(bundle.getParcelable(KEY_SUPER_STATE));
        } else {
            super.onRestoreInstanceState(state);
        }
        updateControls();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.length_picker, this);

        mPlusButton = findViewById(R.id.plus_button);
        mMinusButton = findViewById(R.id.minus_button);
        mTextView = findViewById(R.id.length_picker);

        updateControls();

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.plus_button:
                        mNumInches++;
                        updateControls();
                        break;
                    case R.id.minus_button:
                        mNumInches--;
                        updateControls();
                        break;
                }
            }
        };

        mPlusButton.setOnClickListener(listener);
        mMinusButton.setOnClickListener(listener);
    }

    private void updateControls() {
        int feet = mNumInches / 12;
        int inches = mNumInches % 12;

        String text = String.format("%d' %d\"", feet, inches);

        if (feet == 0) {
            text = String.format("%d\"", inches);
        } else {
            if (inches == 0) {
                text = String.format("%d'", feet);
            }
        }

        mTextView.setText(text);

        mMinusButton.setEnabled(mNumInches > 0);
    }
}
