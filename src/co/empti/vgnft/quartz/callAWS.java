package co.empti.vgnft.quartz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class callAWS {

	// ȸ���� �� �Ǹűݾ�, ��÷��, 
	public static String getPrecipitation(String nx, String ny) throws IOException {
	
		String resPre = "0";
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
		SimpleDateFormat format2 = new SimpleDateFormat ( "HH");

		Date time = new Date();

		String today = format1.format(time);
		String nowtime = format2.format(time);
		
		System.out.println("today : " + today);
		System.out.println("time : " + nowtime);
		
		String req = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		
	//	Strng param1 = "?serviceKey=";
		String param1 = "?serviceKey=PFSrGHovnKpmb219PIpyMtHqaofxCFWWOiPYcgdH6URXSCqe1mSapF5%2BUIQ1IyTw6G74DmmTMyZHeiQ7oRmPkw%3D%3D";
		
		String param2 = "&pageNo=1";
		String param3 = "&numOfRows=1";

		String param4 = "&nx=" + nx;
		String param5 = "&ny=" + ny;
		
		String param6 = "&base_date=" + today;
		
		String[] date = { "0200", "0500", "0800", "1100", "1400", "1700", "2000", "2300"};
		int[] date2 = { 2, 5, 8, 11, 14, 17, 20, 23 };
		
		int flag = 0;
		
		System.out.println("data2.length : " + date2.length);
		
		for(int i=0; i < date2.length ; i++){
			if ( Integer.parseInt(nowtime) <= date2[i] ){
				flag = i-1;
				break;
			}
		}
		
		System.out.println("nowtime : " + date[flag]);
		System.out.println("flag : " + flag);
		
		String param7 = "&base_time=" + date[flag];
		String param8 = "&dataType=json";
			
		URL url = new URL(req+param1+param2+param3+param4+param5+param6+param7+param8);
		
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
	    
		System.out.println("\nSending 'GET' request to URL : " + url);
	    System.out.println("Response Code : " + responseCode);
		
		JSONParser parser = new JSONParser();

		try (BufferedReader in = new BufferedReader(
			
	        new InputStreamReader(con.getInputStream()))) {

	        StringBuilder res = new StringBuilder();
	        String line;

	        while ((line = in.readLine()) != null) {
	            res.append(line);
	        }
	        
			JSONObject jsonObject = (JSONObject) parser.parse(res.toString());
	        
			JSONObject jobj1 = (JSONObject) jsonObject.get("response");
			
			System.out.println("response : " + jobj1);
			
	        JSONObject jobj2 = (JSONObject) jobj1.get("body");
	        
	        System.out.println("body : " + jobj2);
	        
	        JSONObject jobj3 = (JSONObject) jobj2.get("items");
	        
	        System.out.println("items : " + jobj3);
	        
	        JSONArray arr = (JSONArray)jobj3.get("item");
	        
	        System.out.println("item size : " + arr.size());
	        
	        JSONObject tmp = null;
	        
			for(int i=0;i<arr.size();i++){
	        	
				tmp = (JSONObject)arr.get(i);//�ε��� ��ȣ�� �����ؼ� �����´�.
				
				String check = (String)tmp.get("category");
				
				String fcstValue = ""; 
						
				if ("POP".equals(check)){
					
					fcstValue = (String)tmp.get("fcstValue");	
					resPre = fcstValue;
				}
							
			//	System.out.println("----- "+i+"��° �ε��� �� -----");
				
			//	System.out.println("���� : "+ num);
				System.out.println("����Ȯ�� : "+ fcstValue);
				return resPre.toString().trim();
				
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return resPre;

	}
	
}
