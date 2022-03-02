package topica.edu.vn.pushnotificationfirebase;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplication extends Application {
    public static final String CHANNEL_ID ="push notification" ;

    @Override
    public void onCreate() {
        super.onCreate();
        createChanelNotification();
    }

    private void createChanelNotification() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"Push nottification",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}
