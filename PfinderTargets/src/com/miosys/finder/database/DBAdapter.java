package com.miosys.finder.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBAdapter {

	public static final String TAG ="DBAdapter";
	
	public static final String KEY_ID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_URL = "url";
	
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDB;
	
	private static final String DATABASE_CREATE = "create table users (_id integer primary key autoincrement, "+ "name text not null, "+ "url text not null);";
	private static final String DATABASE_NAME = "Database_Demo";
	private static final String DATABASE_TABLE = "users";
	private static final int DATABASE_VERSION = 3;
	
	private final Context mContext;
	
	//This class use create database, test and update new version
	private static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.i(TAG, "Upgrading DB");
			db.execSQL("DROP TABLE IF EXISTS users");
			onCreate(db);
		}
	}
	
	public DBAdapter(Context ctx){
		this.mContext = ctx;
	}
	
	//Open database
	public DBAdapter open()
	{
		mDbHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
		mDB = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		mDbHelper.close();
	}
	
	//Add value to database
	public long createUser(String name, String url){
		ContentValues inititalValues = new ContentValues();
		inititalValues.put(KEY_NAME, name);
		inititalValues.put(KEY_URL, url);
		return mDB.insert(DATABASE_TABLE, null, inititalValues);
	}
	
	//delete row
	public boolean deleteUser(long rowId)
	{
		return mDB.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) >0;
	}
	
	//get all users
	public Cursor getAllUsers(){
		return mDB.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_NAME, KEY_URL}, null, null, null, null, null);
	}
	
	//count user number
	public long count(){
		return DatabaseUtils.queryNumEntries(mDB, "users");
	}
	
	//delete all users
	public void deleteAllUser()
	{
		mDB.delete(DATABASE_TABLE, null, null);
	}
}
