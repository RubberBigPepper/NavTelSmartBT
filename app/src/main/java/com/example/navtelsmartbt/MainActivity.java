package com.example.navtelsmartbt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.navtelsmartbt.view.DataAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DataAdapter adapter;
    public static final int PERMISSION_REQUEST_CODE = 12367;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckPermissionsOrRun();
    }

    private void showContent(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        adapter=new DataAdapter();
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

    }

    private void CheckPermissionsOrRun() {
        ArrayList<String> strArNeedPermission = new ArrayList<String>();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED)
            strArNeedPermission.add(Manifest.permission.BLUETOOTH);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED)
            strArNeedPermission.add(Manifest.permission.BLUETOOTH_ADMIN);
        if (strArNeedPermission.size() > 0) {
            //спрашиваем пермишен у пользователя
            requestPermission(strArNeedPermission);
        } else {
            showContent();
        }
    }

    public void requestPermission(ArrayList<String> strArNeedPermission) {
        String[] strArList = new String[strArNeedPermission.size()];
        strArNeedPermission.toArray(strArList);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, strArList, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            boolean bBT = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED;
            boolean bBTAdmin = ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED;
            for (int n = 0; n < permissions.length; n++) {
                if (grantResults[n] == PackageManager.PERMISSION_GRANTED) {
                    if (permissions[n].equals(Manifest.permission.BLUETOOTH))
                        bBT = true;
                    if (permissions[n].equals(Manifest.permission.BLUETOOTH_ADMIN))
                        bBTAdmin = true;
                }
            }
            if (bBT && bBTAdmin) {
                showContent();
            } else {
                AlertDialog.Builder cBuilder = new AlertDialog.Builder(this);
                cBuilder.setTitle(R.string.app_name);
                cBuilder.setMessage("Не все нужные разрешения даны программе. Программа завершается");
                cBuilder.setCancelable(false);
                cBuilder.create().show();
                cBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                    }
                });
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void ConnectBTDlg(){
        AlertDialog.Builder cBuilder = new AlertDialog.Builder(this);
        cBuilder.setTitle(R.string.app_name);
        cBuilder.setMessage("Выберите устройство для работы");

    }
}