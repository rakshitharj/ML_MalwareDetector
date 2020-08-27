package com.project.apktool;

import java.awt.List;

import com.project.utils.LinuxInteractor;
import com.project.utils.Str_Int;

public class Apktool {
	public static List ApktoolExec(List apkfilepath)
	{
		List ipfile = apkfilepath;
		List apklist = new List();
		LinuxInteractor li = new LinuxInteractor();
		try
		{
			for(int i=0;i<ipfile.getItemCount();i++)
			{
				Str_Int op = li.executeCommand("/usr/bin/java -jar /opt/apktool_2.2.2.jar/apktool_2.2.2.jar d \""+ipfile.getItem(i)+"\" -o \"/tmp/op/file-"+ipfile.getItem(i).substring((ipfile.getItem(i).indexOf("com.")), (ipfile.getItem(i).indexOf(".com")))+"\" -f",true);
				apklist.add("/tmp/op/file-"+ipfile.getItem(i).substring((ipfile.getItem(i).indexOf("com.")), (ipfile.getItem(i).indexOf(".com"))));
				System.out.println("Response: "+op.get_respstring()+"Exit Status: "+op.get_exitstat());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return apklist;
	}
	public static void main(String[] args)
	{
		List l =new List();
		List l1 = new List();
		//l.add("/home/msudesh_kumar/Documents/apkfiles2/{**malware**}com.sec.android.app.camera.shootingmode.dual_3.011-16_minAPI23(nodpi)_apkmirror.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/{**malware**}com.ComponentNotInManifest1.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.sec.android.sidesync.source_3.1.0.1963037-124_minAPI19(nodpi)_apkmirror.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/{**malware**}com.XTaoAd.A.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.apkfiles.com_507557_NatureLiveWallpaper.apk.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/{**malware**}com.021d55c415ff951c8e7b1ce3f94399bb.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.Bluetooth GPS Output.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.android.apps.tag_3.0.01-300100100_minAPI1(nodpi)_apkmirror.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.Free Wifi Hotspot Portable.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.Quick Memo.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.samsung.android.allshare.service.fileshare_3.0-30_minAPI23(nodpi)_apkmirror.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.samsung.android.app.accesscontrol_1.6.01-48-160100048_minAPI23(nodpi)_apkmirror.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.Flash On Call (SMS Alerts)-2.0.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.coinbase.android_4.0.0-99_minAPI15(nodpi)_apkmirror.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/{**malware**}com.b5f9a2b92bccb21d7cdd0d81c3d5b4a63ad04775e288218f1a8e5a79ed54025d.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.DroidMote Client.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.android.htmlviewer_1.1.22-112200000_minAPI14(nodpi)_apkmirror.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/{**malware**}com.ContentProvider1.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/{**malware**}com.Unregister1.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.de.battery.widget.com.apk");
		l.add("/home/msudesh_kumar/Documents/apkfiles2/com.My Notes-1.2.com.apk");
		//for(i-0;i<)
		
		l1 = ApktoolExec(l);
		for(int j=0;j<l1.getItemCount();j++)
		{
			System.out.println(l1.getItem(j));
		}
	}

}