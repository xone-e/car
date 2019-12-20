package com.example.mycar.receiver;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mycar.Notification;
import com.example.mycar.R;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by sajad on 7/9/2018.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        Notification.createNotificationChannel(context);

        Notification.createNotification(context);
    }



    public void setAlarm(AppCompatActivity activity, long milliseconds) {
        Intent intent = new Intent(activity, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                activity.getApplicationContext(), 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) activity.getApplicationContext().getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, milliseconds, pendingIntent);
        }
    }

   /* public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, BroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 10, pi); // Millisec * Second * Minute
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, BroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }*/
}
