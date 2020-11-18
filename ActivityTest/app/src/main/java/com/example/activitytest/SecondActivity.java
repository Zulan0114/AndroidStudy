package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d("SecondActivity", this.toString());
        Log.d("SecondActivity", "Task id is "+getTaskId());
        setContentView(R.layout.second_layout);

//        // get the data from another activity
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.d("SecondActivity", data);

        Button button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);

//                Intent intentReturn = new Intent();
//                intentReturn.putExtra("data_return", "Hello FirstActivity");
//                setResult(RESULT_OK, intentReturn);
//                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intentReturn = new Intent();
        intentReturn.putExtra("data_return", "Hello FirstActivity");
        setResult(RESULT_OK, intentReturn);
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity", "onDestroy");
    }
}