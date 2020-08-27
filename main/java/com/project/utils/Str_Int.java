package com.project.utils;

public class Str_Int {
	private String respstring;
	private int exitstat;
	
	public Str_Int()
	{
		respstring = "";
		exitstat = 0;
	}
	
	public void set_respstring(String arg_respstring)
	{
		respstring = arg_respstring;
	}
	
	public String get_respstring()
	{
		return respstring;
	}
	
	public void set_exitstat(int arg_exitstat)
	{
		exitstat = arg_exitstat;
	}
	
	public int get_exitstat()
	{
		return exitstat;
	}
}
