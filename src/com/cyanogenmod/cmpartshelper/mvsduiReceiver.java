package com.cyanogenmod.cmpartshelper;

import android.os.Bundle;
import android.os.Environment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.DataInputStream;

import com.cyanogenmod.cmpartshelper.R;


public class mvsduiReceiver extends BroadcastReceiver {

    public static final String mvSdUi = "com.cyanogenmod.cmparts.RESTORE_CMPARTS_UI";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(mvSdUi)) {
            boolean success = true;
            try {
              FileInputStream infile = new FileInputStream(Environment.getExternalStorageDirectory() + "/cmparts_ui.xml");
              DataInputStream in = new DataInputStream(infile);
              byte[] b = new byte[in.available()];
              in.readFully(b);
              in.close();
              String result = new String(b, 0, b.length);
              Intent UiSd = new Intent("com.cyanogenmod.cmpartshelper.RESTORE_CMPARTS_UI");
              UiSd.putExtra("xmldataret", result);
              context.sendBroadcast(UiSd);
            } catch (Exception e) {
              e.printStackTrace();
              success = false;
            }
            if (success) {
                Toast.makeText(context, R.string.helper_complete, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
