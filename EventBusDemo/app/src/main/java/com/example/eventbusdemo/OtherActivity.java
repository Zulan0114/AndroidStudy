package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class OtherActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegisterSticky;
    private TextView tvSticky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        btnRegisterSticky = findViewById(R.id.btnRegisterSticky);
        btnRegisterSticky.setOnClickListener(this);

        tvSticky = findViewById(R.id.tvSticky);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetMessage(Message message) {
        if (message != null && message.getMessage() != null) {
            tvSticky.setText(message.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnRegisterSticky:
                EventBus.getDefault().register(this);
                btnRegisterSticky.setEnabled(false);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}