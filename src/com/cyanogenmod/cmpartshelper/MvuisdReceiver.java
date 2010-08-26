package com.cyanogenmod.cmpartshelper;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

import com.cyanogenmod.cmpartshelper.R;


public class MvuisdReceiver extends BroadcastReceiver {

    public static final String mvUiSd = "com.cyanogenmod.cmparts.SAVE_CMPARTS_UI";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(mvUiSd)) {
            boolean success = true;
            Bundle extras = intent.getExtras();
            String value = extras.getString("xmldata");
            String filename = extras.getString("filename");
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                try {
                    // check for existence of CMTheme folder and create if needed
                    File cmtheme = new File(Environment.getExternalStorageDirectory()+"/CMTheme");
                    if (!cmtheme.exists())
                        cmtheme.mkdir();
                    BufferedWriter out = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory() + "/CMTheme/" + filename));
                    out.write(value);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    success = false;
                    String fail = context.getString(R.string.helper_cmui_fail);
                    Toast.makeText(context, fail + " " + filename, Toast.LENGTH_SHORT).show();
                }
                if (success){
                    String suc = context.getString(R.string.helper_cmui_success);
                    Toast.makeText(context, suc + " " + filename, Toast.LENGTH_SHORT).show();
                }
            } else {
                    Toast.makeText(context, R.string.xml_sdcard_unmounted, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
