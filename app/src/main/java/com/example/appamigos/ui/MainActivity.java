package com.example.appamigos.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.appamigos.R;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISO_VARIOS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obtenerPermisos();
        comprobarPermisoContactos();
    }

    private void obtenerPermisos() {

        int permisoContactos = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS);
        int permisoLog = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG);
        int permisoLlamadas = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE);

        if (permisoContactos != PackageManager.PERMISSION_GRANTED || permisoLog != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_PHONE_STATE}, PERMISO_VARIOS);
            }

        }
    }

    private void comprobarPermisoContactos() {
        int result = PackageManager.PERMISSION_GRANTED;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            result = checkSelfPermission(Manifest.permission.READ_CONTACTS);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permisosConcedidos = 0;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                permisosConcedidos++;

            }
        }

        if (permisosConcedidos == grantResults.length) {

        } else {
            obtenerPermisos();
        }

    }
}