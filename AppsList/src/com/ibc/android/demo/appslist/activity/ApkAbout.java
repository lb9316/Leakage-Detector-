package com.ibc.android.demo.appslist.activity;
 
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
 
public class ApkAbout extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.apk_about, menu);
        return super.onCreateOptionsMenu(menu);
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Take appropriate action for each action item click
        switch (item.getItemId()) 
        {
        case R.id.action_sort_state:
        	Intent sort_state = new Intent(getApplicationContext(), ApkListActivity1.class);
            startActivity(sort_state);
            return true;
        case R.id.action_sort_alpha:
        	Intent sort_aplha = new Intent(getApplicationContext(), ApkListActivity.class);
            startActivity(sort_aplha);
            return true;
        case R.id.action_about:
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
}