package com.ibc.android.demo.appslist.activity;
 
import java.util.ArrayList;
import java.util.List;
 
import com.ibc.android.demo.appslist.adapter.ApkAdapter;
import com.ibc.android.demo.appslist.app.AppData;
 
import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
 
public class ApkListActivity1 extends Activity implements OnItemClickListener
{
	
	String[] perms={
			"android.permission.SEND_SMS",
			"android.permission.SEND_RESPOND_VIA_MESSAGE",
			"android.permission.RECIEVE_SMS",
			"android.permission.RECIEVE_MMS",
			"android.permission.RECIEVE_EMERGENCY_BROADCAST",
			"android.permission.READ_CELL_BROADCASTS",
			"android.permission.READ_SMS",
			"android.permission.WRITE_SMS",
			"android.permission.RECIEVE_WAP_PUSH",
			"android.permission.READ_CONTACTS",
			"android.permission.WRITE_CONTACTS",
			"android.permission.BIND_DIRECTORY_SEARCH",
			"android.permission.READ_CALL_LOG",
			"android.permission.WRITE_CALL_LOG",
			"android.permission.READ_SOCIAL_STREAM",
			"android.permission.WRITE_SOCIAL_STREAM",
			"android.permission.READ_PROFILE",
			"android.permission.WRITE_PROFILE",
			"android.permission.READ_CALENDER",
			"android.permission.WRITE_CALENDER",
			"android.permission.READ_USER_DICTIONARY",
			"android.permission.WRITE_USER_DICTIONARY",
			"android.permission.READ_HISTORY_BOOKMARKS",
			"android.permission.WRITE_HISTORY_BOOKMARKS",
			"android.permission.SET_ALARM",
			"android.permission.ADD_VOICEMAIL",
			"android.permission.ACCESS_FINE_LOCATION",
			"android.permission.ACCESS_COARSE_LOCATION",
			"android.permission.ACCESS_MOCK_LOCATION",
			"android.permission.ACCESS_LOCATION_EXTRA_COMMANDS",
			"android.permission.INSTALL_LOCATION_PROVIDER",
			"android.permission.LOCATION_HARDWARE",
			"android.permission.INTERNET",
			"android.permission.ACCESS_NETWORK_STATE",
			"android.permission.ACCESS_WIFI_STATE",
			"android.permission.CHANGE_WIFI_STATE",
			"android.permission.ACCESS_WIMAX_STATE",
			"android.permission.CHANGE_WIMAX_STATE",
			"android.permission.BLUETOOTH",
			"android.permission.BLUETOOTH_ADMIN",
			"android.permission.BLUETOOTH_PRIVILEGED",
			"android.permission.BLUETOOTH_STACK",
			"android.permission.NFC",
			"android.permission.CONNECTIVITY_INTERNAL",
			"android.permission.RECIEVE_DATA_ACTIVITY_CHANGE",
			"android.permission.LOOP_RADIO",
			"android.permission.GET_ACCOUNTS",
			"android.permission.AUTHENTICATE_ACCOUNTS",
			"android.permission.USE_CREDENTIALS",
			"android.permission.MANAGE_ACCOUNTS",
			"android.permission.ACCOUNT_MANAGER",
			"android.permission.CHANGE_WIFI_MULTICAST_STATE",
			"android.permission.VIBRATE",
			"android.permission.FLASHLIGHT",
			"android.permission.WAKE_LOCK",
			"android.permission.TRANSMIT_IR",
			"android.permission.MODIFY_AUDIO_SETTINGS",
			"android.permission.MANAGE_USB",
			"android.permission.ACCESS_MTP",
			"android.permission.HARDWARE_TEST",
			"android.permission.NET_ADMIN",
			"android.permission.REMOTE_AUDIO_PLAYBACK",
			"android.permission.RECORD_AUDIO",
			"android.permission.CAMERA",
			"android.permission.CAMERA_DISABLE_TRANSMIT_LED",
			"android.permission.PROCESS_OUTGOING_CALLS",
			"android.permission.MODIFY_PHONE_STATE",
			"android.permission.READ_PHONE_STATE",
			"android.permission.READ_PRECISE_PHONE_STATE",
			"android.permission.READ_PRIVILEGED_PHONE_STATE",
			"android.permission.CALL_PHONE",
			"android.permission.USE_SIP",
			"android.permission.BIND_CALL_SERVICE",
			"android.permission.READ_EXTERNAL_STORAGE",
			"android.permission.WRITE_EXTERNAL_STORAGE",
			"android.permission.WRITE_MEDIA_STORAGE",
			"android.permission.MANAGE_ACCOUNTS",
			"android.permission.DISABLE_KEYGUARD",
			"android.permission.GET_TASKS",
			"android.permission.INTERACT_ACROSS_USERS",
			"android.permission.INTERACT_ACROSS_USERS_FULL",
			"android.permission.MANAGE_USERS",
			"android.permission.GET_DETAILED_TASKS",
			"android.permission.REORDER_TASKS",
			"android.permission.REMOVE_TASKS",
			"android.permission.MANAGE_ACTIVITY_STACKS",
			"android.permission.START_ANY_ACTIVITY",
			"android.permission.RESTART_PACKAGES",
			"android.permission.KILL_BACKGROUND_PROCESSES",
			"android.permission.SYSTEM_ALERT_WINDOW",
			"android.permission.SET_WALLPAPER",
			"android.permission.SET_WALLPAPER_HINTS",
			"android.permission.SET_TIME",
			"android.permission.SET_TIME_ZONE",
			"android.permission.EXPAND_STATUS_BAR",
			"android.permission.INSTALL_SHORTCUT",
			"android.permission.UNINSTALL_SHORTCUT",
			"android.permission.READ_SYNC_SETTINGS",
			"android.permission.WRITE_SYNC_SETTINGS",
			"android.permission.READ_SYNC_STATS",
			"android.permission.SET_SCREEN_COMPATIBILITY",
			"android.permission.ACCESS_ALL_EXTERNAL_STORAGE",
			"android.permission.CHANGE_CONFIGURATION",
			"android.permission.WRITE_SETTINGS",
			"android.permission.WRITE_GSERVICES",
			"android.permission.FORCE_STOP_PACKAGES",
			"android.permission.RETRIEVE_WINDOW_CONTENT",
			"android.permission.SET_ANIMATION_SCALE",
			"android.permission.PERSISTENT_ACTIVITY",
			"android.permission.GET_PACKAGE_SIZE",
			"android.permission.SET_PREFERRED_APPLICATIONS",
			"android.permission.RECEIVE_BOOT_COMPLETED",
			"android.permission.BROADCAST_STICKY",
			"android.permission.MOUNT_UNMOUNT_FILESYSTEMS",
			"android.permission.MOUNT_FORMAT_FILESYSTEMS",
			"android.permission.ASEC_ACCESS",
			"android.permission.ASEC_CREATE",
			"android.permission.ASEC_DESTROY",
			"android.permission.ASEC_MOUNT_UNMOUNT",
			"android.permission.ASEC_RENAME",
			"android.permission.WRITE_APN_SETTINGS",
			"android.permission.SUBSCRIBED_FEEDS_READ",
			"android.permission.CHANGE_NETWORK_STATE",
			"android.permission.CLEAR_APP_CACHE",
			"android.permission.ALLOW_ANY_CODEC_FOR_PLAYBACK",
			"android.permission.MANAGE_CA_CERTIFICATES",
			"android.permission.WRITE_SECURE_SETTINGS",
			"android.permission.DUMP",
			"android.permission.READ_LOGS",
			"android.permission.SET_DEBUG_APP",
			"android.permission.SET_PROCESS_LIMIT",
			"android.permission.SET_ALWAYS_FINISH",
			"android.permission.SIGNAL_PERSISTENT_PROCESSES",
			"android.permission.DIAGNOSTIC",
			"android.permission.STATUS_BAR",
			"android.permission.STATUS_BAR_SERVICE",
			"android.permission.FORCE_BACK",
			"android.permission.UPDATE_DEVICE_STATS",
			"android.permission.GET_APP_OPS_STATS",
			"android.permission.UPDATE_APP_OPS_STATS",
			"android.permission.INTERNAL_SYSTEM_WINDOW",
			"android.permission.MANAGE_APP_TOKENS",
			"android.permission.FREEZE_SCREEN",
			"android.permission.INJECT_EVENTS",
			"android.permission.FILTER_EVENTS",
			"android.permission.RETRIEVE_WINDOW_INFO",
			"android.permission.TEMPORARY_ENABLE_ACCESSIBILITY",
			"android.permission.MAGNIFY_DISPLAY",
			"android.permission.SET_ACTIVITY_WATCHER",
			"android.permission.SHUTDOWN",
			"android.permission.STOP_APP_SWITCHES",
			"android.permission.GET_TOP_ACTIVITY_INFO",
			"android.permission.READ_INPUT_STATE",
			"android.permission.BIND_INPUT_METHOD",
			"android.permission.BIND_ACCESSIBILITY_SERVICE",
			"android.permission.BIND_PRINT_SERVICE",
			"android.permission.BIND_NFC_SERVICE",
			"android.permission.BIND_PRINT_SPOOLER_SERVICE",
			"android.permission.BIND_TEXT_SERVICE",
			"android.permission.BIND_VPN_SERVICE",
			"android.permission.BIND_WALLPAPER",
			"android.permission.BIND_REMOTE_DISPLAY",
			"android.permission.BIND_DEVICE_ADMIN",
			"android.permission.MANAGE_DEVICE_ADMINS",
			"android.permission.SET_ORIENTATION",
			"android.permission.SET_POINTER_SPEED",
			"android.permission.SET_KEYBOARD_LAYOUT",
			"android.permission.INSTALL_PACKAGES",
			"android.permission.CLEAR_APP_USER_DATA",
			"android.permission.DELETE_CACHE_FILES",
			"android.permission.DELETE_PACKAGES",
			"android.permission.MOVE_PACKAGE",
			"android.permission.CHANGE_COMPONENT_ENABLED_STATE",
			"android.permission.GRANT_REVOKE_PERMISSIONS",
			"android.permission.ACCESS_SURFACE_FLINGER",
			"android.permission.READ_FRAME_BUFFER",
			"android.permission.CONFIGURE_WIFI_DISPLAY",
			"android.permission.CONTROL_WIFI_DISPLAY",
			"android.permission.CAPTURE_AUDIO_OUTPUT",
			"android.permission.CAPTURE_AUDIO_HOTWORD",
			"android.permission.CAPTURE_VIDEO_OUTPUT",
			"android.permission.CAPTURE_SECURE_VIDEO_OUTPUT",
			"android.permission.MEDIA_CONTENT_CONTROL",
			"android.permission.BRICK",
			"android.permission.REBOOT",
			"android.permission.DEVICE_POWER",
			"android.permission.NET_TUNNELING",
			"android.permission.FACTORY_TEST",
			"android.permission.BROADCAST_PACKAGE_REMOVED",
			"android.permission.BROADCAST_SMS",
			"android.permission.BROADCAST_WAP_PUSH",
			"android.permission.MASTER_CLEAR",
			"android.permission.CALL_PRIVILEGED",
			"android.permission.PERFORM_CDMA_PROVISIONING",
			"android.permission.CONTROL_LOCATION_UPDATES",
			"android.permission.ACCESS_CHECKING_PROPERTIES",
			"android.permission.PACKAGE_USAGE_STATS",
			"android.permission.BATTERY_STATS",
			"android.permission.BACKUP",
			"android.permission.CONFIRM_FULL_BACKUP",
			"android.permission.BIND_REMOTEVIEWS",
			"android.permission.BIND_APPWIDGET",
			"android.permission.BIND_KEYGUARD_APPWIDGET",
			"android.permission.MODIFY_APPWIDGET_BIND_PERMISSIONS",
			"android.permission.CHANGE_BACKGROUND_DATA_SETTINGS",
			"android.permission.GLOBAL_SEARCH",
			"android.permission.GLOBAL_SEARCH_CONTROL",
			"android.permission.SET_WALPAPER_COMPONENT",
			"android.permission.READ_DREAM_STATE",
			"android.permission.WRITE_DREAM_STATE",
			"android.permission.ACCESS_CACHE_FILESYSTEM",
			"android.permission.COPY_PROCTECTED_DATA",
			"android.permission.CRYPT_KEEPER",
			"android.permission.READ_NETWORK_USAGE_HISTORY",
			"android.permission.MANAGE_NETWORK_POLICY",
			"android.permission.MODIFY_NETWORK_ACCOUNTING",
			"android.permission.MARK_NETWORK_SOCKET",
			"android.permission.PACKAGE_VERIFICATION_AGENT",
			"android.permission.BIND_PACKAGE_VERIFIER",
			"android.permission.SERIAL_PORT",
			"android.permission.ACCESS_CONTENT_PROVIDERS_EXTERNALLY",
			"android.permission.UPDATE_LOCK",
			"android.permission.ACCESS_NOTIFICATIONS",
			"android.permission.ACCESS_KEYGUARD_SECURE_STORAGE",
			"android.permission.CONTROL_KEYGUARD",
			"android.permission.BIND_NOTIFICATION_LISTENER_SERVICE",
			"android.permission.INVOKE_CARRIER_SETUP",
			"android.permission.ACCESS_NETWORK_CONDITIONS"
		};
	int[] priority={
	    		36,
	    		36,
	    		36,
	    		36,
	    		36,
	    		36,
	    		36,
	    		36,
	    		36,
	    		32,
	    		32,
	    		32,
	    		32,
	    		32,
	    		32,
	    		32,
	    		31,
	    		31,
	    		29,
	    		29,
	    		17,
	    		16,
	    		30,
	    		30,
	    		21,
	    		28,
	    		33,
	    		33,
	    		33,
	    		33,
	    		33,
	    		33,
	    		27,
	    		27,
	    		27,
	    		27,
	    		27,
	    		27,
	    		26,
	    		26,
	    		26,
	    		26,
	    		26,
	    		26,
	    		26,
	    		26,
	    		20,
	    		20,
	    		20,
	    		20,
	    		20,
	    		18,
	    		18,
	    		18,
	    		18,
	    		18,
	    		13,
	    		26,
	    		26,
	    		26,
	    		26,
	    		26,
	    		34,
	    		35,
	    		35,
	    		37,
	    		37,
	    		37,
	    		37,
	    		37,
	    		37,
	    		37,
	    		37,
	    		24,
	    		24,
	    		24,
	    		24,
	    		23,
	    		22,
	    		22,
	    		22,
	    		22,
	    		22,
	    		22,
	    		22,
	    		22,
	    		22,
	    		22,
	    		22,
	    		19,
	    		15,
	    		15,
	    		14,
	    		14,
	    		11,
	    		11,
	    		11,
	    		12,
	    		12,
	    		12,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		10,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		31,
	    		36,         
	    		36,
	    		36,
	    		36,
	    		36,
	    		30,
	    		30,
	    		30,
	    		36,
	    		30,
	    		30,
	    		30,
	    		30,
	    		30,
	    		32,
	    		35,
	    		36,
	    		28,
	    		28,
	    		28,
	    		28,
	    		28,
	    		30,
	    		31,
	    		26,
	    		26,
	    		26,
	    		32,
	    		35,
	    		31,
	    		34,
	    		34,
	    		34,
	    		31,
	    		28,
	    		28,
	    		31,
	    		34,
	    		24,
	    		36,
	    		35,
	    		25,
	    		26,
	    		24,
	    		24				
			};


    PackageManager packageManager;
    ListView apkList;
 
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
 
        packageManager = getPackageManager();
        List<PackageInfo> packageList = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);
 
        List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();
               
        /*To filter out System apps*/
        for(PackageInfo pi : packageList)
        {
            boolean b = isSystemPackage(pi);
            if(!b)
            {
                packageList1.add(pi);
            }
        }
        
        List<PackageInfo> packageList_vd = new ArrayList<PackageInfo>();
        List<PackageInfo> packageList_d = new ArrayList<PackageInfo>();
        List<PackageInfo> packageList_m = new ArrayList<PackageInfo>();
        List<PackageInfo> packageList_s = new ArrayList<PackageInfo>();
        List<PackageInfo> packageList2 = new ArrayList<PackageInfo>();
        
        for(PackageInfo pi : packageList1)
    	{
		        //getting maximum critical value
		        String x="";
		        String per[]=new String[100];
		        for (int i=0;i<100;i++)
		        	per[i]="";
		        int j=0;
		        if (pi.requestedPermissions != null)
		        {
		        	x = getPermissions(pi.requestedPermissions);
		        	int length=x.length();
		        	for (int i=0;i<length;i++)
		        	{
		        		if (x.charAt(i)==',')
		        		{
		        			j++;
		        			i++;
		        		}
		        		else
		        			per[j]=per[j]+x.charAt(i);
		        	}
		        }
		        per[j]="0";
		        
		        j=0;
		        int t=0;
		        int max=0;
		        
		        while (per[j]!="0")
		        {
		        	for (int i=0;i<perms.length;i++)
		        	{
		        		if (per[j].equals(perms[i]))
		        		{
		        			t=t+priority[i];
		        			if (priority[i]>max)
		        				max=priority[i];
		        		}
		        	}
		        	j++;
		        }
		        
		        if (max>35)
		        	packageList_vd.add(pi);
		        else if (max>=30)
		        	packageList_d.add(pi);
		        else if (max>=15)
		        	packageList_m.add(pi);
		        else
		        	packageList_s.add(pi);
		   }
        
        for(PackageInfo pi : packageList_vd)
        	packageList2.add(pi);
        for(PackageInfo pi : packageList_d)
        	packageList2.add(pi);
        for(PackageInfo pi : packageList_m)
        	packageList2.add(pi);
        for(PackageInfo pi : packageList_s)
        	packageList2.add(pi);
		   
        apkList = (ListView) findViewById(R.id.applist);
        apkList.setAdapter(new ApkAdapter(this, packageList2, packageManager));
        apkList.setOnItemClickListener(this);
    }
    
    private String getPermissions(String[] requestedPermissions) 
    {
        String permission = "";
        for (int i = 0; i < requestedPermissions.length; i++) 
        {
            permission = permission + requestedPermissions[i] + ",\n";
        }
        return permission;
    }
 
     /**
     * Return whether the given PackgeInfo represents a system package or not.
     * User-installed packages (Market or otherwise) should not be denoted as
     * system packages.
     *
     * @param pkgInfo
     * @return boolean
     */
    
    private boolean isSystemPackage(PackageInfo pkgInfo)
    {
        return((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true:false;
    }
 
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long row) 
    {
        PackageInfo packageInfo = (PackageInfo) parent.getItemAtPosition(position);
        AppData appData = (AppData) getApplicationContext();
        appData.setPackageInfo(packageInfo);
        Intent appInfo = new Intent(getApplicationContext(), ApkInfo.class);
        startActivity(appInfo);
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.apk_list_activity21, menu);
        
     // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search1).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Take appropriate action for each action item click
        switch (item.getItemId()) 
        {
        case R.id.action_search1:
            // search action
            return true;
        case R.id.action_sort_state1:
            return true;
        case R.id.action_sort_alpha1:
        	Intent sort_alpha = new Intent(getApplicationContext(), ApkListActivity.class);
            startActivity(sort_alpha);
            return true;
        case R.id.action_about:
        	Intent abt = new Intent(getApplicationContext(), ApkAbout.class);
        	startActivity(abt);
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
        