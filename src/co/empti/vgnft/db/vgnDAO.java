package co.empti.vgnft.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.empti.vgnft.util.Config;
import co.empti.vgnft.util.Logger;
import co.empti.vgnft.vo.vgnVO;

public class vgnDAO {
	/*
	String DB_URL = "jdbc:mysql://127.0.0.1:3306/rainvow?autoReconnect=true&allowMultiQueries=true&characterEncoding=UTF-8";
	String DB_USER = "boxads";
	String DB_PASSWORD= "!234Qwer";
	*/
	String DB_URL = "jdbc:mysql://mvon-db1.cyfuzg9fme7f.ap-northeast-2.rds.amazonaws.com/vgnft?autoReconnect=true&allowMultiQueries=true&characterEncoding=UTF-8";
	String DB_USER = "vgndb";
	String DB_PASSWORD= "1234qwer";
	
	public vgnDAO(){
		super();
	}

	public Connection conn = null;
	public String msg ="";
	int rscnt = 0;
	
	public int SelectUser(vgnVO vvo) throws Exception
	{

		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		
		int cnt = 0;

		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			sb.append("SELECT COUNT(*) FROM mining_usr WHERE usr_addr = ? ");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, vvo.getUuid() );

			rs = pstmt.executeQuery();

		//	Logger.log(Logger.LOG_ERROR, "Select Count() : " + rs);
			
			if (rs.next()) {
				
				Logger.log(Logger.LOG_ERROR, "---------- Select Count() : " + rs.getString("COUNT(*)") + " ----------");
				
				cnt = Integer.parseInt(rs.getString("COUNT(*)"));
			}
			else
			{
				msg = "Select Count() = 0";
				Logger.log(Logger.LOG_ERROR, "dblog : " + msg);
			}

		}catch(SQLException ex){
		
			Logger.log(Logger.LOG_ERROR, "dblog : Select Count() : " + ex.getSQLState().toString() + ex.getMessage().toString());
			
		}finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
		}

		return cnt;
	}
	
	public boolean insertUsrData(vgnVO vvo) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			StringBuffer sb = new StringBuffer();
			
			sb.append("Insert Into mining_usr");
			sb.append("( usr_addr )");
			sb.append(" Values ");
			sb.append("( ? )");
			
			pstmt = conn.prepareStatement( sb.toString() );
			
			
			pstmt.setString( 1, vvo.getUuid() 		);
	//		pstmt.setString( 2, vvo.getVgncnt()		);
	//		pstmt.setString( 3, vvo.getPfruta()		);

			pstmt.execute();
			
			return true;
			
		} catch (Exception e) {
			Logger.log(Logger.LOG_ERROR, e.toString());
			return false;
			
		} finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
			
		}
	}
	
	public boolean insertUsrVgn(vgnVO vvo) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			StringBuffer sb = new StringBuffer();
			
			sb.append("Insert Into vgn_history");
			sb.append("( usr_addr, usr_vgncnt )");
			sb.append(" Values ");
			sb.append("( ?, ? )");
			
			pstmt = conn.prepareStatement( sb.toString() );
			
			pstmt.setString( 1, vvo.getUuid() 		);
			pstmt.setString( 2, vvo.getVgncnt()		);
	//		pstmt.setString( 3, vvo.getPfruta()		);

			pstmt.execute();
			
			return true;
			
		} catch (Exception e) {
			Logger.log(Logger.LOG_ERROR, e.toString());
			return false;
			
		} finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
			
		}
	}
	
	public boolean insertUsrPfru(vgnVO vvo) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			StringBuffer sb = new StringBuffer();
			
			sb.append("Insert Into pfru_history");
			sb.append("( usr_addr, usr_pfrucnt )");
			sb.append(" Values ");
			sb.append("( ?, ? )");
			
			pstmt = conn.prepareStatement( sb.toString() );
						
			pstmt.setString( 1, vvo.getUuid() 		);
			pstmt.setString( 2, vvo.getVgncnt()		);
	//		pstmt.setString( 2, vvo.getPfruta()		);

			pstmt.execute();
			
			return true;
			
		} catch (Exception e) {
			Logger.log(Logger.LOG_ERROR, e.toString());
			return false;
			
		} finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
			
		}
	}
	
	public boolean useUsrPfru(vgnVO vvo) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			StringBuffer sb = new StringBuffer();
			
			sb.append("Insert Into pfru_history");
			sb.append("( usr_addr, usr_pfrucnt, tx_hash )");
			sb.append(" Values ");
			sb.append("( ?, ?, ? )");
			
			pstmt = conn.prepareStatement( sb.toString() );
						
			pstmt.setString( 1, vvo.getUuid() 		);
			pstmt.setString( 2, vvo.getPfruta()		);
			pstmt.setString( 3, vvo.getPfruta()		);
	//		pstmt.setString( 2, vvo.getPfruta()		);

			pstmt.execute();
			
			return true;
			
		} catch (Exception e) {
			Logger.log(Logger.LOG_ERROR, e.toString());
			return false;
			
		} finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
			
		}
	}
	
	
/*	
	public int SelectCount() throws Exception
	{
		/*
		if ( cardNo == null ) {
			msg ="Select Cardno : Cardno is null  ";
			Logger.log(Logger.LOG_ERROR, "dblog : " + msg);
			return 0;
		}
		//

		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		
		int cnt = 0;

		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			sb.append("SELECT COUNT(*) FROM dcollect ");

			pstmt = conn.prepareStatement(sb.toString());

		//	pstmt.setString(1, cardNo);

			rs = pstmt.executeQuery();

		//	Logger.log(Logger.LOG_ERROR, "Select Count() : " + rs);
			
			if (rs.next()) {
				
				Logger.log(Logger.LOG_ERROR, "---------- Select Count() : " + rs.getString("COUNT(*)") + " ----------");
				
				cnt = Integer.parseInt(rs.getString("COUNT(*)"));
			}
			else
			{
				msg = "Select Count() = 0";
				Logger.log(Logger.LOG_ERROR, "dblog : " + msg);
			}

		}catch(SQLException ex){
		
			Logger.log(Logger.LOG_ERROR, "dblog : Select Count() : " + ex.getSQLState().toString() + ex.getMessage().toString());
		}finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
		}

		return cnt;
	}
	
	public boolean insertRVData(vgnVO rvo) throws Exception {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			StringBuffer sb = new StringBuffer();
			/*
			sb.append("Insert Into test2");
			sb.append("( latitude, longitude, freq, cdmaDbm, cdmaEcio, evdoDbm, evdoEcio, evdoSnr, gsmStr, weather, temp, hum, atm, addr )");
			sb.append(" Values ");
			sb.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
			//
			sb.append("Insert Into dcollect");
			sb.append("( uuid, brand, model, carrier, mcc, "
						+ "mnc, ct, cid, nodeB, lci, "
						+ "pci, asu, earfcn, latitude, longitude, "
						+ " freq, dbm, weather, temp, hum,"
						+ " atm, deg, speed, addr, Precipitation )");
			sb.append(" Values ");
			sb.append("( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement( sb.toString() );
			
			
			pstmt.setString( 1, rvo.getUuid() 		);
			pstmt.setString( 2, rvo.getBrand()		);
			pstmt.setString( 3, rvo.getModel()		);
			pstmt.setString( 4, rvo.getCarrier()		);
			pstmt.setString( 5, rvo.getMcc()		);

			pstmt.setString( 6, rvo.getMnc()		);
			pstmt.setString( 7, rvo.getCt()		);
			pstmt.setString( 8, rvo.getCid()		);
			pstmt.setString( 9, rvo.getNodeB()		);
			pstmt.setString( 10, rvo.getLci()		);

			pstmt.setString( 11, rvo.getPci()		);
			pstmt.setString( 12, rvo.getAsu()		);
			pstmt.setString( 13, rvo.getEarfcn()		);
			pstmt.setString( 14, rvo.getLatitude()		);
			pstmt.setString( 15, rvo.getLongitude()		);

			pstmt.setString( 16, rvo.getFreq()		);
			pstmt.setString( 17, rvo.getDbm()		);
			pstmt.setString( 18, rvo.getWeather()		);
			pstmt.setString( 19, rvo.getTemp() 		);
			pstmt.setString( 20, rvo.getHum()		);

			pstmt.setString( 21, rvo.getAtm()		);
			pstmt.setString( 22, "0"		);
			pstmt.setString( 23, "0"		);
			pstmt.setString( 24, rvo.getAddr()		);
			pstmt.setString( 25, rvo.getPrecip()		);
			
			pstmt.execute();
			
			return true;
			
		} catch (Exception e) {
			Logger.log(Logger.LOG_ERROR, e.toString());
			return false;
			
		} finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
			
		}
	}
	
	
	public HashMap SelectWhetherInfo(String addr) throws Exception
	{
				
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmm");
		
		Date time = new Date();
		
		Date time1 = new Date(System.currentTimeMillis() - 3600 * 1000);
				
		int[] timearr = {0 , 500, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000};
		String[] timearr2 = new String[13];
		
		int i = 0;
		
		String flag = null;
		
		for(i = 0; i < 13 ; i++) {
			
			System.out.println( "-------STEP : SelectWhetherInfo timearr : "+ timearr[i] + " ----------");
			time = new Date(System.currentTimeMillis() - 3600 * timearr[i]);
			flag = format1.format(time);
			timearr2[i] = flag;
			
			System.out.println( "-------STEP : SelectWhetherInfo timearr2 : "+ timearr2[i] + " ----------");
		
		}
		
		HashMap hm = new HashMap();
		vgnVO rvo = null;
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			for(i = 0; i < 13 ; i++) {
			
				sb = new StringBuffer();
				
				System.out.println("--- addr : " + addr);
				System.out.println("--- timearr2["+ i +"] : " + timearr2[i]);
				
			//	sb.append("SELECT regdate, addr, temp, speed, Precipitation FROM dcollect Where addr = ? and DATE_FORMAT(regdate, '%Y%m%d%H%i') = ?");
			//	sb.append("SELECT regdate, addr, temp, speed, Precipitation FROM dcollect Where addr = ? and regdate"
			//			+ "BETWEEN STR_TO_DATE ( '"+ timearr2[i] +"00' , '%Y%m%d%H%i%s') and STR_TO_DATE ( '"+ timearr2[i] + "59' ,'%Y%m%d%H%i%s')");			
				
				sb.append("SELECT regdate, latitude, longitude, addr, temp, speed, Precipitation FROM dcollect Where addr = ? and regdate BETWEEN STR_TO_DATE ( ? , '%Y%m%d%H%i%s') and STR_TO_DATE ( ? ,'%Y%m%d%H%i%s')");
				
				pstmt = conn.prepareStatement(sb.toString());
	
				pstmt.setString(1, addr);
				pstmt.setString(2, timearr2[i] + "00" );
				pstmt.setString(3, timearr2[i] + "59" );
	
				rs = pstmt.executeQuery();
	
			//	Logger.log(Logger.LOG_ERROR, "Select Count() : " + rs);
				
				if (rs.next()) {
					
					rvo = new vgnVO();
					
					Logger.log(Logger.LOG_ERROR, "---------- Select i		 	: " + i + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select regdate		 	: " + rs.getString("regdate") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select latitude		: " + rs.getString("latitude") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select longitude		: " + rs.getString("longitude") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select addr		 	: " + rs.getString("addr") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select temp		 	: " + rs.getString("temp") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select speed		 	: " + rs.getString("speed") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select Precipitation 	: " + rs.getString("Precipitation") + " ----------");
					
					rvo.setDate(		rs.getString("regdate"));
					rvo.setLatitude(	rs.getString("latitude"));
					rvo.setLongitude(	rs.getString("longitude"));
					rvo.setAddr( 		rs.getString("addr"));
					rvo.setTemp(		rs.getString("temp"));
					rvo.setSpeed(		rs.getString("speed"));
					rvo.setPrecip(		rs.getString("Precipitation"));
					
					hm.put(i, rvo);
				}
				else
				{
					msg = "Select Count() = 0";
					Logger.log(Logger.LOG_ERROR, "dblog : " + msg);
					
					Logger.log(Logger.LOG_ERROR, "---------- Select i		 	: " + i + " ----------");
					hm.put(i, rvo);
				}
			}
			
			return hm;

		}catch(SQLException ex){
		
			Logger.log(Logger.LOG_ERROR, "dblog : SQLException : " + ex.getSQLState().toString() + ex.getMessage().toString());
			
		}finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
		}

		return hm;
	}
	
	public HashMap SelectWhetherInfo1(String addr) throws Exception
	{
				
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "20200626HHmm");
		
		Date time = new Date();
		
		Date time1 = new Date(System.currentTimeMillis() - 3600 * 1000);
				
		int[] timearr = { 0, 500, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000};
		String[] timearr2 = new String[13];
		
		int i = 0;
		
		String flag = null;
		
		for(i = 0; i < 13 ; i++) {
			
			System.out.println( "-------STEP : SelectWhetherInfo1 timearr : "+ timearr[i] + " ----------");
			time = new Date(System.currentTimeMillis() - 3600 * timearr[i]);
			flag = format1.format(time);
			timearr2[i] = flag;
			
			System.out.println( "-------STEP : SelectWhetherInfo1 timearr2 : "+ timearr2[i] + " ----------");
		
		}
		
		HashMap hm = new HashMap();
		vgnVO rvo = null;
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			for(i = 0; i < 13 ; i++) {
			
				sb = new StringBuffer();
				
				System.out.println("--- addr : " + addr);
				System.out.println("--- timearr2["+ i +"] : " + timearr2[i]);
				
			//	sb.append("SELECT regdate, addr, temp, speed, Precipitation FROM dcollect Where addr = ? and DATE_FORMAT(regdate, '%Y%m%d%H%i') = ?");
			//	sb.append("SELECT regdate, addr, temp, speed, Precipitation FROM dcollect Where addr = ? and regdate"
			//			+ "BETWEEN STR_TO_DATE ( '"+ timearr2[i] +"00' , '%Y%m%d%H%i%s') and STR_TO_DATE ( '"+ timearr2[i] + "59' ,'%Y%m%d%H%i%s')");			
				
				sb.append("SELECT regdate, latitude, longitude, addr, temp, speed, Precipitation FROM dcollect2 Where regdate BETWEEN STR_TO_DATE ( ? , '%Y%m%d%H%i%s') and STR_TO_DATE ( ? ,'%Y%m%d%H%i%s')");
				
				pstmt = conn.prepareStatement(sb.toString());
	
			//	pstmt.setString(1, addr);
				pstmt.setString(1, timearr2[i] + "00" );
				pstmt.setString(2, timearr2[i] + "59" );
	
				rs = pstmt.executeQuery();
	
			//	Logger.log(Logger.LOG_ERROR, "Select Count() : " + rs);
				
				if (rs.next()) {
					
					rvo = new vgnVO();
					
					Logger.log(Logger.LOG_ERROR, "---------- Select i		 	: " + i + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select regdate		 	: " + rs.getString("regdate") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select latitude		: " + rs.getString("latitude") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select longitude		: " + rs.getString("longitude") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select addr		 	: " + rs.getString("addr") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select temp		 	: " + rs.getString("temp") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select speed		 	: " + rs.getString("speed") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select Precipitation 	: " + rs.getString("Precipitation") + " ----------");
					
					rvo.setDate(		rs.getString("regdate"));
					rvo.setLatitude(	rs.getString("latitude"));
					rvo.setLongitude(	rs.getString("longitude"));
					rvo.setAddr( 		rs.getString("addr"));
					rvo.setTemp(		rs.getString("temp"));
					rvo.setSpeed(		rs.getString("speed"));
					rvo.setPrecip(		rs.getString("Precipitation"));
					
					hm.put(i, rvo);
				}
				else
				{
					msg = "Select Count() = 0";
					Logger.log(Logger.LOG_ERROR, "dblog : " + msg);
					
					Logger.log(Logger.LOG_ERROR, "---------- Select i		 	: " + i + " ----------");
					hm.put(i, rvo);
				}
			}
			
			return hm;

		}catch(SQLException ex){
		
			Logger.log(Logger.LOG_ERROR, "dblog : SQLException : " + ex.getSQLState().toString() + ex.getMessage().toString());
			
		}finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
		}

		return hm;
	}

	public HashMap SelectWhetherInfo2(String addr) throws Exception
	{
				
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		ResultSet rs = null;
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "20200626HHmm");
		
		Date time = new Date();
		
		Date time1 = new Date(System.currentTimeMillis() - 3600 * 1000);
				
		int[] timearr = {0 , 500, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000};
		String[] timearr2 = new String[13];
		
		int i = 0;
		
		String flag = null;
		
		for(i = 0; i < 13 ; i++) {
			
			System.out.println( "-------STEP : SelectWhetherInfo2 timearr : "+ timearr[i] + " ----------");
			time = new Date(System.currentTimeMillis() - 3600 * timearr[i]);
			flag = format1.format(time);
			timearr2[i] = flag;
			
			System.out.println( "-------STEP : SelectWhetherInfo2 timearr2 : "+ timearr2[i] + " ----------");
		
		}
		
		HashMap hm = new HashMap();
		vgnVO rvo = null;
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			for(i = 0; i < 13 ; i++) {
			
				sb = new StringBuffer();
				
				System.out.println("--- addr : " + addr);
				System.out.println("--- timearr2["+ i +"] : " + timearr2[i]);
				
			//	sb.append("SELECT regdate, addr, temp, speed, Precipitation FROM dcollect Where addr = ? and DATE_FORMAT(regdate, '%Y%m%d%H%i') = ?");
			//	sb.append("SELECT regdate, addr, temp, speed, Precipitation FROM dcollect Where addr = ? and regdate"
			//			+ "BETWEEN STR_TO_DATE ( '"+ timearr2[i] +"00' , '%Y%m%d%H%i%s') and STR_TO_DATE ( '"+ timearr2[i] + "59' ,'%Y%m%d%H%i%s')");			
				
				sb.append("SELECT regdate, latitude, longitude, addr, temp, speed, Precipitation FROM dcollect3 Where regdate BETWEEN STR_TO_DATE ( ? , '%Y%m%d%H%i%s') and STR_TO_DATE ( ? ,'%Y%m%d%H%i%s')");
				
				pstmt = conn.prepareStatement(sb.toString());
	
			//	pstmt.setString(1, addr);
				pstmt.setString(1, timearr2[i] + "00" );
				pstmt.setString(2, timearr2[i] + "59" );
	
				rs = pstmt.executeQuery();
	
			//	Logger.log(Logger.LOG_ERROR, "Select Count() : " + rs);
				
				if (rs.next()) {
					
					rvo = new vgnVO();
					
					Logger.log(Logger.LOG_ERROR, "---------- Select i		 	: " + i + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select regdate		 	: " + rs.getString("regdate") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select latitude		: " + rs.getString("latitude") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select longitude		: " + rs.getString("longitude") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select addr		 	: " + rs.getString("addr") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select temp		 	: " + rs.getString("temp") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select speed		 	: " + rs.getString("speed") + " ----------");
					Logger.log(Logger.LOG_ERROR, "---------- Select Precipitation 	: " + rs.getString("Precipitation") + " ----------");
					
					rvo.setDate(		rs.getString("regdate"));
					rvo.setLatitude(	rs.getString("latitude"));
					rvo.setLongitude(	rs.getString("longitude"));
					rvo.setAddr( 		rs.getString("addr"));
					rvo.setTemp(		rs.getString("temp"));
					rvo.setSpeed(		rs.getString("speed"));
					rvo.setPrecip(		rs.getString("Precipitation"));
					
					hm.put(i, rvo);
				}
				else
				{
					msg = "Select Count() = 0";
					Logger.log(Logger.LOG_ERROR, "dblog : " + msg);
					
					Logger.log(Logger.LOG_ERROR, "---------- Select i		 	: " + i + " ----------");
					hm.put(i, rvo);
				}
			}
			
			return hm;

		}catch(SQLException ex){
		
			Logger.log(Logger.LOG_ERROR, "dblog : SQLException : " + ex.getSQLState().toString() + ex.getMessage().toString());
			
		}finally {
			try	{if (pstmt != null) pstmt.close();}catch (Exception ignored) {}
			try	{if (conn != null) conn.close();}catch (Exception ignored) {}
		}

		return hm;
	}
	*/
}


