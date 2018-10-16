package com.omelchenkoaleks.joke;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class DelayedMessageService extends IntentService {

    // используем константу для передачи сообщения от активности к службе
    public static final String EXTRA_MESSAGE = "message";

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
        Log.v("DelayedMessageService", "The message is: " + text);
    }
}
