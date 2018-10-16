package com.omelchenkoaleks.joke;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.support.v4.app.NotificationCompat;

public class DelayedMessageService extends IntentService {

    // используем константу для передачи сообщения от активности к службе
    public static final String EXTRA_MESSAGE = "message";

    // используется для идентификации уведомления, значение выбирается произвольно
    public static final int NOTIFICATION_ID = 5453;

    // вызов конструктора суперкласса
    public DelayedMessageService() {
        super("DelayedMessageService");
    }


    // метод содержит код, который должен выпоняться при получении интента службой
    @Override
    protected void onHandleIntent(Intent intent) {

        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        // получаем текст из интента
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        // вызываем созданный метод
        showText(text);
    }

    private void showText(final String text) {

//        // лог испоьзовался для проверки работы сервиса, теперь он не нужен
//        Log.v("DelayedMessageService", "The message is: " + text);

        // создаем построителя уведомлений
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.question))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[] {0, 1000})
                .setAutoCancel(true);

        // создание действия
        Intent actionIntent = new Intent(this, MainActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // отложенный интент добавляется к уведомлению
        builder.setContentIntent(actionPendingIntent);

        // выдача уведомления
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // уведомление отображается при помощи объекта NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
