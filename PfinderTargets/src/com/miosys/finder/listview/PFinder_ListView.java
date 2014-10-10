package com.miosys.finder.listview;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.miosys.finder.database.CustomAdapter;
import com.miosys.finder.database.DBAdapter;
import com.miosys.finder.database.Infomation;
import com.miosys.finder.ui.R;
import com.miosys.finder.web.PFinder_WebView;

public class PFinder_ListView extends Activity {
		
	public static String c;
	private DBAdapter mDB;
	private Cursor mCursor;

//	Boolean checkDelete = false;
	ListView mList;
	CustomAdapter mAdapter;
	public ArrayList<Infomation> mInfoArr = new ArrayList<Infomation>();
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pfinder_list_view);

		mDB = new DBAdapter(this);
    	mDB.open();

		//set value for 2 colum name and url
    	Intent i = getIntent();
//		String targetName = i.getStringExtra("targetName");
		mDB.createUser(i.getStringExtra("targetName"), "https://youtube.com/");		
    	getData();
    	
    	Log.e("count", String.valueOf(mDB.count()));
		
		Resources mRes =getResources();
		mList  = (ListView)findViewById(R.id.listview1);
		mAdapter = new CustomAdapter(this, mInfoArr, mRes);
		mList.setAdapter(mAdapter);
		
		//delete all users
		Button buttonReset = (Button) findViewById(R.id.btnReset);
		buttonReset.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				mDB.deleteAllUser();
				//mInfoArr.clear();
				getData();
				mAdapter.notifyDataSetChanged();
				Log.d("ListView","Count: " + mInfoArr.size());
			}
		});
		
		//back to vofuria camera
		Button btnBack = (Button) findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
			}
		});
	}
	//getData from database to show listview and put data into InfoArr
	  private void getData() {
		  	mInfoArr.clear();
		  
			mCursor = mDB.getAllUsers();
			mCursor.moveToFirst();
			String namedata, urldata;
	    	
	    	for (int i = 0; i < mDB.count(); i++) {

				namedata = mCursor.getString(1);
				urldata = mCursor.getString(2);
				mCursor.moveToNext();
				Log.e("string", namedata+urldata); 
				
		        final Infomation info = new Infomation();   
		    	info.setInfo("Info:"+ namedata);
		    	info.setUrl(urldata);
		    	Log.e("url",urldata);
	            mInfoArr.add(info);
	    	}
	  }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//when click each row of listview, send url to webview activity and start it 
	public void onItemClick(int mPosition) {
		// TODO Auto-generated method stub
		Infomation infoValues = (Infomation) mInfoArr.get(mPosition);
		 
        Toast.makeText(PFinder_ListView.this,"Info: "+infoValues.getUrl(),Toast.LENGTH_LONG).show();
        Intent i = new Intent (PFinder_ListView.this, PFinder_WebView.class);
      	i.putExtra(c, infoValues.getUrl());    
      	startActivity(i); 

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
	}


}
