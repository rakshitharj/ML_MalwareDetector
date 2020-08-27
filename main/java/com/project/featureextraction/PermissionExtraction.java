package com.project.featureextraction;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class PermissionExtraction {
	public static int[] permissionExtractor(String filepath) throws IOException
	{
		String fpaths = filepath;
		String X = null;
		String feac = null;
		int[] fea = {0,0,0,0,0,0,0};
		BufferedWriter op = null;
		for(int i=0;i<fpaths.length();i++)
		{
			FileReader fr = new FileReader(fpaths+"/perm.txt");
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter(fpaths+"/PermFeatures.txt");
			op = new BufferedWriter(fw);
			for(int k =0;k<fea.length;k++) fea[k] = 0;
			while((X=br.readLine())!=null)
			{
				if(X.isEmpty())
				 {
					//System.out.println("Is Empty");
					 continue;
				 }
				if(X.compareTo("android.permission.INTERNET")==0)
				{
					fea[0] = 1;
				}
				if(X.compareTo("android.permission.READ_PHONE_STATE")==0)
				{
					fea[1] = 1;
				}
				if(X.compareTo("android.permission.SEND_SMS")==0)
				{
					fea[2] = 1;
				}
				if(X.compareTo("android.permission.READ_SMS")==0)
				{
					fea[3] = 1;
				}
				if(X.compareTo("android.permission.RECEIVE_SMS")==0)
				{
					fea[4] = 1;
				}
				if(X.compareTo("android.permission.RECORD_AUDIO")==0)
				{
					fea[5] = 1;
				}
				if(X.compareTo("android.permission.READ_CONTACTS")==0)
				{
					fea[6] = 1;
				}
			}
			feac = Arrays.toString(fea);
			//System.out.println(feac);
			op.write(feac);
			//op.newLine();
			op.close();
		}
		return fea;
	}
	public static void main(String[] Args) throws IOException
	{
		List l1 = new List();
		int[] i1 = {};
		l1.add("/tmp/op/file0");
		l1.add("/tmp/op/file1");
		l1.add("/tmp/op/file2");
		//l1.add("/tmp/op/file3/smali/smalilist.txt/");
		for(int i=0;i<l1.getItemCount();i++)
		{
			i1 = null;
			i1 = permissionExtractor(l1.getItem(i));
			for(int j=0;j<i1.length;j++)
			{
				System.out.println(i1[j]);
			}
		}
		
	}
}
