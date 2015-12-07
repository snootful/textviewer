package com.example.textviewer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri uri = intent.getData();

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            if (inputStream != null) {
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();

                StringBuffer text = new StringBuffer();
                while (line != null) {
                    text.append(line);
                    text.append("\n");
                    line = bufferedReader.readLine();
                }

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(text);

                reader.close();
            }
        }catch (Exception e) {
            Log.d("MainActivity", e.toString());
            e.printStackTrace();
        }
    }
}
