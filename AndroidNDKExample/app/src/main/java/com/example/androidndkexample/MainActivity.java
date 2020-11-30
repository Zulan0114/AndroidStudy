package com.example.androidndkexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    EditText editTextX;
    EditText editTextY;
    TextView resultAdd;
    TextView resultSub;
    TextView resultMultiply;
    TextView resultDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        Button add = findViewById(R.id.buttonAdd);
        add.setOnClickListener(this);
        Button sub = findViewById(R.id.buttonSub);
        sub.setOnClickListener(this);
        Button multiply = findViewById(R.id.buttonMultiply);
        multiply.setOnClickListener(this);
        Button divide = findViewById(R.id.buttonDivide);
        divide.setOnClickListener(this);

        editTextX = findViewById(R.id.editTextX);
        editTextY = findViewById(R.id.editTextY);

        resultAdd = findViewById(R.id.textViewAdd);
        resultSub = findViewById(R.id.textViewSub);
        resultMultiply = findViewById(R.id.textViewMultiply);
        resultDivide = findViewById(R.id.textViewDivide);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native int add(int x, int y);
    public native int sub(int x, int y);
    public native int multiply(int x, int y);
    public native int divide(int x, int y);

    @Override
    public void onClick(View v) {
        String x = editTextX.getText().toString();
        String y = editTextY.getText().toString();
        switch (v.getId()) {
            case R.id.buttonAdd:
                if (!(TextUtils.isEmpty(x) && TextUtils.isEmpty(y))) {
                    int result = add(Integer.parseInt(x), Integer.parseInt(y));
                    resultAdd.setText(String.valueOf(result));
                }
                break;
            case R.id.buttonSub:
                if (!(TextUtils.isEmpty(x) && TextUtils.isEmpty(y))) {
                    int result = sub(Integer.parseInt(x), Integer.parseInt(y));
                    resultSub.setText(String.valueOf(result));
                }
                break;
            case R.id.buttonMultiply:
                if (!(TextUtils.isEmpty(x) && TextUtils.isEmpty(y))) {
                    int result = multiply(Integer.parseInt(x), Integer.parseInt(y));
                    resultMultiply.setText(String.valueOf(result));
                }
                break;
            case R.id.buttonDivide:
                if (!(TextUtils.isEmpty(x) && TextUtils.isEmpty(y))) {
                    int result = divide(Integer.parseInt(x), Integer.parseInt(y));
                    resultDivide.setText(String.valueOf(result));
                }
                break;
            default:
        }
    }
}