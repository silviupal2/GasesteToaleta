package teamepa.gasestetoaleta.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import teamepa.gasestetoaleta.Constants;
import teamepa.gasestetoaleta.EndpointsAsyncTask;
import teamepa.gasestetoaleta.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AbstractMainActivity
{
	private GoogleMap mMap; // Might be null if Google Play services APK is not available.

	protected ImageButton imageButton;
	protected boolean isFirstLaunch = true;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		imageButton = (ImageButton) findViewById(R.id.add_bathroom_button);
		imageButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!imageButton.isActivated())
				{
					imageButton.setActivated(true);
					imageButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_delete));
					Toast.makeText(getApplicationContext(), "Apasa pe harta unde vrei sa adaugi toaleta.",
							Toast.LENGTH_SHORT).show();
				}
				else
				{
					imageButton.setActivated(false);
					imageButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_input_add));
				}
			}
		});
		setUpMapIfNeeded();
		new EndpointsAsyncTask().execute(new Pair<Context, String>(this, getCurrentUserEmail()));
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded()
	{
		// Do a null check to confirm that we have not already instantiated the map.
		if (mMap == null)
		{
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
					.getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null)
			{
				addMarkerOnMap(Constants.BUCHAREST, 10);
				addAllMarkers(mMap);
				mMap.setMyLocationEnabled(true);
				mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener()
				{
					@Override
					public void onMapClick(LatLng latLng)
					{
						//TO DO WHEN CLICK ON MAP, ADD BATHROOM
						if (imageButton.isActivated())
						{
							addMarkerOnMap(latLng, mMap.getCameraPosition().zoom);
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Apasa + pentru a adauga toaleta.",
									Toast.LENGTH_SHORT).show();

						}
					}
				});
			}
		}
	}

	private void addMarkerOnMap(LatLng latLng, float zoom)
	{
		MarkerOptions marker = new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R
				.drawable.toilet));
		mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
		{
			@Override
			public boolean onMarkerClick(Marker marker)
			{
				showMarkerDialog(marker);
				return false;
			}
		});
		if (isFirstLaunch)
		{
			marker.title(Constants.Title.BUCHAREST).snippet(Constants.Snippet.BUCHAREST);
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
			mMap.animateCamera(CameraUpdateFactory.zoomTo(zoom + 5), 2000, null);
			mMap.addMarker(marker);
			isFirstLaunch = false;
		}
		else
		{
			showAddToiletDialog(latLng, zoom);
		}

	}

	protected void showAddToiletDialog(final LatLng latLng, final float zoom)
	{
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.adauga_toaleta_dialog, null);
		View viewTitlu = LayoutInflater.from(getApplicationContext()).inflate(R.layout.title_dialog, null);
		TextView titluDialog = (TextView) viewTitlu.findViewById(R.id.titlu_dialog_tv);
		titluDialog.setText(getAddress(latLng));
		final EditText mTitlu = (EditText) view.findViewById(R.id.et_titlu);
		final EditText mDescriere = (EditText) view.findViewById(R.id.et_descriere);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setCustomTitle(viewTitlu)
			 .setView(view)
			 .setPositiveButton(("OK"), new DialogInterface.OnClickListener()
			 {
				 @Override
				 public void onClick(DialogInterface dialog, int which)
				 {
					 //addDataToDatabase(mTitlu.getText().toString(),mDescriere.getText().toString(), latLng, );
					 mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom), 2000, null);
					 mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R
							 .drawable.toilet)).title(mTitlu.getText().toString()).snippet(mDescriere.getText()
																									 .toString()));
					 //refreshMarkers();
					 imageButton.setActivated(false);
					 imageButton.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_input_add));
				 }
			 })
			 .setNegativeButton("Anuleaza", new DialogInterface.OnClickListener()
			 {
				 @Override
				 public void onClick(DialogInterface dialog, int which)
				 {

				 }
			 }).show();


	}

	protected void showMarkerDialog(Marker marker)
	{
		View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.afiseaza_toaleta_dialog, null);
		View viewTitlu = LayoutInflater.from(getApplicationContext()).inflate(R.layout.title_dialog, null);
		TextView titluDialog = (TextView) viewTitlu.findViewById(R.id.titlu_dialog_tv);
		titluDialog.setText(getAddress(marker.getPosition()));
		TextView titlu = (TextView) view.findViewById(R.id.tv_titlu_toaleta);
		TextView descriere = (TextView) view.findViewById(R.id.tv_descriere_toaleta);
		titlu.setText(marker.getTitle());
		descriere.setText(marker.getSnippet());
		//getInfo from Database with the user that added this marker
		/*
		if marker.getPosition() equals database.getString(latlng)
		titlul si descrierea preiau acele date
		 */
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setCustomTitle(viewTitlu)
			 .setView(view)
			 .setPositiveButton(("Editeaza"), new DialogInterface.OnClickListener()
			 {
				 @Override
				 public void onClick(DialogInterface dialog, int which)
				 {

				 }
			 })
			 .setNegativeButton("Inapoi", new DialogInterface.OnClickListener()
			 {
				 @Override
				 public void onClick(DialogInterface dialog, int which)
				 {

				 }
			 }).show();


	}

	protected String getAddress(LatLng latLng)
	{
		Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);
		try
		{
			List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

			if (addresses != null)
			{
				Address returnedAddress = addresses.get(0);
				StringBuilder strReturnedAddress = new StringBuilder("Adresa:\n");
				for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++)
				{
					strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
				}
				return strReturnedAddress.toString();
			}
			else
			{
				return "No Address returned!";
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Canont get Address!";
		}
	}

	private void setUpMap()
	{
		mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
	}
}
