package com.ibc.android.demo.appslist.activity;

import java.util.ArrayList;
import java.util.List;
import com.ibc.android.demo.appslist.adapter.ApkAdapter;
import com.ibc.android.demo.appslist.app.AppData;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
 
public class SearchResultsActivity extends Activity  implements OnItemClickListener
{
 
	PackageManager packageManager1;
    ListView apkList1;
    List<PackageInfo> packageList11;
    List<PackageInfo> packageList22;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        
        // get the action bar
        ActionBar actionBar = getActionBar();
 
        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);
 
        handleIntent(getIntent());
    }
 
    @Override
    protected void onNewIntent(Intent intent) 
    {
    	setIntent(intent);
        handleIntent(intent);
    }
 
    /**
     * Handling intent data
     */
    
    @SuppressLint("DefaultLocale")
	private void handleIntent(Intent intent) 
    {
    	String query="";
    	apkList1 = (ListView) findViewById(R.id.applist_search);;
        packageList11 = new ArrayList<PackageInfo>();
        packageList22 = new ArrayList<PackageInfo>();
        
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) 
        {
            query = intent.getStringExtra(SearchManager.QUERY);
            query=query.toLowerCase();
        }
        
        packageManager1 = getPackageManager();
        List<PackageInfo> packageList0 = packageManager1
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);
        
        /*To filter out System apps*/
        for(PackageInfo pi : packageList0)
        {
            boolean b = isSystemPackage(pi);
            if(!b)
            {
                packageList11.add(pi);
            }
        }
        
        for (PackageInfo pi:packageList11)
        {
	        String s1= packageManager1.getApplicationLabel(pi.applicationInfo).toString().toLowerCase();
	        if (s1.contains(query))
	        	packageList22.add(pi);
        }
        	apkList1.setAdapter(new ApkAdapter(this, packageList22, packageManager1));
	        apkList1.setOnItemClickListener(this);
    }
    
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
}