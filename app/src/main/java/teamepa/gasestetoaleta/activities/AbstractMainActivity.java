package teamepa.gasestetoaleta.activities;

import android.content.Intent;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import teamepa.gasestetoaleta.ConnectionUtils;
import teamepa.gasestetoaleta.Constants;
import teamepa.gasestetoaleta.MainInterface;
import teamepa.gasestetoaleta.R;
import teamepa.gasestetoaleta.handler.UIHandler;

public abstract class AbstractMainActivity extends FragmentActivity implements MainInterface
{
	Menu        mMenu;
	ImageButton imageButton;
	public static String mCurrentAccount;
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

	public void addAllMarkers(GoogleMap mMap)
	{
		MarkerOptions marker;
		marker = new MarkerOptions().position(Constants.BUCHAREST_1).icon(BitmapDescriptorFactory.fromResource(R.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_1).snippet(Constants.Snippet.BUCHAREST_1);
		mMap.addMarker(marker);

		marker = new MarkerOptions().position(Constants.BUCHAREST_2).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_2).snippet(Constants.Snippet.BUCHAREST_2);
		mMap.addMarker(marker);

		marker = new MarkerOptions().position(Constants.BUCHAREST_3).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_3).snippet(Constants.Snippet.BUCHAREST_3);
		mMap.addMarker(marker);

		marker = new MarkerOptions().position(Constants.BUCHAREST_4).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_4).snippet(Constants.Snippet.BUCHAREST_4);
		mMap.addMarker(marker);

		marker = new MarkerOptions().position(Constants.BUCHAREST_5).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_5).snippet(Constants.Snippet.BUCHAREST_5);
		mMap.addMarker(marker);

		marker = new MarkerOptions().position(Constants.BUCHAREST_6).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_6).snippet(Constants.Snippet.BUCHAREST_6);
		mMap.addMarker(marker);
		marker = new MarkerOptions().position(Constants.BUCHAREST_7).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_7).snippet(Constants.Snippet.BUCHAREST_7);
		mMap.addMarker(marker);
		marker = new MarkerOptions().position(Constants.BUCHAREST_8).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_8).snippet(Constants.Snippet.BUCHAREST_8);
		mMap.addMarker(marker);
		marker = new MarkerOptions().position(Constants.BUCHAREST_9).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_9).snippet(Constants.Snippet.BUCHAREST_9);
		mMap.addMarker(marker);
		marker = new MarkerOptions().position(Constants.BUCHAREST_10).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		marker.title(Constants.Title.BUCHAREST_10).snippet(Constants.Snippet.BUCHAREST_10);
		mMap.addMarker(marker);

	}

	public void refreshMarkers()
	{

	}

	void addDataToDatabase(String titlu, String Descriere, LatLng latLng, String email)
	{

	}

	public static void setCurrentAccount(String gmail)
	{
		mCurrentAccount = gmail;
	}

	public String getCurrentUserEmail()
	{
		return mCurrentAccount;
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
