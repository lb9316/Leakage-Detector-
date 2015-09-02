package com.ibc.android.demo.appslist.activity;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
 
public class ApkListActivity extends Activity implements OnItemClickListener
{
    PackageManager packageManager;
    ListView apkList;
 
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
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
        
        /*Sorting the arrayList*/
        String appName[]=new String[packageList1.size()];
        int t=0;
        for(PackageInfo pi : packageList1)
        {
        	 appName[t] = packageManager.getApplicationLabel(pi.applicationInfo).toString();
        	 appName[t] = appName[t].trim();
        	 t++;
        }
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(appName));
        Collections.sort(list);
        
        List<PackageInfo> packageList2 = new ArrayList<PackageInfo>();
        
        for (int i=0;i<packageList1.size();i++)
        {
        	for(PackageInfo pi : packageList1)
        	{
        		if (packageManager.getApplicationLabel(pi.applicationInfo).toString().trim()==list.get(i))
        		{
        			packageList2.add(pi);
        		}
        	}
        }
        
        apkList = (ListView) findViewById(R.id.applist);
        apkList.setAdapter(new ApkAdapter(this, packageList2, packageManager));
        apkList.setOnItemClickListener(this);
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
        getMenuInflater().inflate(R.menu.apk_list_activity2, menu);
        
     // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Take appropriate action for each action item click
        switch (item.getItemId()) 
        {
        case R.id.action_search:
            // search action
            return true;
        case R.id.action_sort_state:
        	Intent sort_state = new Intent(getApplicationContext(), ApkListActivity1.class);
            startActivity(sort_state);
            return true;
        case R.id.action_sort_alpha:
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
        