package com.example.eventbusdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private Button btnPublish;
    private EditText editTextPublish;
    private TextView tvMessage;

    private Button btnPostSticky;
    private EditText editTextSticky;
    private Button btnStartOtherActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        btnPublish = findViewById(R.id.btnPublish);
        btnPublish.setOnClickListener(this);

        editTextPublish = findViewById(R.id.editTextPublish);
        tvMessage = findViewById(R.id.tvMessage);

        btnPostSticky = findViewById(R.id.btnPostSticky);
        btnPostSticky.setOnClickListener(this);

        editTextSticky = findViewById(R.id.editTextSticky);

        btnStartOtherActivity = findViewById(R.id.btnStartOtherActivity);
        btnStartOtherActivity.setOnClickListener(this);

        enableComponents(false);
    }

    private void enableComponents(boolean enabled) {
        btnPublish.setEnabled(enabled);
        editTextPublish.setEnabled(enabled);
        btnPostSticky.setEnabled(enabled);
        editTextSticky.setEnabled(enabled);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextPublish.setText("");
        editTextSticky.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                EventBus.getDefault().register(this);
                Toast.makeText(this, "Registered...", Toast.LENGTH_SHORT).show();
                btnRegister.setEnabled(false);
                enableComponents(true);
                break;
            case R.id.btnPublish:
                String message = editTextPublish.getText().toString();
                if (message == null || message.isEmpty()) {
                    Toast.makeText(this, "Please enter the Pubish Message...", Toast.LENGTH_SHORT).show();
                } else {
                    EventBus.getDefault().post(new Message(message));
                    Toast.makeText(this, "Published...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnPostSticky:
                String stickyMessage = editTextSticky.getText().toString();
                if (stickyMessage == null || stickyMessage.isEmpty()) {
                    Toast.makeText(this, "Please enter the Post Sticky Message...", Toast.LENGTH_SHORT).show();
                } else {
                    EventBus.getDefault().postSticky(new Message(stickyMessage));
                    Toast.makeText(this, "Post Sticky...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnStartOtherActivity:
                startActivity(new Intent(this, OtherActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessageRsp(Message message) {
        if (message != null && message.getMessage() != null) {
            tvMessage.setText(message.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}