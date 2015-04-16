package teamepa.gasestetoaleta.activities;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by SilviuPal on 08-Apr-15.
 */
public class LoginActivityTest extends TestCase
{
	@Test
	public void testPickUserAccount()
	{
		String[] x = LoginActivity.getGoogleAccount();
		assertTrue(x[0].toString(), true);


	}

}
