package com.morimoku.alertmanagerexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmNotice extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"your time is up!",Toast.LENGTH_LONG).show();
        Log.v("time","your time is up");

    }
}
