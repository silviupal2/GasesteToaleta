package com.example.SilviuPal.myapplication.backend;

/**
 * Created by SilviuPal on 08-Apr-15.
 */
public class ToaletaModel
{
	private int    id;
	private String coordx;
	private String coordy;
	private String descriere;
	private String author_email;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getCoordx()
	{
		return coordx;
	}

	public void setCoordx(String coordx)
	{
		this.coordx = coordx;
	}

	public String getCoordy()
	{
		return coordy;
	}

	public void setCoordy(String coordy)
	{
		this.coordy = coordy;
	}

	public String getDescriere()
	{
		return descriere;
	}

	public void setDescriere(String descriere)
	{
		this.descriere = descriere;
	}

	public String getAuthor_email()
	{
		return author_email;
	}

	public void setAuthor_email(String author_email)
	{
		this.author_email = author_email;
	}
}
