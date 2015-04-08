package teamepa.gasestetoaleta.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Vector;

public abstract class PauseHandler extends Handler
{

	public PauseHandler()
	{
		// TODO Auto-generated constructor stub
	}

	public PauseHandler(Callback callback)
	{
		super(callback);
		// TODO Auto-generated constructor stub
	}

	public PauseHandler(Looper looper)
	{
		super(looper);
		// TODO Auto-generated constructor stub
	}

	public PauseHandler(Looper looper, Callback callback)
	{
		super(looper, callback);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Message Queue Buffer
	 */
	final protected Vector<Message> messageQueueBuffer = new Vector<Message>();

	/**
	 * Flag indicating the pause state
	 */
	private boolean paused;

	/**
	 * Resume the handler
	 */
	final public void resume()
	{
		paused = false;

		while (messageQueueBuffer.size() > 0)
		{
			final Message msg = messageQueueBuffer.elementAt(0);
			messageQueueBuffer.removeElementAt(0);
			sendMessage(msg);
		}
	}

	/**
	 * Pause the handler
	 */
	final public void pause()
	{
		paused = true;
	}

	/**
	 * Notification that the message is about to be stored as the activity is
	 * paused. If not handled the message will be saved and replayed when the
	 * activity resumes.
	 *
	 * @param message the message which optional can be handled
	 * @return true if the message is to be stored
	 */
	protected abstract boolean storeMessage(Message message);

	/**
	 * Notification message to be processed. This will either be directly from
	 * handleMessage or played back from a saved message when the activity was
	 * paused.
	 *
	 * @param message the message to be handled
	 */
	protected abstract void processMessage(Message message);

	/**
	 * {@inheritDoc}
	 */
	@Override
	final public void handleMessage(Message msg)
	{
		if (paused)
		{
			if (storeMessage(msg))
			{
				Message msgCopy = new Message();
				msgCopy.copyFrom(msg);
				messageQueueBuffer.add(msgCopy);
			}
		}
		else
		{
			processMessage(msg);
		}
	}

	/**
	 * Removes all the cached messages from the pause Activity state
	 */
	public final void clearCachedMessages()
	{
		if (messageQueueBuffer != null)
		{
			messageQueueBuffer.clear();
		}
	}


}
