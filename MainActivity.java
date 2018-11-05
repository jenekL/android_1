package com.company.android_1;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "My Logs";
    Menu menu;
    int numOfMenus = 0;
    TextView tvOut;
    EditText editText;

    public void onClickSetFirstLayout(View v){
        setContentView(R.layout.activity_main);
        setElements();
    }
    public void onClickSetSecondLayout(View v){
        setContentView(R.layout.screen2);
    }

    public void onClickSetText(View view){
        tvOut.setText(editText.getText());
    }

    public void onClickAddMenu (View view){

       // PopupMenu popup = new PopupMenu(this, view);
        //MenuInflater inflater = popup.getMenuInflater();
        //popup.getMenu().add("1");
        //inflater.inflate(R.menu.menu_main, popup.getMenu());
        //popup.show();

        if(numOfMenus < 3) {
            numOfMenus++;
            //MenuItem add = menu.add("Кнопка " + numOfMenus);
            MenuItem add = menu.add(numOfMenus, numOfMenus, numOfMenus, "Кнопка " + numOfMenus);

            add.setOnMenuItemClickListener(new ButtonListener(add, this));
        }
    }

    public void onClickDelMenu(View v){
        menu.removeItem(numOfMenus);
        numOfMenus--;

    }


    public void onClickPaint() {
        Draw2D draw2D = new Draw2D(this);
        setContentView(draw2D);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_item1:
                Toast.makeText(MainActivity.this, getString(R.string.action_item1), Toast.LENGTH_LONG).show();
                break;
            case R.id.action_item2:
                Log.d(TAG, "Пункт 2");
                break;
            case R.id.action_item3:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Нажат пункт")
                        .setMessage("Номер 3")
                        .setCancelable(false)
                        .setNegativeButton("Ок, закройся",
                                (dialog, id1) -> dialog.cancel());
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.action_item4:
                setContentView(R.layout.activity_main);
                setElements();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setElements(){
        tvOut = findViewById(R.id.textView2);
        editText = findViewById(R.id.textBox1);
        findViewById(R.id.button5).setOnClickListener(event -> onClickPaint());

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setElements();

    }
}