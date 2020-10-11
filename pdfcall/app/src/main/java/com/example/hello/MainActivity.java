package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.nio.file.Paths;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int granted = this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        Log.i("RX300", String.format("granted: %b", granted == PackageManager.PERMISSION_GRANTED));

        requestPermissions( new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int granted = this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        Log.i("RX300", String.format("granted again: %b", granted == PackageManager.PERMISSION_GRANTED));

    }

    public void onClick(View v) {
        File documentPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        Log.i("RX300", documentPath.getAbsolutePath());
        File pdf = new File(documentPath.getAbsolutePath() + File.separator+"test.pdf");
        Log.i("RX300", String.format("%b", pdf.exists()));

//        Toast.makeText(this, pdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();

//        File[] children = documentPath.listFiles();
//        if(children != null) {
//            Log.i("RX300", String.format("%d", children.length));
//        }
//        Toast.makeText(this, children[0].getAbsolutePath(), Toast.LENGTH_SHORT).show();

//        try {
//            Log.i("RX300", Uri.fromFile(pdf).toString());
//        }catch(Exception ex) {
//            Log.w("RX300",ex.getMessage());
//        }

        Uri uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName()+".provider", pdf);
        Log.i("RX300", uri.toString());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/pdf");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        try {
            startActivity(intent);
        }catch(Exception ex) {
            Log.w("RX300", ex.getMessage());
        }

    }
}