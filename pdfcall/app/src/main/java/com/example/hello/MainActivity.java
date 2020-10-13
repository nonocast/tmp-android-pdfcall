package com.example.hello;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.FileProvider;

import com.example.hello.model.Agenda;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanweiyan
 */
public class MainActivity extends Activity {
    public static String PDF = Environment.getExternalStorageDirectory().getPath() + File.separator + "PDF";
    public Button btnContent, BtnOfd;
    public TableLayout agendaView;
    private List<Agenda> agendaList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContent = findViewById(R.id.button);
        BtnOfd = findViewById(R.id.button2);
        agendaView = findViewById(R.id.agendaView);
        hideBottomUIMenu();
//        init();
        initView();
    }

    private void initView() {
        findViewById(R.id.file1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open("1.ofd");
            }
        });
        findViewById(R.id.file2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open("2.ofd");
            }
        });
        findViewById(R.id.file3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open("3.ofd");
            }
        });
        findViewById(R.id.file4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open("4.ofd");
            }
        });
        findViewById(R.id.file5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open("5.ofd");
            }
        });
        findViewById(R.id.file6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open("6.ofd");
            }
        });
    }

    private void open(String name) {
        Log.i("RX300", String.format(">>> %s", name));
    }

    @Override
    protected void onStart() {
        super.onStart();

        int granted = this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        String ret = String.format("granted again: %b", granted == PackageManager.PERMISSION_GRANTED);
        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//        btnContent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onContentClick();
//            }
//        });
//        BtnOfd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onOfdContentClick();
//            }
//        });
    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int granted = this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    public void onContentClick() {
        try {
            File pdf = new File(PDF + "/" + "aaa.pdf");
            if (!pdf.isFile()) {
                return;
            }
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", pdf);
            intent.setDataAndType(uri, "application/pdf");
            startActivity(intent);
        } catch (Exception ex) {
            Log.w("RX300", ex.getMessage());
        }
    }

    public void onOfdContentClick() {
        try {
            File ofd = new File(PDF + "/" + "ccc.ofd");
            Uri uri = Uri.fromFile(ofd);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri, "application/ofd");
            startActivity(intent);
        } catch (Exception ex) {
            Log.w("RX300", ex.getMessage());
        }
    }
}