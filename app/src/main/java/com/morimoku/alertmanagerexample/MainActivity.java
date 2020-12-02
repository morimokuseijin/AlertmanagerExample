package com.morimoku.alertmanagerexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button button, button2;
    Calendar calendar1;

    private static Date localTimeToDate(LocalTime localTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DATE), localTime.getHour(), localTime.getMinute(), localTime.getSecond());
        Log.v("timeInfo", Calendar.getInstance().get(Calendar.YEAR) + "." + Calendar.getInstance().get(Calendar.MONTH) + "." + Calendar.getInstance().get(Calendar.DATE));
        return calendar.getTime();

    }

    private static Calendar dateToCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button_easy);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalTime now = LocalTime.now().plusMinutes(1);
                Log.v("time1", String.valueOf(now));
                Date calendar = localTimeToDate(now);
                //Date has no problem!
                Log.v("time2", String.valueOf(calendar));
                Calendar calendar1 = dateToCalendar(calendar);
                Log.v("time3", String.valueOf(calendar1.getTime()));
                long calendar_long = calendar1.getTimeInMillis();
                Date calendar_human = calendar1.getTime();
                Intent intent = new Intent(getApplicationContext(),
                        AlarmNotice.class);
                PendingIntent pending = PendingIntent.getBroadcast(
                        getApplicationContext(), 0, intent, 0);
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                if (am != null) {
                    am.setExact(AlarmManager.RTC_WAKEUP, calendar_long, pending);
                }
                Toast.makeText(getApplicationContext(), "Your set time is" +
                        calendar_human, Toast.LENGTH_SHORT).show();
                Log.v("time", String.valueOf(calendar_long));
                Log.v("time", String.valueOf(calendar_human));
            }

        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                // Get the current time by millis from calendar.
                calendar.setTimeInMillis(System.currentTimeMillis());
                // Setting in 5 seconds
                calendar.add(Calendar.MINUTE, 1);

                //explicit broadcast Receiver
                Intent intent = new Intent(getApplicationContext(),
                        AlarmNotice.class);
                PendingIntent pending = PendingIntent.getBroadcast(
                        getApplicationContext(), 0, intent, 0);

                // setting alarm
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                if (am != null) {
                    am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);

                    Toast.makeText(getApplicationContext(),
                            "Set Alarm " + calendar.getTime(), Toast.LENGTH_SHORT).show();
                    Log.v("time", "Set Alarm " + calendar.getTime());
                }
            }
        });
    }
}