package com.dataon.cordova.plugin.autostartmanager;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

/**
 * This class echoes a string called from JavaScript.
 */
public class AutoStartManagerPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("showStartManager")) {
            this.showStartManager();
            return true;
        }
        return false;
    }

    private void showStartManager() {
        Context context = this.cordova.getActivity().getApplicationContext();
        new cordova.plugin.logiclinkplugin.AutoStartPermissionHelper().getAutoStartPermission(context);
    }
}
