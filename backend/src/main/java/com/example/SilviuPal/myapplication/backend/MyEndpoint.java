/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.SilviuPal.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(name = "myApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend.myapplication.SilviuPal.example.com", ownerName = "backend.myapplication.SilviuPal.example.com", packagePath = ""))
public class MyEndpoint
{

	/**
	 * A simple endpoint method that takes a name and says Hi back
	 */
	@ApiMethod(name = "sayHi")
	public MyBean sayHi(@Named("name") String name)
	{
		MyBean response = new MyBean();
		response.setData("Hi, " + name);

		return response;
	}

	@ApiMethod(name = "afiseazaToaleta")
	public ToaletaModel afiseazaToaleta(@Named("id") int id, @Named("coordx") String coordx,
										@Named("coordy") String coordy,
										@Named("descriere") String descriere, @Named("author_email") String author_email)
	{
		ToaletaModel response = new ToaletaModel();
		response.setId(id);
		response.setCoordx(coordx);
		response.setCoordy(coordy);
		response.setDescriere(descriere);
		response.setAuthor_email(author_email);

		return response;
	}

}
