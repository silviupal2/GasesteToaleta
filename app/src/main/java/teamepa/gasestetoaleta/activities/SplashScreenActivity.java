package teamepa.gasestetoaleta.activities;

import android.content.Intent;
import android.os.Bundle;
import teamepa.gasestetoaleta.R;
import teamepa.gasestetoaleta.handler.UIHandler;

public class SplashScreenActivity extends AbstractMainActivity
{
	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		mUIHandler.sendEmptyMessageDelayed(UIHandler.ADVANCE_TO_NEXT_ACTIVITY, 1500);
	}

	@Override
	public void goToNextActivity()
	{
		super.goToNextActivity();
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(intent);
		finish();
	}
}