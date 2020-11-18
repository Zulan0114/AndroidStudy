package com.example.componettest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
//            EditText editText = findViewById(R.id.editText);
//            String inputText = editText.getText().toString();
//            Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();

//            ImageView imageView = findViewById(R.id.imageView);
//            imageView.setImageResource(R.drawable.img_2);

            ProgressBar progressBar = findViewById(R.id.progressBar);
//            if (progressBar.getVisibility() == View.GONE) {
//                progressBar.setVisibility(View.VISIBLE);
//            } else {
//                progressBar.setVisibility(View.GONE);
//            }
            int progress = progressBar.getProgress();
            progress = progress + 10;
            progressBar.setProgress(progress);

//            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//            dialog.setTitle("This is Dialog");
//            dialog.setMessage("Something important");
//            // 是否可以通过back键取消
//            dialog.setCancelable(false);
//            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    // TODO
//                }
//            });
//
//            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    // TODO
//                }
//            });
//            dialog.show();

            ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("This is ProgressDialog");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(true);
            progressDialog.show();
        });
    }
}