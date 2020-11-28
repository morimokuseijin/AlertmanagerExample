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

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocalTime now = LocalTime.now().plusMinutes(5);
                Date calendar = localTimeToDate(now);
                //Date has no problem!
                Calendar calendar1 = dateToCalendar(calendar);
                long calendar_long = calendar1.getTimeInMillis();
                Date calendar_human = calendar1.getTime();

                Intent intent = new Intent(getApplicationContext(),
                        AlarmNotice.class);
                PendingIntent pending = PendingIntent.getBroadcast(
                        getApplicationContext(), 0, intent, 0);

                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                if(am != null){
                    am.setExact(AlarmManager.RTC_WAKEUP, calendar_long, pending);
                }
                Toast.makeText(getApplicationContext(),"Your set time is"+
                       calendar_long, Toast.LENGTH_SHORT).show();

                Log.v("time", String.valueOf(calendar_long));
                Log.v("time", String.valueOf(calendar_human));
            }

        });
        }
    private static Date localTimeToDate (LocalTime localTime){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.set(0,0,0,localTime.getHour(),localTime.getMinute(),localTime.getSecond());
        return calendar.getTime();

    }

    private static Calendar dateToCalendar(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }
}