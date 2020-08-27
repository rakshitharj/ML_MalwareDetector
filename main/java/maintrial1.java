import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.List;
//import java.
import java.util.Arrays;

//import org.apache.commons.lang.ArrayUtils;

import com.project.fileexplorer.ApkFileExplorer;
import com.project.fileexplorer.FileExplorer;
import com.project.apktool.Apktool;
import com.project.smaliparser.SmaliParser1;
import com.project.androidmanifestparser.AndroidManifestParser;
import com.project.featureextraction.*;
import com.project.deeplearningengine.DBN;
import com.project.machinelearningengine.*;
//import jsat.classifiers.DataPoint;
//import jsat.linear.Vec;
public class maintrial1 {
	public static void main(String[] args) throws IOException
	{
		/*String trainpath = args[1];
		String testpath = args[2];*/
		String trainpath = "/home/msudesh_kumar/Documents/apkfiles3";
		String testpath = "/home/msudesh_kumar/Documents/tapkfiles3";
		String X = null;
		List files = new List();
		files.removeAll();
		files = null;
		List tfiles =  new List();
		tfiles.removeAll();
		List apktoolfiles = new List();
		List tapktoolfiles = new List();
		/*int[][] train_X_perm = new int[4][6];
		int[][] train_X_func = new int[4][6];
		int[][] train_X = new int[4][12];
		int[][] train_Y = new int[4][2];
		int[][] test_X_perm = new int[4][6];
		int[][] test_X_func = new int[4][6];
		int[][] test_X = new int[2][12];
		int[][] test_Y = new int[2][2];
		int[][] dres = new int[test_X.length][2];
		int[][] mres = new int[test_X.length][2];*/
		int[][] train_X_perm = null;
		int[][] train_X_func = null;
		int[][] train_X = null;
		int[][] train_Y = null;
		int[][] test_X_perm = null;
		int[][] test_X_func = null;
		int[][] test_X = null;
		int[][] test_Y = null;
		int[][] dres = null;
		int[][] mres = null;
		int mcor=0;
		int dcor=0;
		long totalTime = 0;
		//int[][] test_X  = new int[2][6];
		/*int[][] test_X = {
         		{1,1,1,1,0,0,0,0,1,0,1,1},
         		{1,1,1,1,0,0,0,0,1,0,1,0}
         };*/
		long startTime = System.currentTimeMillis();
		files = ApkFileExplorer.apkexplorer(trainpath);
		for(int f=0;f<files.getItemCount();f++)
		{
			System.out.println(files.getItem(f));
		}
		System.out.println("-----------------------------------------------------------------------------------");
		tfiles = ApkFileExplorer.apkexplorer(testpath);
		for(int f=0;f<tfiles.getItemCount();f++)
		{
			System.out.println(tfiles.getItem(f));
		}
		System.out.println("-----------------------------------------------------------------------------------");
		train_X_perm = new int[files.getItemCount()][7];
		train_X_func = new int[files.getItemCount()][6];
		train_X = new int[files.getItemCount()][14];
		train_Y = new int[files.getItemCount()][2];
		test_X_perm = new int[tfiles.getItemCount()][7];
		test_X_func = new int[tfiles.getItemCount()][6];
		test_X = new int[tfiles.getItemCount()][14];
		test_Y = new int[tfiles.getItemCount()][2];
		apktoolfiles = Apktool.ApktoolExec(files);
		tapktoolfiles = Apktool.ApktoolExec(tfiles);
		dres = new int[test_X.length][2];
		mres = new int[test_X.length][2];
		for(int i=0;i<apktoolfiles.getItemCount();i++)
		{
			FileExplorer.explorer(apktoolfiles.getItem(i));
		}
		for(int i=0;i<tapktoolfiles.getItemCount();i++)
		{
			FileExplorer.explorer(tapktoolfiles.getItem(i));
		}
		System.out.println("Done 0");
		for(int j=0;j<apktoolfiles.getItemCount();j++)
		{
			AndroidManifestParser.parse(apktoolfiles.getItem(j));
		}
		for(int j=0;j<tapktoolfiles.getItemCount();j++)
		{
			AndroidManifestParser.parse(tapktoolfiles.getItem(j));
		}
		SmaliParser1.smaliparse(apktoolfiles);
		SmaliParser1.smaliparse(tapktoolfiles);
		System.out.println("Done 1");
		for(int k=0,h=0;k<apktoolfiles.getItemCount();k++)
		//for(int k=0;k<6;k++)
		{
			train_X_perm[h++] = PermissionExtraction.permissionExtractor(apktoolfiles.getItem(k));
		}
		for(int k=0,h=0;k<tapktoolfiles.getItemCount();k++)
		//for(int k=0;k<6;k++)
		{
			test_X_perm[h++] = PermissionExtraction.permissionExtractor(tapktoolfiles.getItem(k));
		}
		System.out.println("Done 2");
		for(int k=0,h=0;k<apktoolfiles.getItemCount();k++)
		//for(int k=0;k<6;k++)
		{
			train_X_func[h++] = APIFeatureExtractor.apiFeatureExtractor(apktoolfiles.getItem(k));
		}
		for(int k=0,h=0;k<tapktoolfiles.getItemCount();k++)
		//for(int k=0;k<6;k++)
		{
			test_X_func[h++] = APIFeatureExtractor.apiFeatureExtractor(tapktoolfiles.getItem(k));
		}
		System.out.println("Done 3");
		for(int l=0;l<apktoolfiles.getItemCount();l++)
		{
			train_X[l] = combine(train_X_perm[l],train_X_func[l]);
		}
		for(int l=0;l<tapktoolfiles.getItemCount();l++)
		{
			test_X[l] = combine(test_X_perm[l],test_X_func[l]);
		}
		System.out.println("Done 4");
		for(int m=0;m<files.getItemCount();m++)
		{
			X = files.getItem(m);
			if(X.contains("{**malware**}"))
			{
				//train_Y[m] = {1,0};
				//Arrays.fill(train_Y[m][0],1);
				System.out.println(X);
				train_Y[m][0] = 1;
				train_Y[m][1] = 0;
			}
			else
			{
				train_Y[m][0] = 0;
				train_Y[m][1] = 1;
			}
		}
		for(int m=0;m<tfiles.getItemCount();m++)
		{
			X = tfiles.getItem(m);
			if(X.contains("{**malware**}"))
			{
				//train_Y[m] = {1,0};
				//Arrays.fill(train_Y[m][0],1);
				System.out.println(X);
				test_Y[m][0] = 1;
				test_Y[m][1] = 0;
			}
			else
			{
				test_Y[m][0] = 0;
				test_Y[m][1] = 1;
			}
		}
		System.out.println("Done 5");
		for(int x=0;x<train_X.length;x++)
		{
			for(int x1=0;x1<train_X[x].length;x1++)
			{
				System.out.print("["+train_X[x][x1]+"]");
			}
			System.out.println();
		}
		for(int x=0;x<test_X.length;x++)
		{
			for(int x1=0;x1<test_X[x].length;x1++)
			{
				System.out.print("["+test_X[x][x1]+"]");
			}
			System.out.println();
		}
		System.out.println("Done 6");
		for(int y=0;y<train_Y.length;y++)
		{
			for(int y1=0;y1<train_Y[y].length;y1++)
			{
				System.out.print("["+train_Y[y][y1]+"]");
			}
			System.out.println();
		}
		for(int y=0;y<test_Y.length;y++)
		{
			for(int y1=0;y1<test_Y[y].length;y1++)
			{
				System.out.print("["+test_Y[y][y1]+"]");
			}
			System.out.println();
		}
		System.out.println("Done 7");
		FileWriter fr = new FileWriter("/tmp/op/ml.arff");
		BufferedWriter br = new BufferedWriter(fr);
		br.write("@relation malware");
		br.newLine();
		br.write("@attribute android.permission.INTERNET NUMERIC");
		br.newLine();
		br.write("@attribute android.permission.READ_PHONE_STATE NUMERIC");
		br.newLine();
		br.write("@attribute android.permission.SEND_SMS NUMERIC");
		br.newLine();
		br.write("@attribute android.permission.READ_SMS NUMERIC");
		br.newLine();
		br.write("@attribute android.permission.RECEIVE_SMS NUMERIC");
		br.newLine();
		br.write("@attribute android.permission.RECORD_AUDIO NUMERIC");
		br.newLine();
		br.write("@attribute android.permission.READ_CONTACTS NUMERIC");
		br.newLine();
		br.write("@attribute Ljava/net/URL;->openConnection NUMERIC");
		br.newLine();
		//br.write("@attribute Landroid/content/ContentResolver;->query NUMERIC");
		//br.newLine();
		br.write("@attribute Landroid/telephony/TelephonyManager;->getSimSerialNumber NUMERIC");
		br.newLine();
		br.write("@attribute Landroid/telephony/TelephonyManager;->getLine1Number NUMERIC");
		br.newLine();
		br.write("@attribute Landroid/telephony/SmsManager;->sendTextMessage NUMERIC");
		br.newLine();
		br.write("@attribute Landroid/hardware/Camera;->open NUMERIC");
		br.newLine();
		br.write("@attribute Landroid/telephony/TelephonyManager;->getDeviceId NUMERIC");
		br.newLine();
		br.write("@attribute class {Malicious,Benign}");
		br.newLine();
		br.newLine();
		br.write("@data");
		br.newLine();
		br.newLine();
		//String Z = null;
		for(int a=0;a<train_X.length;a++)
		{
			for(int b=0;b<train_X[a].length;b++)
			{
				//Z = Arrays.toString(train_X[a]);
				System.out.print(train_X[a][b]);
				br.write(train_X[a][b]+",");
			}
			System.out.println();
			if(train_Y[a][0]==0)
				br.write("Benign");
			else
				br.write("Malicious");
			br.newLine();
		}
		br.flush();
		br.close();
		System.out.println("Done 8");
		//DBN()
		FileWriter fr2 = new FileWriter("/tmp/op/mltest.arff");
		BufferedWriter br2 = new BufferedWriter(fr2);
		br2.write("@relation malware");
		br2.newLine();
		br2.write("@attribute android.permission.INTERNET NUMERIC");
		br2.newLine();
		br2.write("@attribute android.permission.READ_PHONE_STATE NUMERIC");
		br2.newLine();
		br2.write("@attribute android.permission.SEND_SMS NUMERIC");
		br2.newLine();
		br2.write("@attribute android.permission.READ_SMS NUMERIC");
		br2.newLine();
		br2.write("@attribute android.permission.RECEIVE_SMS NUMERIC");
		br2.newLine();
		br2.write("@attribute android.permission.RECORD_AUDIO NUMERIC");
		br2.newLine();
		br2.write("@attribute android.permission.READ_CONTACTS NUMERIC");
		br2.newLine();
		br2.write("@attribute Ljava/net/URL;->openConnection NUMERIC");
		br2.newLine();
		//br2.write("@attribute Landroid/content/ContentResolver;->query NUMERIC");
		//br2.newLine();
		br2.write("@attribute Landroid/telephony/TelephonyManager;->getSimSerialNumber NUMERIC");
		br2.newLine();
		br2.write("@attribute Landroid/telephony/TelephonyManager;->getLine1Number NUMERIC");
		br2.newLine();
		br2.write("@attribute Landroid/telephony/SmsManager;->sendTextMessage NUMERIC");
		br2.newLine();
		br2.write("@attribute Landroid/hardware/Camera;->open NUMERIC");
		br2.newLine();
		br2.write("@attribute Landroid/telephony/TelephonyManager;->getDeviceId NUMERIC");
		br2.newLine();
		br2.write("@attribute class {Malicious,Benign}");
		br2.newLine();
		br2.newLine();
		br2.write("@data");
		br2.newLine();
		br2.newLine();
		for(int c=0;c<test_X.length;c++)
		{
			for(int d=0;d<test_X[c].length;d++)
			{
				System.out.print(test_X[c][d]);
				br2.write(test_X[c][d]+",");
			}
			System.out.println();
			if(test_Y[c][0]==0)
				br2.write("Benign");
			else
				br2.write("Malicious");
			br2.newLine();
		}
		br2.flush();
		br2.close();
		System.out.println("Done 9");
		mres = MachineLearningEngine.machineLearner("/tmp/op/ml.arff", "/tmp/op/mltest.arff",test_X.length);
		System.out.println("Done 10");
		dres = DBN.deepEngine(train_X, train_Y, test_X);
		System.out.println("Done 11");
		for(int mr=0;mr<mres.length;mr++)
		{
			for(int mr1=0;mr1<mres[mr].length;mr1++)
			{
				System.out.print(mres[mr][mr1]);
			}
			System.out.println();
		}
		for(int dr=0;dr<dres.length;dr++)
		{
			for(int dr1=0;dr1<dres[dr].length;dr1++)
			{
				System.out.print(dres[dr][dr1]);
			}
			System.out.println();
		}
		for(int c=0; c<test_Y.length;c++)
		{
			//System.out.println(mres[c].toString());
			System.out.println("Pred: "+Arrays.toString(mres[c]));
			//System.out.println(test_Y[c].toString());
			System.out.println("Act: "+Arrays.toString(test_Y[c]));
			if(((Arrays.toString(mres[c])).compareTo(Arrays.toString(test_Y[c])))==0)
			{
				mcor++;
			}
		}
		System.out.println(mcor);
		System.out.println(test_Y.length);
		System.out.print("Machine Learning Accuracy = ");
		double mac = ((double)mcor/(double)test_Y.length)*100;
		//System.out.println(((double)(mcor/(test_Y.length)))*100+"%");
		System.out.println(mac+"%");
		for(int c=0; c<test_Y.length;c++)
		{
			//System.out.println(mres[c].toString());
			System.out.println("Pred: "+Arrays.toString(dres[c]));
			//System.out.println(test_Y[c].toString());
			System.out.println("Act: "+Arrays.toString(test_Y[c]));
			if(((Arrays.toString(dres[c])).compareTo(Arrays.toString(test_Y[c])))==0)
			{
				dcor++;
			}
		}
		System.out.println(dcor);
		System.out.println(test_Y.length);
		System.out.print("Deep Learning Accuracy = ");
		double dac = ((double)dcor/(double)test_Y.length)*100;
		//System.out.println(((double)(dcor/test_Y.length))*100+"%");
		System.out.println(dac+"%");
		System.out.println("-----------------------------------------------------------------------------------");
		long endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println(((totalTime/1000)/60));
	}
	public static int[] combine(int[] a, int[] b){
        int length = a.length + b.length;
        int[] result = new int[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

}
