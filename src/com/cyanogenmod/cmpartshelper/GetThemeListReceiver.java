package com.cyanogenmod.cmpartshelper;

import android.os.Bundle;
import android.os.Environment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;

import com.cyanogenmod.cmpartshelper.R;


public class GetThemeListReceiver extends BroadcastReceiver {

    public static final String getList = "com.cyanogenmod.cmparts.GET_THEME_LIST";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(getList)) {
            File themeDir = new File(Environment.getExternalStorageDirectory() + "/CMTheme");
            if (themeDir.exists()) {
                try {
                    File files[] = themeDir.listFiles();
                    if (files != null) {
                        int numfiles = files.length;
                        String filelist[] = new String[numfiles];
                        for (int i=0; i < numfiles; i++) {
                            filelist[i] = files[i].getName();
                            }
                        Intent returnFiles = new Intent("com.cyanogenmod.cmpartshelper.GET_THEME_LIST");
                        returnFiles.putExtra("filelist", filelist);
                        returnFiles.putExtra("gotfile", 1);
                        context.sendBroadcast(returnFiles);
                    }
                } catch (Exception e) { }
            } else {
                Intent returnFiles = new Intent("com.cyanogenmod.cmpartshelper.GET_THEME_LIST");
                returnFiles.putExtra("gotfile", -1);
                returnFiles.putExtra("filelist", new String[] {});
                context.sendBroadcast(returnFiles);
            }
        }
    }
}