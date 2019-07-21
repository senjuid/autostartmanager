package com.dataon.cordova.plugin.autostartmanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.List;

public class AutoStartPermissionHelper {

    /***
     * Xiaomi
     */
    public static final String BRAND_XIAOMI = "xiaomi";
    private String PACKAGE_XIAOMI_MAIN = "com.miui.securitycenter";
    private String PACKAGE_XIAOMI_COMPONENT = "com.miui.permcenter.autostart.AutoStartManagementActivity";

    /***
     * Letv
     */
    public static final String BRAND_LETV = "letv";
    private String PACKAGE_LETV_MAIN = "com.letv.android.letvsafe";
    private String PACKAGE_LETV_COMPONENT = "com.letv.android.letvsafe.AutobootManageActivity";

    /***
     * ASUS ROG
     */
    public static final String BRAND_ASUS = "asus";
    public static final String PACKAGE_ASUS_MAIN = "com.asus.mobilemanager";
    public static final String PACKAGE_ASUS_COMPONENT = "com.asus.mobilemanager.MainActivity";

    /***
     * Honor
     */
    public static final String BRAND_HONOR = "honor";
    private String PACKAGE_HONOR_MAIN = "com.huawei.systemmanager";
    private String PACKAGE_HONOR_COMPONENT = "com.huawei.systemmanager.optimize.process.ProtectActivity";

    /**
     * Oppo
     */
    public static final String BRAND_OPPO = "oppo";
    private String PACKAGE_OPPO_MAIN = "com.coloros.safecenter";
    private String PACKAGE_OPPO_FALLBACK = "com.oppo.safe";
    private String PACKAGE_OPPO_COMPONENT = "com.coloros.safecenter.permission.startup.StartupAppListActivity";
    private String PACKAGE_OPPO_COMPONENT_FALLBACK = "com.oppo.safe.permission.startup.StartupAppListActivity";
    private String PACKAGE_OPPO_COMPONENT_FALLBACK_A = "com.coloros.safecenter.startupapp.StartupAppListActivity";

    /**
     * Vivo
     */

    public static final String BRAND_VIVO = "vivo";
    private String PACKAGE_VIVO_MAIN = "com.iqoo.secure";
    private String PACKAGE_VIVO_FALLBACK = "com.vivo.permissionmanager";
    private String PACKAGE_VIVO_COMPONENT = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity";
    private String PACKAGE_VIVO_COMPONENT_FALLBACK = "com.vivo.permissionmanager.activity.BgStartUpManagerActivity";
    private String PACKAGE_VIVO_COMPONENT_FALLBACK_A = "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager";

    /**
     * Nokia
     */

    public static final String BRAND_NOKIA = "nokia";
    private String PACKAGE_NOKIA_MAIN = "com.evenwell.powersaving.g3";
    private String PACKAGE_NOKIA_COMPONENT = "com.evenwell.powersaving.g3.exception.PowerSaverExceptionActivity";


    public final void getAutoStartPermission(Context context) {

        String build_info = Build.BRAND.toLowerCase();

        System.out.println(build_info);

        switch (build_info) {
            case BRAND_ASUS:
                autoStartAsus(context);
                break;
            case BRAND_XIAOMI:
                autoStartXiaomi(context);
                break;
            case BRAND_LETV:
                autoStartLetv(context);
                break;
            case BRAND_HONOR:
                autoStartHonor(context);
                break;
            case BRAND_OPPO:
                autoStartOppo(context);
                break;
            case BRAND_VIVO:
                autoStartVivo(context);
                break;
            case BRAND_NOKIA:
                autoStartNokia(context);
                break;
        }
    }

    private Boolean isPackageExists(Context context, String targetPackage) {
        List<ApplicationInfo> packages;
        PackageManager pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);
        System.out.println(targetPackage);
        for(Integer i=0; i<packages.size(); i++) {
            System.out.println(packages.get(i).packageName);
            if(packages.get(i).packageName.equals(targetPackage)) {
                return true;
            }
        }
        return false;
    }

    private final void startIntent(Context context, String packageName, String componentName) throws Exception {
        System.out.println(packageName);
        System.out.println(componentName);
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(packageName, componentName));
            context.startActivity(intent);
        } catch (Exception exc) {
            System.out.println(exc);
            exc.printStackTrace();
        }
    }

    private final void autoStartXiaomi(Context context) {
        if (this.isPackageExists(context, this.PACKAGE_XIAOMI_MAIN)) {
            try {
                this.startIntent(context, this.PACKAGE_XIAOMI_MAIN, this.PACKAGE_XIAOMI_COMPONENT);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    private final void autoStartAsus(Context context) {
        System.out.println("Masuk Asus");
        System.out.println(this.isPackageExists(context, PACKAGE_ASUS_MAIN));
        if (this.isPackageExists(context, PACKAGE_ASUS_MAIN)) {
            try {
                this.startIntent(context, PACKAGE_ASUS_MAIN, PACKAGE_ASUS_COMPONENT);
            } catch (Exception var3) {
                System.out.println(var3);
                var3.printStackTrace();
            }
        }

    }

    private final void autoStartLetv(Context context) {
        if (this.isPackageExists(context, this.PACKAGE_LETV_MAIN)) {
            try {
                this.startIntent(context, this.PACKAGE_LETV_MAIN, this.PACKAGE_LETV_COMPONENT);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    private final void autoStartHonor(Context context) {
        if (this.isPackageExists(context, this.PACKAGE_HONOR_MAIN)) {
            try {
                this.startIntent(context, this.PACKAGE_HONOR_MAIN, this.PACKAGE_HONOR_COMPONENT);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    private final void autoStartOppo(Context context) {
        if (this.isPackageExists(context, this.PACKAGE_OPPO_MAIN) || this.isPackageExists(context, this.PACKAGE_OPPO_FALLBACK)) {
            try {
                this.startIntent(context, this.PACKAGE_OPPO_MAIN, this.PACKAGE_OPPO_COMPONENT);
            } catch (Exception var7) {
                var7.printStackTrace();

                try {
                    this.startIntent(context, this.PACKAGE_OPPO_FALLBACK, this.PACKAGE_OPPO_COMPONENT_FALLBACK);
                } catch (Exception var6) {
                    var6.printStackTrace();

                    try {
                        this.startIntent(context, this.PACKAGE_OPPO_MAIN, this.PACKAGE_OPPO_COMPONENT_FALLBACK_A);
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }
                }
            }
        }

    }

    private final void autoStartVivo(Context context) {
        if (this.isPackageExists(context, this.PACKAGE_VIVO_MAIN) || this.isPackageExists(context, this.PACKAGE_VIVO_FALLBACK)) {
            try {
                this.startIntent(context, this.PACKAGE_VIVO_MAIN, this.PACKAGE_VIVO_COMPONENT);
            } catch (Exception var7) {
                var7.printStackTrace();

                try {
                    this.startIntent(context, this.PACKAGE_VIVO_FALLBACK, this.PACKAGE_VIVO_COMPONENT_FALLBACK);
                } catch (Exception var6) {
                    var6.printStackTrace();

                    try {
                        this.startIntent(context, this.PACKAGE_VIVO_MAIN, this.PACKAGE_VIVO_COMPONENT_FALLBACK_A);
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }
                }
            }
        }

    }

    private final void autoStartNokia(Context context) {
        if (this.isPackageExists(context, this.PACKAGE_NOKIA_MAIN)) {
            try {
                this.startIntent(context, this.PACKAGE_NOKIA_MAIN, this.PACKAGE_NOKIA_COMPONENT);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }


}
