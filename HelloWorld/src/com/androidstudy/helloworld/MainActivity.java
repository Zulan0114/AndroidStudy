package com.androidstudy.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	String msg = "Android : ";

	/** 当活动第一次被创建时调用 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(msg, "The onCreate() event");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	/** 当活动即将可见时调用 */
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(msg, "The onStart() event");
	}

	/** 当活动可见时调用 */
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(msg, "The onResume() event");
	}

	/** 当其他活动获得焦点时调用 */
	@Override
	protected void onPause() {
		super.onPause();
		Log.d(msg, "The onPause() event");
	}

	/** 当活动不再可见时调用 */
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(msg, "The onStop() event");
	}

	/** 当活动将被销毁时调用 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(msg, "The onDestroy() event");
	}
}
