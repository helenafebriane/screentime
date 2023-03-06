package com.example.screentime.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.screentime.DialogController.PromptAlertUserDialogs;
import com.example.screentime.ToastController.ToastDisplay;
import com.example.screentime.ToastController.ToastMessages;
import com.example.screentime.database.dbAddUsageLimit.DBAddUsageLimitHandler;
import com.example.screentime.database.dbViewUsageStats.DBViewUsageStatsHandler;
import com.example.screentime.helper.AndroidSystemWrappers.AndroidPackageManagerWrappers;
import com.example.screentime.helper.AppAccessAllowed;
import com.example.screentime.helper.CheckUsageStatsPermission;
import com.example.screentime.helper.DBWrappers;
import com.example.screentime.helper.SetNotificationsAlarm;
import com.example.screentime.model.AppAddUsageLimit;
import com.example.screentime.model.AppViewUsageStats;
import com.example.screentime.adapter.AppAddUsageLimitAdapter;
import com.example.screentime.service.AppLaunchDetectService;
import com.example.screentime.helper.NavDrawerItemsIntentResolver;
import com.example.screentime.service.UsageStatsUpdateService;
import com.example.screentime.R;
import com.example.screentime.sharedPreferences.MySharedPreferences;
import com.example.screentime.sharedPreferences.SPNames;

import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DBAddUsageLimitHandler mDbAddUsageLimitHandler;
    private Intent appLaunchDetectServiceIntent, usageStatsUpdateServiceIntent;

    List<AppAddUsageLimit> restrictedAppsList;
    private ListView allAppsListView;
    private AppAddUsageLimit appAddUsageLimit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---------------VIEWS initialisation START------------------

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        //---------------VIEWS initialisation ENDS------------------

        //---------------MEMBERS initialisation START---------------
        mDbAddUsageLimitHandler = new DBAddUsageLimitHandler(this);
        appLaunchDetectServiceIntent = new Intent(this, AppLaunchDetectService.class);
        usageStatsUpdateServiceIntent = new Intent(this, UsageStatsUpdateService.class);

        allAppsListView = (ListView) findViewById(R.id.allAppsListView);
        allAppsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                appAddUsageLimit = restrictedAppsList.get(position);
                if (!appAddUsageLimit.getAppName().equals(DBWrappers.NO_RESTRICTED_APPS)) {
                    showDialogConfirmAppRestrictionRemoval(MainActivity.this, appAddUsageLimit);
                }
                return true;
            }
        });

        //---------------MEMBERS initialisation ENDS---------------


        // --------------- Some Initial Tasks STARTS ------------
        AndroidPackageManagerWrappers.generateAndStoreAppNamePackageNamePair(this);
        startService(appLaunchDetectServiceIntent);
        startService(usageStatsUpdateServiceIntent);
        refreshRestrictedAppsList();
        SetNotificationsAlarm.displayNotificationsEveryday(MainActivity.this);
        // -------------- Some Initial Tasks ENDS -------------

    }


    @Override
    protected void onResume() {
        super.onResume();

        boolean appLaunchedFirstTime = MySharedPreferences.getBooleanValue(this, SPNames.SP_APP_FIRST_TIME_USED,
                SPNames.SP_KEY_APP_FIRST_LAUNCH, true);

        if (appLaunchedFirstTime) {
            MySharedPreferences.storeBooleanValue(this, SPNames.SP_APP_FIRST_TIME_USED,
                    SPNames.SP_KEY_APP_FIRST_LAUNCH, false);
            PromptAlertUserDialogs.showDialogOpenHelpPage(this);
        }

        refreshRestrictedAppsList();

        // check for usage access permission
        if (!CheckUsageStatsPermission.hasUsageStatsPermission(this)) {
            PromptAlertUserDialogs.showDialogUsageAccessRequired(this);
        }
    }


    @Override
    protected void onDestroy() {
        stopService(appLaunchDetectServiceIntent);
        stopService(usageStatsUpdateServiceIntent);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.limitAppsUsage) {
            NavDrawerItemsIntentResolver.onlimitAppsUsageSelected(this);
        } else if (id == R.id.appsUsageStats) {
            NavDrawerItemsIntentResolver.onappsUsageStatsselected(this);
        } else if (id == R.id.settings) {
            NavDrawerItemsIntentResolver.onSettingsselected(this);
        } else if (id == R.id.share) {
            NavDrawerItemsIntentResolver.onShareSelected(this);
        } else if (id == R.id.help) {
            NavDrawerItemsIntentResolver.onHelpSelected(this);
        } else if (id == R.id.about) {
            NavDrawerItemsIntentResolver.onAboutSelected(this);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void refreshRestrictedAppsList() {
        restrictedAppsList = DBWrappers.getAppsWithAnyLimit(MainActivity.this);
        AppAddUsageLimitAdapter appListAdapter = new AppAddUsageLimitAdapter(MainActivity.this, this.restrictedAppsList);
        allAppsListView.setAdapter(appListAdapter);
    }

    // dialog created here in order to get reference to the restricted apps list
    // in this activity so that it may be updated INSTANTLY when an app restriction is removed from the app
    private void showDialogConfirmAppRestrictionRemoval(final Context context, final AppAddUsageLimit appAddUsageLimit) {

        String title = "Hapus Pembatasan Pemakaian";
        String text = "Apakah anda yakin ingin hapus Pembatasan Pemakaian dari " +
                appAddUsageLimit.getAppName() + " ? ";
        String OKButton = "YA";
        String CancelButton = "BATAL";

        AlertDialog confirmAppRestrictionRemovalDialog = new AlertDialog.Builder(context).create();
        confirmAppRestrictionRemovalDialog.setTitle(title);
        confirmAppRestrictionRemovalDialog.setMessage(text);
        confirmAppRestrictionRemovalDialog.setIcon(appAddUsageLimit.getIcon());
        confirmAppRestrictionRemovalDialog.setCancelable(false);
        confirmAppRestrictionRemovalDialog.setCanceledOnTouchOutside(false);

        confirmAppRestrictionRemovalDialog.setButton(AlertDialog.BUTTON_POSITIVE, OKButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        AppAddUsageLimit restriction = DBAddUsageLimitHandler.getAppUsageRestrictionData(
                                appAddUsageLimit.getPackageName());
                        AppViewUsageStats appViewUsageStats = DBViewUsageStatsHandler
                                .getAppUsageData(appAddUsageLimit.getPackageName());

                        if (AppAccessAllowed.canAppUsageRestrictionBeRemovedNow(getApplicationContext(), appViewUsageStats, restriction)) {
                            removeAppRestriction(appAddUsageLimit.getPackageName());
                            refreshRestrictedAppsList();
                            displayToastRestrictionRemoved();
                        } else {
                            PromptAlertUserDialogs.showDialogAppLimitNotRemoved(context, appAddUsageLimit);
                        }
                    }
                });

        confirmAppRestrictionRemovalDialog.setButton(AlertDialog.BUTTON_NEGATIVE, CancelButton,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        confirmAppRestrictionRemovalDialog.show();
    }

    private void removeAppRestriction(String packageName) {
        DBAddUsageLimitHandler.deleteAppLimit(packageName);
    }

    private void displayToastRestrictionRemoved() {
        String toastMsg = ToastMessages.USAGE_RESTRICTION_REMOVED + appAddUsageLimit.getAppName() + " ";
        ToastDisplay.makeLongDurationToast(getApplicationContext(), toastMsg);
    }

}
