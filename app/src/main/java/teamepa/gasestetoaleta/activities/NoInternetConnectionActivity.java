package teamepa.gasestetoaleta.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import teamepa.gasestetoaleta.R;

public class NoInternetConnectionActivity extends AbstractMainActivity
{
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.no_internet_connection);
		Button tryAgain = (Button) findViewById(R.id.try_again_button);
		tryAgain.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				boolean connection = checkConnection();
				if (connection)
				{
					goBackToLogin();
				}
			}
		});
	}

	protected void goBackToLogin()
	{
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		startActivity(intent);
		finish();
	}
}
