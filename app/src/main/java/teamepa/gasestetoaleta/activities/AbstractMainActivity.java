package teamepa.gasestetoaleta.activities;

import android.content.Intent;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import teamepa.gasestetoaleta.ConnectionUtils;
import teamepa.gasestetoaleta.MainInterface;
import teamepa.gasestetoaleta.UIHandler;

public class AbstractMainActivity extends FragmentActivity implements MainInterface
{
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

	@Override
	public void goToNextActivity()
	{
	}
}
