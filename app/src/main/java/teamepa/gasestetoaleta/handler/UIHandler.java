package teamepa.gasestetoaleta.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by SilviuPal on 17-Mar-15.
 */
public class UIHandler extends PauseHandler
{
	public static final int ADVANCE_TO_NEXT_ACTIVITY              = 0;
	public static final int GO_TO_NO_INTERNET_CONNECTION_ACTIVITY = 1;

	public UIHandler()
	{
	}

	public UIHandler(Handler.Callback callback)
	{
		super(callback);
	}

	public UIHandler(Looper looper)
	{
		super(looper);
	}

	public UIHandler(Looper looper, Handler.Callback callback)
	{
		super(looper, callback);
	}

	@Override
	protected boolean storeMessage(Message message)
	{
		return false;
	}

	@Override
	protected void processMessage(Message message)
	{
	}

	public void removeQueuedMessage(int what)
	{
		for (int i = 0; i < messageQueueBuffer.size(); i++)
		{
			final Message toCompare = messageQueueBuffer.get(i);
			if (toCompare.what == what)
			{
				messageQueueBuffer.remove(toCompare);
			}
		}
	}
}
