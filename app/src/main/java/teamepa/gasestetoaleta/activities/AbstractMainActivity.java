package teamepa.gasestetoaleta.activities;

import android.content.Intent;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import com.google.android.gms.maps.model.LatLng;
import teamepa.gasestetoaleta.ConnectionUtils;
import teamepa.gasestetoaleta.MainInterface;
import teamepa.gasestetoaleta.R;
import teamepa.gasestetoaleta.handler.UIHandler;

public class AbstractMainActivity extends FragmentActivity implements MainInterface
{
	Menu        mMenu;
	ImageButton imageButton;
	//	handlers
	protected final UIHandler mUIHandler = new UIHandler()
	{
		@Override
		protected boolean storeMessage(Message message)
		{
			return true;
		}

		@Override
		protected void processMessage(Message message)
		{
			switch (message.what)
			{
				case UIHandler.ADVANCE_TO_NEXT_ACTIVITY:
					preliminaryChecks();
					break;
			}
		}
	};

	protected void preliminaryChecks()
	{
		if (ConnectionUtils.getInternetConnection(getApplicationContext()))
		{
			goToNextActivity();
		}
		else
		{
			// go to no internet connection activity
			final Intent intent = new Intent(this, NoInternetConnectionActivity.class);
			startActivity(intent);
			finish();
		}
	}

	protected boolean checkConnection()
	{
		boolean isConnected;
		if (!ConnectionUtils.getInternetConnection(getApplicationContext()))
		{
			isConnected = false;
		}
		else
		{
			isConnected = true;
		}
		return isConnected;
	}

	@Override
	public void goToNextActivity()
	{
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		mMenu = menu;
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.action_about:
				return true;
			case R.id.action_help:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void addAllMarkers()
	{

	}

	public void refreshMarkers()
	{

	}

	void addDataToDatabase(String titlu, String Descriere, LatLng latLng, String email)
	{

	}

	/*protected void refresh()
	{
		imageButton = (ImageButton) getLayoutInflater().inflate(R.layout.refresh_action_view, null);
		Animation rotation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.animate_get_my_location);
		rotation.setRepeatCount(1);
		imageButton.startAnimation(rotation);
		MenuItem item = mMenu.findItem(R.id.action_select_location);
		MenuItemCompat.setActionView(item, imageButton);
	}*/
}
