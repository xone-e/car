package com.example.mycar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.mycar.activities.MainActivity;

public class Notification {
    private static final String CHANNEL_NOTIFICATION_SERVICE_ID = "channelNotificationService";

    public static void createNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);

        android.app.Notification notification = new NotificationCompat.Builder(context, CHANNEL_NOTIFICATION_SERVICE_ID)
                .setContentTitle("یادآوری سرویس")
                .setContentText("2 روز مانده به انجام اخرین سرویس شما!")
                .setSmallIcon(R.drawable.ic_notifications_active_black)
                .setChannelId(CHANNEL_NOTIFICATION_SERVICE_ID)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(0, notification);
    }

    public static void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name); // The user-visible name of the channel.
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channelNotificationService = new NotificationChannel(CHANNEL_NOTIFICATION_SERVICE_ID, name, importance);
            channelNotificationService.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channelNotificationService);
        }
    }

}
