package com.omar_hidrogo_local.mascotas;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Gravity;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by tmhidrooma on 11/08/2017.
 */

public class NotificationService extends FirebaseMessagingService {

    public static final String TAG = "firebase";
    public static final int NOTIFICATION_ID = 001;
    public static final int NOTIFICATION2_ID = 002;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d(TAG, "FROM: "+remoteMessage.getFrom());
        Log.d(TAG, "Notificacion Message Body: " + remoteMessage.getNotification().getBody());

        Intent i = new Intent();
        i.setAction("TOQUE_ANIMAL");

        PendingIntent pendingIntent =   PendingIntent.getBroadcast(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_full_hand,
                getString(R.string.texto_accion_seguir),pendingIntent)
                .build();
        NotificationCompat.Action action2 = new NotificationCompat.Action.Builder(R.drawable.ic_full_hand_no,
                getString(R.string.texto_accion_noseguri),pendingIntent)
                .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(),
                                R.drawable.universo))
                        .setGravity(Gravity.CENTER_VERTICAL)
                ;

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(action))
                .extend(wearableExtender.addAction(action2))
                //.addAction(R.drawable.ic_full_hand,getString(R.string.texto_accion_toque), pendingIntent)
                ;


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());
        notificationManager.notify(NOTIFICATION2_ID, notificacion.build());
    }
}
