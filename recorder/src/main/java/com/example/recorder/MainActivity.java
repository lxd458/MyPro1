package com.example.recorder;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA},100);

    }

    public void record(View view) {
      startActivity(new Intent(this,Main2Activity.class));
    }

    public void playvidio(View view) {
    }

    public void playaudio(View view) {
    }
}
