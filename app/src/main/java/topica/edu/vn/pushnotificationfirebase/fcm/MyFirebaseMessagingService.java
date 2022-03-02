package topica.edu.vn.pushnotificationfirebase.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import topica.edu.vn.pushnotificationfirebase.MainActivity;
import topica.edu.vn.pushnotificationfirebase.MyApplication;
import topica.edu.vn.pushnotificationfirebase.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
       /* RemoteMessage.Notification notification=remoteMessage.getNotification();
        if(notification==null)
        {
            return;
        }
        String title=notification.getTitle();
        String strMessage=notification.getBody();
        sendNotification(title,strMessage);*/
        Map<String,String> stringMap=remoteMessage.getData();
        String title=stringMap.get("user_name");
        String body=stringMap.get("description");
        sendNotification(title,body);
    }

    private void sendNotification(String title, String strMessage) {
        Intent intent=new Intent( this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder=new
                NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(strMessage)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);

        Notification notification=notificationBuilder.build();
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notification!=null)
        {
            notificationManager.notify(1,notification);
        }

    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }
}
