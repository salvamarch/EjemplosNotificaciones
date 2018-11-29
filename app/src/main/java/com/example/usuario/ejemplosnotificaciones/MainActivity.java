package com.example.usuario.ejemplosnotificaciones;

import android.app.NotificationManager;
import android.content.Context;
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

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Ejemplo de Notificación")
                        .setContentText("Texto de notificación.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, mBuilder.build());


            }
        });
    }
}
