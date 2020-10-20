package com.androidstudy.contentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

	public void onClickAddName(View view) {
		// Add a new student record
		ContentValues values = new ContentValues();

		values.put(StudentsProvider.NAME, ((EditText) findViewById(R.id.editText)).getText().toString());

		values.put(StudentsProvider.GRADE, ((EditText) findViewById(R.id.editText1)).getText().toString());

		Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);

		Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
	}

	public void onClickRetrieveStudents(View view) {

		// Retrieve student records
//      String URL = "content://com.example.provider.College/students";
//
//      Uri students = Uri.parse(URL);
//      Cursor c = managedQuery(students, null, null, null, "name");

		String name = ((EditText) findViewById(R.id.editText)).getText().toString();
		String grade = ((EditText) findViewById(R.id.editText1)).getText().toString();
		StringBuffer selection = new StringBuffer();
		Cursor c;
		if (name != null && name.trim().length() > 0) {
			// 根据姓名查找
			selection.append(StudentsProvider.NAME + " =? ");
			c = getContentResolver().query(StudentsProvider.CONTENT_URI, null, selection.toString(),
					new String[] { "" + name.trim() + "" }, "name");
		} else if (grade != null && grade.trim().length() > 0) {
			// 根据年级查找
			selection.append(StudentsProvider.GRADE + " =? ");
			c = getContentResolver().query(StudentsProvider.CONTENT_URI, null, selection.toString(),
					new String[] { "" + grade.trim() + "" }, "name");
		} else {
			// 查找所有
			c = getContentResolver().query(StudentsProvider.CONTENT_URI, null, null, null, "name");
		}

		if (c.moveToFirst()) {
			do {
				Toast.makeText(this,
						c.getString(c.getColumnIndex(StudentsProvider._ID)) + ", "
								+ c.getString(c.getColumnIndex(StudentsProvider.NAME)) + ", "
								+ c.getString(c.getColumnIndex(StudentsProvider.GRADE)),
						Toast.LENGTH_SHORT).show();
			} while (c.moveToNext());
		}
	}
}
