package com.github.customviews;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class VersionView extends AppCompatTextView {
    public VersionView(Context context) {
        super(context);
        setVersion();
    }

    public VersionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setVersion();
    }

    public VersionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setVersion();
    }

    private void setVersion() {
        String version;
        try {
            PackageInfo packageInfo = getContext().getPackageManager()
                    .getPackageInfo(getContext().getPackageName(), 0);
            version = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            version = "Not found";
        }

        setText(version);
    }
}
