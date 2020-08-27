package com.project.fileexplorer;
import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
public class ApkFileExplorer {
	static ArrayList<String> l2 = new ArrayList<String>();
	public static List apkexplorer(String apkfname)
	{
		List apkfilenamelist = new List();
		apkfilenamelist.removeAll();
		String apkfoldername = apkfname;
		try
		{
			apksearcher(apkfoldername);
			for(int j=0;j<l2.size();j++)
			{
				apkfilenamelist.add(l2.get(j).toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return apkfilenamelist;
	}
	public static void apksearcher(String apkfolderarg)
	{
		l2.clear();
		try
		{ 
			File folder = new File(apkfolderarg);
			File[] listOfFiles = folder.listFiles();
		    	for (int i = 0; i < listOfFiles.length; i++) 
		    	{
		    		if (listOfFiles[i].isFile()) 
		    		{
		    			l2.add(listOfFiles[i].getPath().toString());
		    			//System.out.println(listOfFiles[i].getPath().toString()+listOfFiles[i].getName().toString());
		    		} 
		    		else if (listOfFiles[i].isDirectory()) 
		    		{
		    			apksearcher(listOfFiles[i].toString());
		    		}
		    	}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		String fpath = "/home/msudesh_kumar/Documents/apkfiles2";
		String tfpath = "/home/msudesh_kumar/Documents/tapkfiles2";
		List l = apkexplorer(fpath);
		for(int i = 0; i<l.getItemCount();i++)
		System.out.println(l.getItem(i));
		List l2 = apkexplorer(tfpath);
		for(int i = 0; i<l2.getItemCount();i++)
		System.out.println(l2.getItem(i));
	}
}
