package com.demo.firebasedemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

    private int notificationId = 0;
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

        context = getBaseContext();

        if(message == null){
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //创建通知渠道
            NotificationChannel channel = new NotificationChannel(
                    context.getPackageName(), "MyChannel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        String title = message.getNotification().getTitle();
        String content = message.getNotification().getBody();
        Log.d(TAG, "title: " +title);
        Log.d(TAG, "content: "+content);

        // 在服务或后台任务中创建通知
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        // 创建一个Intent指向你想要启动的Activity
//        Intent intent = new Intent(context, SecondActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addNextIntentWithParentStack(intent);
//        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
//                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // 构建Uri
        Uri deepLinkUri = Uri.parse("myapp://example.com/deeplink");
        // 创建Intent
        Intent intent = new Intent(Intent.ACTION_VIEW, deepLinkUri);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // 构建通知
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pendingIntent) // 绑定点击行为
                .setAutoCancel(true); // 点击后自动取消通知

        // 发送通知
        Notification notification = builder.build();
        notificationManager.notify(notificationId++, notification);
    }
}
