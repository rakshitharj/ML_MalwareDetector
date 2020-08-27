package com.project.smaliparser;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SmaliParser {
	public static void smaliparse(List opfiles) throws IOException
	{
		FileReader fr = null;
		BufferedReader br = null;
		List opfilepaths = opfiles;
		System.out.println(opfilepaths.getItem(0));
		for(int i=0;i<opfilepaths.getItemCount();i++)
		{
			BufferedWriter op = new BufferedWriter(new FileWriter(opfilepaths.getItem(i)+"/func.txt"));
			System.out.println(opfilepaths.getItem(i)+"/func.txt");
			fr = new FileReader(opfilepaths.getItem(i)+"/smalilist.txt");
			System.out.println(opfilepaths.getItem(i)+"/smalilist.txt");
			br = new BufferedReader(fr);
			//FileReader fr1 = new FileReader(br);
			String st= null;
			String X;
			for(int j=0;((X = br.readLine())!=null);j++)
			{
				System.out.println(X);
				FileReader fr1 = new FileReader(br.readLine());
				BufferedReader br1 = new BufferedReader(fr1);
			while((st=br1.readLine())!=null)
			{
				System.out.println(st);
				if(st.isEmpty())
				{
					continue;
				}
				else if(st.trim().startsWith("# direct methods"))
				{
					do
					{
						if(st.trim().startsWith("invoke-direct"))
						{
							System.out.println(" direct  Function Call:"+st.trim().substring(st.indexOf("},")-2).trim());
							op.write(st.trim().substring(st.indexOf("},")-2).trim());
							op.newLine();
						}
						else if(st.trim().startsWith("invoke-virtual"))
						{
							System.out.println(" virtual Function Call:"+st.trim().substring(st.indexOf("},")-2).trim());
							op.write(st.trim().substring(st.indexOf("},")-2).trim());
							op.newLine();
						}
						else if(st.trim().startsWith("invoke-interface"))
						{
							System.out.println(" interface Function Call:"+st.trim().substring(st.indexOf("},")-2).trim());
							op.write(st.trim().substring(st.indexOf("},")-2).trim());
							op.newLine();
						}
						else
						{
							continue;
						}
					}
					while((st=br.readLine())!=null);
				}
			}
	System.out.println("\n-------------------------------------------------------------------------------------------------------------------\n");
			}
		}
	}
	public static void main(String[] Args) throws IOException
	{
		List l1 = new List();
		l1.add("/tmp/op/file0/smali");
		l1.add("/tmp/op/file1/smali");
		l1.add("/tmp/op/file2/smali");
		l1.add("/tmp/op/file3/smali");
		smaliparse(l1);
	}

}
