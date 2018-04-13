package pushnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.userone.myapplication.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by userone on 4/12/2018.
 */

public class CloudMessagingService extends FirebaseMessagingService {
    RemoteViews contentViewBig,contentViewSmall;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        createNotification(remoteMessage);
    }

    private void createNotification(RemoteMessage remoteMessage) {
        Intent intent = new Intent( this , ResultActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity( this , 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        contentViewBig = new RemoteViews(getPackageName(), R.layout.custom_notification);
        contentViewSmall = new RemoteViews(getPackageName(),R.layout.custom_notification_small);

        contentViewBig.setImageViewResource(R.id.image_app, R.mipmap.ic_heart_red_launcher);
        contentViewSmall.setImageViewResource(R.id.image_app, R.mipmap.ic_heart_red_launcher);

        /*contentViewBig.setTextViewText(R.id.nottitle, remoteMessage.getNotification().getTitle());
        contentViewSmall.setTextViewText(R.id.nottitle, remoteMessage.getNotification().getTitle());*/

        contentViewBig.setTextViewText(R.id.nottitle, "My Application");
        contentViewSmall.setTextViewText(R.id.nottitle, "My Application");

        contentViewBig.setTextViewText(R.id.text, remoteMessage.getNotification().getBody());
        contentViewSmall.setTextViewText(R.id.text, remoteMessage.getNotification().getBody());

        /*Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this,"Messages_Channel");

        mNotificationBuilder.setSmallIcon(R.mipmap.ic_heart_red_launcher)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setColor(getResources().getColor(R.color.colorAccent))
                .setAutoCancel( true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            notificationManager.notify(0, mNotificationBuilder.build());
        }*/


        /**
         * Custom layout for push notification
         */
        Intent notificationIntent = new Intent(getApplicationContext(), ResultActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_heart_red_launcher)
                .setCustomContentView(contentViewSmall)
                .setCustomBigContentView(contentViewBig)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());

        mNotificationManager.notify(1, notificationBuilder.build());

    }
}
