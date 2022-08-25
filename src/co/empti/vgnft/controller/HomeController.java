package co.empti.vgnft.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import co.empti.vgnft.util.Logger;
import co.empti.vgnft.util.MakeFile;
import co.empti.vgnft.vo.vgnVO;
import co.empti.vgnft.db.vgnDAO;
import co.empti.vgnft.util.Global;

public class HomeController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -2294711188366143277L;
	
	protected String getClientIP(HttpServletRequest request) throws ServletException, IOException {
		String result = request.getHeader("WL-Proxy-Client-IP");
		if( result == null || "".equals(result.trim()) ) {
			result = request.getRemoteAddr();
		}
		return result;
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
				
		RequestDispatcher dispatcher = null;
		
		try {
		
			Logger.log(Logger.LOG_ERROR, "-------test1 : " + request.getParameter("STEP").toString());
			
			vgnDAO rdb = new vgnDAO();
			vgnVO vvo = new vgnVO();
			HashMap hm = new HashMap();
			
			boolean check;
			
			StringBuffer res;
			
			JSONObject json = new JSONObject();
			String res1 = json.toJSONString();
			
			PrintWriter out = response.getWriter();
			
			String step = request.getParameter("STEP").toString();
						
			switch (request.getParameter("STEP").toString())
			{
			
				//*** Case 0 : agreement check form			
				case "VGN0000" : //otp make and response
					
					request.setCharacterEncoding("utf-8");
			        response.setContentType("text/html; charset=utf-8");
					
					Logger.log(Logger.LOG_ERROR, "----------  STEP   		: " + request.getParameter("STEP").toString() + "----------------------");
					
					System.out.println("step : " + request.getParameter("STEP").toString());
					System.out.println("account : " + request.getParameter("account").toString());
					System.out.println("vgncnt : " + request.getParameter("vgncnt").toString());
					
					vvo = new vgnVO();
					
					vvo.setUuid( (request.getParameter("account")==null)? "" : request.getParameter("account") );
					vvo.setVgncnt( (request.getParameter("vgncnt")==null)? "" : request.getParameter("vgncnt") );
					vvo.setPfruta("0");
					
					
					
					if( "".equals(vvo.getUuid()) ){
						out.print("unusable");
						
					}
					
					;
					
					if (rdb.SelectUser(vvo) <1)  
					
						check = rdb.insertUsrData(vvo);
					
					out.print("usable");
					
					return;
					
				//*** Case 1 : 1day check 			
				case "VGN0010" : //otp make and response
					
					request.setCharacterEncoding("utf-8");
			        response.setContentType("text/html; charset=utf-8");
					
					Logger.log(Logger.LOG_ERROR, "----------  STEP   		: " + request.getParameter("STEP").toString() + "----------------------");
					
					System.out.println("step : " + request.getParameter("STEP").toString());
					System.out.println("account : " + request.getParameter("account").toString());
					System.out.println("vgncnt : " + request.getParameter("vgncnt").toString());
					
					vvo = new vgnVO();
					
					vvo.setUuid( (request.getParameter("account")==null)? "" : request.getParameter("account") );
					vvo.setVgncnt( (request.getParameter("vgncnt")==null)? "" : request.getParameter("vgncnt") );
					vvo.setPfruta("0");
					
					
					
					if( "".equals(vvo.getUuid()) ){
						out.print("unusable");
						
					}
					
					;
					
					if (rdb.SelectUser(vvo) <1)  
					
						check = rdb.insertUsrData(vvo);
					
					out.print("usable");
					
					return;
					
					
				case "VGN0001" : //otp make and response
					
					request.setCharacterEncoding("euc-kr");
					
					Logger.log(Logger.LOG_ERROR, "----------  STEP   		: " + request.getParameter("STEP").toString() + "----------------------");
					/*
					vgnVO rvo = new vgnVO();
					// null ó�� �ʿ���
					
					rvo.setLatitude( (request.getParameter("LAT")==null)? "" : request.getParameter("LAT") );
					rvo.setLongitude( (request.getParameter("LON")==null)? "" : request.getParameter("LON") );
					rvo.setFreq(request.getParameter("FRQ"));
					rvo.setDbm(request.getParameter("DBM"));
					rvo.setWeather(request.getParameter("WEA"));
					
					
					Logger.log(Logger.LOG_ERROR, "----------  uuid		: " +request.getParameter("UUID").toString() 		+ "----------------------");
					Logger.log(Logger.LOG_ERROR, "----------  ct		: " +request.getParameter("CT").toString()		+ "----------------------");
					Logger.log(Logger.LOG_ERROR, "----------  brand		: " +request.getParameter("BRA").toString() 	    + "----------------------");
					Logger.log(Logger.LOG_ERROR, "----------  model		: " +request.getParameter("MOD").toString()		+ "----------------------");
					Logger.log(Logger.LOG_ERROR, "----------  carrier		: " +request.getParameter("CAR").toString()		+ "----------------------");
		
					
					String Pre = MakeFile.ReadCacheFile();
					
					if("�����ֽ� ������".equals(request.getParameter("ADDR").toString())) {
						Pre = MakeFile.ReadCacheFile2();
					}
					
					Logger.log(Logger.LOG_ERROR, "----------  Precipitation		: " + Pre + " ----------------------");
					
					rvo.setLatitude(request.getParameter("LAT"));
					rvo.setLongitude(request.getParameter("LON"));
					rvo.setFreq(request.getParameter("FRQ"));
					rvo.setDbm(request.getParameter("DBM"));
					rvo.setWeather(request.getParameter("WEA"));
					

					
					boolean check = rdb.insertRVData(rvo);
					
					Logger.log(Logger.LOG_ERROR, "----------  Insert data res : " + check  + " ----------");
					*/
					
					check = true;
					
					res = new StringBuffer();
										
				//	int cnt = rdb.SelectCount();
					int cnt = 0;
					JSONParser parser = new JSONParser();
					
				//	JSONObject json = new JSONObject();
					
					if(check) {
						
						res.append("RESCD=0");
						res.append("&COUNT=0");
						res.append("&END");
						
						json.put("RESCD", 0);
						json.put("COUNT", cnt);
						
				//		String res1 = json.toJSONString();
						
						Logger.log(Logger.LOG_ERROR, "----------  response data : " + res1.toString()  + " ----------");
						
						request.setAttribute("RES", res1.toString());
						
						dispatcher = getServletContext().getRequestDispatcher("/jsp/cli_res.jsp");
						dispatcher.forward(request, response);
						return;

					} else {
						
						res.append("RESCD=1");
						res.append("&COUNT=" + cnt);
						res.append("&END");
						
						json.put("RESCD", 1);
						json.put("COUNT", Integer.toString(cnt));
						
						res1 = json.toJSONString();
						
						Logger.log(Logger.LOG_ERROR, "----------  response data : " + res1.toString()  + " ----------");
						
						request.setAttribute("RES", res1.toString());
						
						
						//test
						//JSONObject jsonObject = (JSONObject) parser.parse(res1);
						
						
						dispatcher = getServletContext().getRequestDispatcher("/jsp/cli_res.jsp");
						dispatcher.forward(request, response);
						return;

					}
					
				case "100" : //otp make and response
					
					request.setCharacterEncoding("euc-kr");
										
					Logger.log(Logger.LOG_ERROR, "----------  STEP   		: " + request.getParameter("STEP").toString() + "----------------------");
					
					json = new JSONObject();
					
					json.put("RESCD", 1);
					
					res1 = json.toJSONString();
					
					Logger.log(Logger.LOG_ERROR, "----------  response data : " + res1.toString()  + " ----------");
					
					request.setAttribute("RES", res1.toString());
					
					dispatcher = getServletContext().getRequestDispatcher("/jsp/cli_res.jsp");
					dispatcher.forward(request, response);
					return;
								
				default :
					
					Logger.log(Logger.LOG_ERROR, "step error:" + request.getParameter("STEP") +", unknown step.");
					
					out.print("ERROR");
			}
			
		} catch (IOException ioe){
			Logger.log(Logger.LOG_ERROR, "------ IOException : " + ioe);
			/*
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp?err=5");
			dispatcher.forward(request, response);								
			return;
			*/
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			/*
			Logger.log(e);
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);								
			
			*/
			Logger.log(Logger.LOG_ERROR, "------ Exception : " + e);
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
		} 
	}
	
}

	
	
