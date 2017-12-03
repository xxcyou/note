import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import org.json.*;

public class Main
{
	public static void main(String[] args) throws JSONException
	{
		
//https://api.ai.qq.com/fcgi-bin/nlp/nlp_texttrans?app_id=1000001&time_stamp=1493468759&nonce_str=fa577ce340859f9fe&sign=B250148B284956EC5218D4B0503E7F8A&type=0&text=%E4%BB%8A%E5%A4%A9%E5%A4%A9%E6%B0%94%E6%80%8E%E4%B9%88%E6%A0%B7'
		while(true){
		String str="";
		System.out.println("Welcome to the translation interface.");

		Scanner input = new Scanner(System.in);

		System.out.print("Content to be translated : ");
		String number1 = input.nextLine();
		str=translation(number1);
		JSONObject json=new JSONObject(str);
			System.out.println(json.getString("ret")+"\n");
			System.out.println(json.getString("msg")+"\n");
			System.out.println(json.getString("data")+"\n");
			System.out.println(json.getJSONObject("data").getString("type")+"\n");
			System.out.println(json.getJSONObject("data").getString("org_text")+"\n");
			System.out.println(json.getJSONObject("data").getString("trans_text")+"\n");
		
		
		
		
		System.out.printf("The result of translation :"+json.getJSONObject("data").getString("trans_text")+"\n");
		}
		
	}
	
	public static String translation (String string){
		String ip="app_id=1000001&time_stamp=1493468759&nonce_str=fa577ce340859f9fe&sign=B250148B284956EC5218D4B0503E7F8A&type=0&text=%E4%BB%8A%E5%A4%A9%E5%A4%A9%E6%B0%94%E6%80%8E%E4%B9%88%E6%A0%B7";
		Map<String,String> pp = new HashMap<String,String>();
		pp.put("type","0");
		pp.put("text",Tencent_ai.getUTF8XMLString(string));
		String s=HttpRequest.sendGet("https://api.ai.qq.com/fcgi-bin/nlp/nlp_texttrans?",Tencent_ai.Ai_Web_site(ip,pp),"");
		return s;
	}
	
}
