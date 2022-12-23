package com.example.flashlight;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flashlight.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        this.binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.button.getText().toString().equals("turn on")) {
                    binding.button.setText("turn off");
                    binding.imageView.setImageResource(R.drawable.on);
                    changeLightState(true);
                } else {
                    binding.button.setText("turn on");
                    binding.imageView.setImageResource(R.drawable.off);
                    changeLightState(false);
                }
            }
        });

    }

    private void changeLightState(boolean state) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
            String camID = null;
            try {
                camID = cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(camID, state);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.button.setText("turn on");
    }
}