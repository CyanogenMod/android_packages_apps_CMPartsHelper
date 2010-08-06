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


public class mvuisdReceiver extends BroadcastReceiver {

    public static final String mvUiSd = "com.cyanogenmod.cmparts.SAVE_CMPARTS_UI";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(mvUiSd)) {
            boolean success = true;
            Bundle extras = intent.getExtras();
            String value = extras.getString("xmldata");
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
              try {
                BufferedWriter out = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory() + "/cmparts_ui.xml"));
                out.write(value);
                out.close();
              } catch (IOException e) {
                e.printStackTrace();
                success = false;
                Toast.makeText(context, R.string.helper_cmui_fail, Toast.LENGTH_SHORT).show();
              }
              if (success)
                Toast.makeText(context, R.string.helper_cmui_success, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, R.string.xml_sdcard_unmounted, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
