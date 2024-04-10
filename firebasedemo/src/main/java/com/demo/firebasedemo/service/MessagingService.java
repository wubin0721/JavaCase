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
    public void onDeletedMessages() {
        super.onDeletedMessages();
        /*
        在某些情况下，FCM 可能不会传递消息。比如说，如果在特定设备连接到 FCM 时，您的应用在该设备上的待处理消息过多（超过 100 条），
        或者设备超过一个月未连接到 FCM，就会发生这种情况。在这些情况下，您可能会收到对 FirebaseMessagingService.onDeletedMessages()
        的回调。当应用实例收到此回调时，应该执行一次与应用服务器的完全同步。如果您在过去 4 周内未向该设备上的应用发送消息，FCM 将不会调用
        onDeletedMessages()
         */
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
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        String title = message.getNotification().getTitle();
        String content = message.getNotification().getBody();
        Log.d(TAG, "title: " +title);
        Log.d(TAG, "content: "+content);

        // 在服务或后台任务中创建通知
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        // 创建一个Intent指向你想要启动的Activity
//        Intent intent = new Intent(context, SecondActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addNextIntentWithParentStack(intent);
//        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
//                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // 构建Uri ，对应AndroidManifest中的 <data>标签
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
