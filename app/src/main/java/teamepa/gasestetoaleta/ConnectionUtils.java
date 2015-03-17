package teamepa.gasestetoaleta;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionUtils
{
	/**
	 * returns the current internet connection status (true/false)
	 */
	public static boolean getInternetConnection(Context c)
	{
		ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		// checking to see if the netInfo is null (airplane mode) or netInfo is connected
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

}
