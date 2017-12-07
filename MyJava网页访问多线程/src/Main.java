import java.util.*;
import java.net.*;
import java.io.*;

public class Main
{
	public static void main(String args[]) throws Exception {
		
		//Map<String,String> pp =new HashMap<String,String>();
		//pp.put("id","7232589d67834df39116c9fdc178fd8d");
		//HttpURLConnection conn = (HttpURLConnection) HttpRequestUtil
			//.sendGetRequest(
			//"http://api.mokucloud.com/web-url/shared/to",
			//pp, null);
		//int code = conn.getResponseCode();
		//http://api.mokucloud.com/web-url/shared/to?id=091523691ef24342ba076f6b3407c882
		//System.out.println(code);
		//InputStream in = conn.getInputStream();
		//String doat = HttpRequestUtil.read2String(in);
		//System.out.println(doat);
		for(int i=0;i<10;i++)
		new JcThread(10,i+1).start();
	}
	
}
