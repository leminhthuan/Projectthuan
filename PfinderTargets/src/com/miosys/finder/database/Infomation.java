package com.miosys.finder.database;

//Create function get and set for each field of database
public class Infomation {

	private String image="";
	private String info="";
	private String url="";
	
	public void setImage(String image)
	{
		this.image=image;
	}
	
	public void setInfo(String info)
	{
		this.info=info;
	}
	
	public String getImage()
	{
		return this.image;
	}
	public String getInfo()
	{
		return this.info;
	}
	public void setUrl(String url)
	{
		this.url=url;
	}
	public String getUrl()
	{
		return this.url=url;
	}
}
