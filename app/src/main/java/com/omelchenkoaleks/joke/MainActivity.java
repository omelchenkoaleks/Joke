package com.omelchenkoaleks.joke;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, DelayedMessageService.class);
        // добавляем текст в интент
        intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, getResources().getString(R.string.response));

        // сервис запускается этой командой
        startService(intent);
    }
}
