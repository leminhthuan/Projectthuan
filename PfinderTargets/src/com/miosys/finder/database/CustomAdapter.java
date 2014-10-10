package com.miosys.finder.database;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miosys.finder.listview.PFinder_ListView;
import com.miosys.finder.ui.R;
@SuppressWarnings("rawtypes")
public class CustomAdapter extends BaseAdapter implements OnClickListener {

	private Activity myActivity;
	private ArrayList myData;
	private static LayoutInflater inflater = null;
	public Resources myRes;
	Infomation myInfo =null;
	int i=0;
	
	
	public CustomAdapter (Activity ac,ArrayList ar, Resources re)
	{
		myActivity =ac;
		myData =ar;
		myRes =re;
		inflater =(LayoutInflater)myActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
//		if(myData.size()<=0)
//			return 1;
		// TODO Auto-generated method stub
		return myData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public static class ViewHolder
	{
		public ImageView mImage;
		public TextView mText;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View mView = convertView;
		ViewHolder mViewHolder ;
		
		
		if(convertView ==null)
		{
			mView =inflater.inflate(R.layout.customlayout, null);
			mViewHolder = new ViewHolder();
			mViewHolder.mText =(TextView)mView.findViewById(R.id.txtViewCustom);
			mViewHolder.mImage =(ImageView)mView.findViewById(R.id.imgViewCustom);
			mView.setTag(mViewHolder);
		}
		else
		
		mViewHolder=(ViewHolder)mView.getTag();
		
		
		if(myData.size()<=0)
		{
			mViewHolder.mText.setText("NO DATA");
		}
		else
		{
			//Random Image to Custom listView
			myInfo =null;
			myInfo=(Infomation)myData.get(position);
			mViewHolder.mText.setText(myInfo.getInfo());
			int imageArray []  = 
					{
					R.drawable.baloon1,R.drawable.baloon2,
                    R.drawable.baloon3
					};
					int rand = new Random().nextInt(2 - 0)+0;
			mViewHolder.mImage.setImageResource(imageArray[rand]);
			mView.setOnClickListener(new OnItemClickListener( position ));
			
		}
		
		return mView;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.e("CustomAdapter", "");
	}
	public class OnItemClickListener implements OnClickListener {

		private int mPosition;
		public OnItemClickListener(int position) {
			// TODO Auto-generated constructor stub
			mPosition = position;
		}
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//for method clickItem in MainActivity
			PFinder_ListView main = (PFinder_ListView)myActivity;
			main.onItemClick(mPosition);
			
		}

	}
	
	
	

}
