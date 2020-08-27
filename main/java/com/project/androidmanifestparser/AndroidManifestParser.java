package com.project.androidmanifestparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.List;
import java.io.*;
import javax.xml.parsers.*;

public class AndroidManifestParser {
	public static void parse(String filepath)
	{
		try
		{
			BufferedWriter output = new BufferedWriter(new FileWriter(filepath+"/perm.txt"));
			int i=0;
			File input = new File(filepath+"/AndroidManifest.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();
			NodeList ndList = doc.getElementsByTagName("uses-permission");
			for(i=0;i<ndList.getLength();i++)
			{
				Node nNode = ndList.item(i);
				Element e = (Element)nNode;
				output.write(e.getAttribute("android:name").toString());
				output.newLine();
			}
			output.close();
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void main(String[] Args) throws IOException
	{
		List l1 = new List();
		l1.add("/tmp/op/file0");
		l1.add("/tmp/op/file1");
		l1.add("/tmp/op/file2");
		for(int i=0;i<l1.getItemCount();i++)
		{
			parse(l1.getItem(i));
		}
	}
}
