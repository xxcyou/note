
import java.util.*;
import java.text.*;
import java.security.*;
import java.io.*;
import java.net.*;
public class Tencent_ai
{
	private static final String app_id = "1106540576";//应用ID
	private static final String app_key = "iiPQ1527nH0MP31V";//应用密匙
	//app_id=10000&nonce_str=20e3408a79&text=%E8%85%BE%E8%AE%AF%E5%BC%80%E6%94%BE%E5%B9%B3%E5%8F%B0&time_stamp=1493449657&app_key=a95eceb1ac8c24ee28b70f7dbba912bf
	private static String Random_string(){
		String str = "";
        Random random = new Random();
		
        for (int i = 0; i < 12; i++) {
            boolean b = random.nextBoolean();
            if (b) { // 字符串
                // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
                str += (char) (97 + random.nextInt(26));// 取得大写字母
            } else { // 数字
                str += String.valueOf(random.nextInt(10));
            }
        }
        return str;
	}
	private static String Timestamp () {
		return Calendar.getInstance().getTimeInMillis()/1000+"";
	}
	public static String Ai_Web_site (String Web_site ,Map<String,String> Replace){
		final String Random_string = Random_string();//随机字符串12个
		final String Timestamp = Timestamp();//时间戳10位
		String Temporary_Web_site = Web_site;
		String Temporary_Web_site2;
		String[] Partitioned_array;
		String Temporary_="";
		StringBuilder Temporary_String = new StringBuilder();
		
		//第一步替换基本内容
		Partitioned_array= Temporary_Web_site.split("&");
		for(String evw : Replace.keySet()){
			for(int i=0;i<Partitioned_array.length;i++){
				if(Partitioned_array[i].indexOf(evw+"=")!=-1){
					Partitioned_array[i]=evw+"="+Replace.get(evw);
				}
				if(Partitioned_array[i].indexOf("app_id=")!=-1){
					Partitioned_array[i]="app_id="+app_id;
				}
				if(Partitioned_array[i].indexOf("nonce_str=")!=-1){
					Partitioned_array[i]="nonce_str="+Random_string;
				}
				if(Partitioned_array[i].indexOf("time_stamp=")!=-1){
					Partitioned_array[i]="time_stamp="+Timestamp;
				}
				if(Partitioned_array[i].indexOf("sign=")!=-1){
					Temporary_=Partitioned_array[i];
				}
			}
		}
		for (int i = 0; i < Partitioned_array.length; i++) {
			
			if(Partitioned_array.length == i+1){
				Temporary_String.append(Partitioned_array[i]);
			}else{
				Temporary_String.append( Partitioned_array[i]).append( "&");
			}
		}
		Temporary_Web_site=Temporary_String.toString();
		Temporary_Web_site2 = Temporary_Web_site.replace("&"+Temporary_,"");
		Partitioned_array = Temporary_Web_site2.split("&");
		for (int i = 0; i < Partitioned_array.length-1; i++) {
			for (int j = i+1; j < Partitioned_array.length; j++) {
				int intTemp = Partitioned_array[i].compareToIgnoreCase(Partitioned_array[j]);
				String strTemp = "";
				if(intTemp>0){
					strTemp = Partitioned_array[j];
					Partitioned_array[j] = Partitioned_array[i];
					Partitioned_array[i] = strTemp;
				}
			}
		}
		Temporary_String=new StringBuilder();
		for (int i = 0; i < Partitioned_array.length; i++) {
			if(Partitioned_array.length == i+1){
				Temporary_String.append( Partitioned_array[i]).append("&app_key=").append(app_key);
			}else{
				Temporary_String.append( Partitioned_array[i]).append( "&");
			}
		}
		Temporary_Web_site2 = Temporary_String.toString();
		Temporary_Web_site = Temporary_Web_site.replace(Temporary_,"sign="+stringMD5(Temporary_Web_site2));
		return Temporary_Web_site; 
	}
	public static String stringMD5(String input) {  //MD5加密
		try {  
			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）  
			MessageDigest messageDigest =MessageDigest.getInstance("MD5");  
			// 输入的字符串转换成字节数组  
			byte[] inputByteArray = input.getBytes();  
			// inputByteArray是输入字符串转换得到的字节数组  
			messageDigest.update(inputByteArray);  
			// 转换并返回结果，也是字节数组，包含16个元素  
			byte[] resultByteArray = messageDigest.digest();  
			// 字符数组转换成字符串返回  
			return byteArrayToHex(resultByteArray);  
		} catch (NoSuchAlgorithmException e) {  
			return null;  
		}  
	}
	private static String byteArrayToHex(byte[] byteArray) {  
		// 首先初始化一个字符数组，用来存放每个16进制字符  
		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };  
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））  
		char[] resultCharArray =new char[byteArray.length * 2];  
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去  
		int index = 0;  
		for (byte b : byteArray) {  
			resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];  
			resultCharArray[index++] = hexDigits[b& 0xf];  
		}  
		// 字符数组组合成字符串返回  
		return new String(resultCharArray);  
	}
	public static String getUTF8XMLString(String xml){//UTF8编码
		// A StringBuffer Object
		StringBuffer sb = new StringBuffer();
		sb.append(xml);
		String xmString = "";
		String xmlUTF8="";
		try {
			xmString = new String(sb.toString().getBytes("UTF-8"));
			xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return to String Formed
		return xmlUTF8;
	}
	public static String subString(String string/*原字符串*/, String str1/*前字符串*/, String str2/*后字符串*/){//取字符串中间
		//记录特定字符str1、str2索引
		int beginIndex = string.indexOf(str1);
		int endIndex = string.indexOf(str2);
		//索引<0，表示字符串中不存在字符，return
		if(beginIndex < 0){
			return "[" + string + "]" + " 中不存在 " + str1 + "，无法截取";
		} else if(endIndex < 0){
			return "[" + string + "]" + " 中不存在 " + str2 + "，无法截取";
		}
		//参数str1 str2位置可调换，不管str1 str2在字符串中的位置顺序如何，都能截取它们之间的字符串
		if(beginIndex > endIndex){
			int tempIndex = beginIndex;
			beginIndex = endIndex;
			endIndex = tempIndex;
			String tempStr = str1;
			str1 = str2;
			str2 = tempStr;
		}
		//截取 第一个substring()方法包含边界字符str1或str2,为了使截取结果不包含边界，用第二个substring()方法去掉边界字符
		String result = string.substring(beginIndex, endIndex).substring(str1.length());
		return result;
	}
	public static void Both_sides (String string,String key,String Left,String right){//取关键字两边，返回2数组
		int abc=string.indexOf(key);
		Left = string.substring(0,abc);
		right = string.substring(abc+key.length(),string.length());
	}
}
