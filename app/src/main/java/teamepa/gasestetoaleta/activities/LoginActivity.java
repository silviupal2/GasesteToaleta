package teamepa.gasestetoaleta.activities;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import com.google.android.gms.common.AccountPicker;
import teamepa.gasestetoaleta.ConnectionUtils;
import teamepa.gasestetoaleta.R;
import teamepa.gasestetoaleta.handler.UIHandler;

public class LoginActivity extends AbstractMainActivity
{
	public static final int REQUEST_CODE_PICK_ACCOUNT = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_page);
		ImageButton b = (ImageButton) findViewById(R.id.login_button);
		b.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				pickUserAccount();
			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		if (!ConnectionUtils.getInternetConnection(getApplicationContext()) && !mUIHandler.hasMessages(UIHandler
				.GO_TO_NO_INTERNET_CONNECTION_ACTIVITY))
		{
			// switch to main activity
			mUIHandler.sendMessage(Message.obtain(null, UIHandler.GO_TO_NO_INTERNET_CONNECTION_ACTIVITY));
		}
		else
		{
			mUIHandler.removeQueuedMessage(UIHandler.GO_TO_NO_INTERNET_CONNECTION_ACTIVITY);
		}
	}

	public static String[] googleAccount;

	public void pickUserAccount()
	{
		googleAccount = new String[]{"com.google"};
		Intent intent = AccountPicker.newChooseAccountIntent(null, null, googleAccount,
				false, null, null, null, null);
		startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);
	}

	public static String[] getGoogleAccount()
	{
		return googleAccount;
	}

	protected void onActivityResult(final int requestCode, final int resultCode,
									final Intent data)
	{
		if (requestCode == REQUEST_CODE_PICK_ACCOUNT && resultCode == RESULT_OK)
		{
			String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
			setCurrentAccount(accountName);
			System.out.println(accountName + " este contul meu!");
			goToNextActivity();
		}
	}

	@Override
	public void goToNextActivity()
	{
		super.goToNextActivity();
		Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
		startActivity(intent);
		finish();
	}

}
