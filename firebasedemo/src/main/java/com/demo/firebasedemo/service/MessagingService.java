package com.demo.firebasedemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.demo.firebasedemo.R;
import com.demo.firebasedemo.SecondActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

    private static final String TAG ="MessagingService";
    private Context context;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        showNotificaiton(message);
    }


    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "token: " + token);
    }

    private void showNotificaiton(RemoteMessage message){

        if(message == null){
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        String title = message.getNotification().getTitle();
        String content = message.getNotification().getBody();
        // 在服务或后台任务中创建通知
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // 创建一个Intent指向你想要启动的Activity
        Intent intent = new Intent(this, SecondActivity.class);

        // 使用FLAG_ACTIVITY_NEW_TASK标志来确保在点击通知时会启动一个新的任务栈
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // 创建PendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        // 构建通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent) // 绑定点击行为
                .setAutoCancel(true); // 点击后自动取消通知

        // 发送通知
        Notification notification = builder.build();
        notificationManager.notify(1, notification);
    }
}
