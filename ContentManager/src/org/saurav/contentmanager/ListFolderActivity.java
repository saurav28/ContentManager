package org.saurav.contentmanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class ListFolderActivity extends FragmentActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_folder);
//		Intent getIntentFromLoginScrneen = getIntent();
//		String userName = getIntentFromLoginScrneen.getStringExtra("UserName");
//		//Create a text view
//		TextView welcomeUserNameText = (TextView) findViewById(R.id.WelcomeText);
//		welcomeUserNameText.setText("Welcome::"+userName);
//		TextView welcomeUserNameText = new TextView(this);
//		welcomeUserNameText.setTextSize(40);
//		welcomeUserNameText.setText(userName);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_folder, menu);
		return true;
	}

}
