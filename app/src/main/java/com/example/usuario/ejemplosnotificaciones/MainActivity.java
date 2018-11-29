package com.example.usuario.ejemplosnotificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "mi canal" ;
    Button buttonNotificacionSencilla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonNotificacionSencilla = findViewById(R.id.buttonNotificacionSencilla);

        buttonNotificacionSencilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notificaciónSencilla();
          
            }
        });
    }
        public void notificaciónSencilla() {

            /**Id para la notificación*/
            int notifyId = 001;

            /**Obtenemos una instancia NotificationManager service**/
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            /**Obtenemos una instancia de NotificationManager**/
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentTitle("Mi notificación sencilla")
                            .setAutoCancel(true)
                            .setContentText("Texto de una notificación sencilla.");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                /**Para Android Oreo o superiores**/

                /**EN Android Oreo necesitamos crear un Canal para mostrar las notificaciones. **/
                String channelId1 = "1";
                String channelName1 = "channel1";

                /**Iniciamos NotificationChannel**/
                NotificationChannel channel = new NotificationChannel(channelId1, channelName1, NotificationManager.IMPORTANCE_DEFAULT);

                /**Activamos la notificación por luz, vibración y Enable Notify Light, Vibration, mostramos la placa**/
                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.setShowBadge(true);
                channel.enableVibration(true);

               //Asociamos el canal
                builder.setChannelId(channelId1);


                if (mNotificationManager != null) {
                    mNotificationManager.createNotificationChannel(channel);
                }

            } else {
                //Para versiones antiguas de Android

                //Pones los valores por defecto de sonido luz y vibración
                builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            }


            Intent intent = getIntent();


            TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());


            stackBuilder.addNextIntent(intent);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(001, PendingIntent.FLAG_UPDATE_CURRENT);


            builder.setContentIntent(pendingIntent);

            /***
             * Cuando la notificación es del mismo evento pero con diferente información lo suyo
             * es actualizar la notificación existente usando el ID. Si son notificaciones diferentes creamos nuevos ID
             * **/



            if (mNotificationManager != null) {
                mNotificationManager.notify(notifyId, builder.build());
            }
        }

        /**************************Notificación en una caja*************************/
        public void inboxStyleNotification() {

            int notifyId = 002;

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Title")
                    .setContentText("Message Received")
                    .setAutoCancel(true);

            /**Definimos el tipo de notificación **/
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();


            inboxStyle.setBigContentTitle("Mensaje expandido.");

            /**Añade todos los mensajes necesarios**/
            inboxStyle.addLine("Mensaje 1");
            inboxStyle.addLine("Mensaje 2");
            inboxStyle.addLine("Mensaje 3");
            inboxStyle.addLine("Mensaje 4");
            inboxStyle.addLine("Mensaje 5");


            mBuilder.setStyle(inboxStyle);

            Intent intent = getIntent();
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);

            stackBuilder.addNextIntent(intent);
            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder.setContentIntent(pIntent);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                String channelId2 = "2";
                String channelName2 = "channel2";

                NotificationChannel channel = new NotificationChannel(channelId2, channelName2, NotificationManager.IMPORTANCE_DEFAULT);

                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.setShowBadge(true);
                channel.enableVibration(true);

                mBuilder.setChannelId(channelId2);

                if (mNotificationManager != null) {
                    mNotificationManager.createNotificationChannel(channel);
                }
            } else {
                mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            }

            if (mNotificationManager != null) {
                mNotificationManager.notify(notifyId, mBuilder.build());
            }
        }

        /*****************Notificaciones con texto largo *********************/

        public void notificaciónTextoGrande() {

            int notifyId = 003;

            Bitmap bigIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);

            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.bigText("Este es un ejemplo de un texto largo para probar las notificaciones con texto largo. Aquí podemos poner un gran texto que luego podrá ser leído.");
            bigText.setSummaryText("Resumen del texto largo");

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Título de mi textolargo")
                    .setLargeIcon(bigIcon)
                    .setStyle(bigText);

            Intent intent = getIntent();
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
            mBuilder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId2 = "2";
                String channelName2 = "channel2";

                NotificationChannel channel = new NotificationChannel(channelId2, channelName2, NotificationManager.IMPORTANCE_DEFAULT);

                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.setShowBadge(true);
                channel.enableVibration(true);

                mBuilder.setChannelId(channelId2);

                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(channel);
                }
            } else {
                mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            }

            if (notificationManager != null) {
                notificationManager.notify(notifyId, mBuilder.build());
            }
        }

        /*******************Notificación con Imagen***********************/
        public void notificaciónConImagen() {

            int notifyId = 004;

            NotificationCompat.BigPictureStyle bpStyle = new NotificationCompat.BigPictureStyle();
            bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.raw.logo)).build();

            Intent rIntent = getIntent();
            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, rIntent, 0);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Notificación con una gran imagen")
                    .addAction(android.R.drawable.ic_menu_share, "Mi Logo", pendingIntent)
                    .setStyle(bpStyle);

            mBuilder.setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId2 = "2";
                String channelName2 = "channel2";

                NotificationChannel channel = new NotificationChannel(channelId2, channelName2, NotificationManager.IMPORTANCE_DEFAULT);

                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.setShowBadge(true);
                channel.enableVibration(true);

                mBuilder.setChannelId(channelId2);

                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(channel);
                }
            } else {
                mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            }

            if (notificationManager != null) {
                notificationManager.notify(notifyId, mBuilder.build());
            }
        }
}
