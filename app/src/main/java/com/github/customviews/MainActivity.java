package com.github.customviews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mPlusButton;
    private Button mMinusButton;
    private TextView mTextView;
    private int mNumInches = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.length_picker);

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
