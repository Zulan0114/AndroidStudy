package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class OtherActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegisterSticky;
    private TextView tvSticky;

    private Button btnPostSticky2;
    private EditText editTextPostMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        btnRegisterSticky = findViewById(R.id.btnRegisterSticky);
        btnRegisterSticky.setOnClickListener(this);

        tvSticky = findViewById(R.id.tvSticky);

        btnPostSticky2 = findViewById(R.id.btnPostSticky2);
        btnPostSticky2.setOnClickListener(this);

        editTextPostMessage = findViewById(R.id.editTextPostMessage);

        enableComponents(false);
    }

    private void enableComponents(boolean enabled) {
        editTextPostMessage.setEnabled(enabled);
        btnPostSticky2.setEnabled(enabled);
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
                enableComponents(true);
                break;
            case R.id.btnPostSticky2:
                String message = editTextPostMessage.getText().toString();
                if (message == null || message.isEmpty()) {
                    Toast.makeText(this, "Please enter message...", Toast.LENGTH_SHORT).show();
                } else {
                    EventBus.getDefault().postSticky(new Message(message));
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextPostMessage.setText("");
    }
}