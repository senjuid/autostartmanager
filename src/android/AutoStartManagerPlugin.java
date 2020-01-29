package com.dataon.cordova.plugin.autostartmanager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.os.Build;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;


/**
 * This class echoes a string called from JavaScript.
 */
public class AutoStartManagerPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("showStartManager")) {
            this.showStartManager();
            return true;
        } else if (action.equals("checkAutomaticDateTimeZone")) {
            JSONArray json = new JSONArray();

            try {
                boolean result = this.isAutomaticChecked();

                Context context = this.cordova.getActivity().getApplicationContext();
                final CordovaInterface cordova = this.cordova;

                if(!result) {
                    AlertDialog alertDialog = new AlertDialog.Builder(cordova.getActivity()).create();
                    alertDialog.setTitle("Enable Automatic Date Time");
                    alertDialog.setCancelable(false);
                    alertDialog.setMessage("Your Automatic Date Time or Time Zone Settings is set to 'Off'.\nPlease Enable to use this feature");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Show Settings",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i = new Intent(android.provider.Settings.ACTION_DATE_SETTINGS);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(i);
                                    dialog.dismiss();

                                    try {
                                        json.put(0, false);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    callbackContext.success(json);
                                }
                            });
                    alertDialog.show();
                } else {
                    json.put(0, true);
                    callbackContext.success(json);
                }
            } catch (Settings.SettingNotFoundException e) {
                json.put(0, "SettingNotFoundException");
                callbackContext.success(json);
            }

            return true;
        }
        return false;
    }

    private void showStartManager() {
        Context context = this.cordova.getActivity().getApplicationContext();
        new AutoStartPermissionHelper().getAutoStartPermission(context);
    }

    private boolean isAutomaticChecked() throws Settings.SettingNotFoundException {

        Integer dateTime, timezone;

        Activity activity = this.cordova.getActivity();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            dateTime = Settings.Global.getInt(activity.getContentResolver(), Settings.Global.AUTO_TIME, 0);
            timezone = Settings.Global.getInt(activity.getContentResolver(), Settings.Global.AUTO_TIME_ZONE, 0);

        } else {
            dateTime = android.provider.Settings.System.getInt(
                    activity.getContentResolver(),
                    android.provider.Settings.System.AUTO_TIME_ZONE,
                    0
            );

            timezone = android.provider.Settings.System.getInt(
                    activity.getContentResolver(),
                    android.provider.Settings.System.AUTO_TIME,
                    0
            );
        }

        if (dateTime == 0 || timezone == 0) {
            return false;
        } else {
            return true;
        }
    }

}
