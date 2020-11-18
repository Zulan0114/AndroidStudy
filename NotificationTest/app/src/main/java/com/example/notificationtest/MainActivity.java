package com.example.notificationtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                int id = 1;
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // create channel
                String channelId = "default";
                String channelName = "chat";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    channel = new NotificationChannel(channelId,channelName,importance);
                    manager.createNotificationChannel(channel);
                } else {
                    Log.d("MainActivity", "error ");
                }
                // create PendingIntent, the action when you clicked the notification
                Intent intent = new Intent(this, NotificationActivity.class);
                intent.putExtra("id", id);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                // create notification
                // only black and white icons can display when using lower version android
                Notification notification = new NotificationCompat.Builder(this, channelId)
                        .setContentTitle("This is content title")
//                        .setContentText("This is content text")
                        .setContentText("Learn how to build notifications, " +
                                "send and sync data, and use voice actions. " +
                                "Get the official Android IDE and developer tools to build apps for Android.")
//                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.notice)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.notice))
                        .setContentIntent(pendingIntent)
                        // auto cancel the notification after clicked it
//                        .setAutoCancel(true)
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, " +
//                                "send and sync data, and use voice actions. " +
//                                "Get the official Android IDE and developer tools to build apps for Android."))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
                        .build();
                // notify
                manager.notify(id, notification);
                break;
            default:
                break;
        }
    }
}