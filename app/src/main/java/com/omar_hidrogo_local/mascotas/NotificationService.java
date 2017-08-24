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

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d(TAG, "FROM: "+remoteMessage.getFrom());
        Log.d(TAG, "Notificacion Message Body: " + remoteMessage.getNotification().getBody());

        Intent i = new Intent();
        i.setAction("FOLLOW");
       // i.setAction("UNFOLLOW");

        //Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent =   PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        //PendingIntent pendingIntent2 =   PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action actionfollow = new NotificationCompat.Action.Builder(R.drawable.ic_full_hand,
                getString(R.string.texto_accion_seguir),pendingIntent)
                .build();

        /*NotificationCompat.Action actionunfollow = new NotificationCompat.Action.Builder(R.drawable.ic_full_hand_no,
                getString(R.string.texto_accion_noseguri),pendingIntent2)
                .build();*/

       /* NotificationCompat.Action actionperfil = new NotificationCompat.Action.Builder(R.drawable.ic_full_user,
                getString(R.string.verperfil),pendingIntent)
                .build();*/

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
                .extend(wearableExtender.addAction(actionfollow))
                //.extend(wearableExtender.addAction(actionunfollow))
                //.extend(wearableExtender.addAction(actionperfil))
                        //.addAction(R.drawable.ic_full_hand,getString(R.string.texto_accion_toque), pendingIntent)
                ;

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());

    }
}
