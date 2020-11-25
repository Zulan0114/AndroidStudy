package com.example.looperandhandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private SimpleWorker worker;
    private TextView tvMessage;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            tvMessage.setText((String)msg.obj);
        }
    };

    private Worker worker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMessage = findViewById(R.id.tv_message);
//        worker = new SimpleWorker();
        worker = new Worker();
        worker.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = Message.obtain();
            msg.obj = "Task 1 completed";
            handler.sendMessage(msg);
        })
        .execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = Message.obtain();
            msg.obj = "Task 2 completed";
            handler.sendMessage(msg);
        })
        .execute(()-> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = Message.obtain();
            msg.obj = "Task 3 completed";
            handler.sendMessage(msg);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        worker.quit();
    }
}