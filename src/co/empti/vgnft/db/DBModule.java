package co.empti.vgnft.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import co.empti.vgnft.util.Logger;

public class DBModule {

	public Connection conn = null;

	public DBModule(){
	}

	public Connection Connect() {

		Driver myDriver = null;
/*
		String DATABASE = "oracle";
		String MODE = "url";
		String DRIVER = "oracle.jdbc.driver.OracleDriver";
		String POOLNAME = "";
		String URL = "jdbc:oracle:thin:@192.168.32.128:1521:orcl";
		String USER = "emptico";
		String PASSWORD = "1234qwer";
*/
		String DATABASE = "mysql";
		String MODE = "url";
		String DRIVER = "net.sf.log4jdbc.sql.jdbcapi.DriverSpy";
		String POOLNAME = "";
		
		String URL = "jdbc:log4jdbc:mysql://mvon-db1.cyfuzg9fme7f.ap-northeast-2.rds.amazonaws.com/vgnft?autoReconnect=true&serverTimezone=UTC&allowMultiQueries=true&characterEncoding=UTF-8";
	//	String URL = "jdbc:log4jdbc:mysql://127.0.0.1:3306/rainvow?autoReconnect=true&serverTimezone=UTC&allowMultiQueries=true&characterEncoding=UTF-8";
		
		String USER = "vgndb";
		String PASSWORD = "1234qwer";
				
		try{
			if("oracle".equals(DATABASE)){
				if("pool".equals(MODE)){
					myDriver = (Driver)Class.forName(DRIVER).newInstance();
					conn = myDriver.connect(POOLNAME,null);
					conn.setAutoCommit(false);
				}else if(MODE.equals("url")){
					Class.forName(DRIVER);
					conn = DriverManager.getConnection(URL, USER, PASSWORD);
					conn.setAutoCommit(false);
				}
			}
			if("mysql".equals(DATABASE)){
				if("pool".equals(MODE)){
					myDriver = (Driver)Class.forName(DRIVER).newInstance();
					conn = myDriver.connect(POOLNAME,null);
					conn.setAutoCommit(false);
				}else if(MODE.equals("url")){
					Class.forName(DRIVER);
					conn = DriverManager.getConnection(URL, USER, PASSWORD);
					conn.setAutoCommit(false);
				}
			}
			if("postgresql".equals(DATABASE)){
				if("pool".equals(MODE)){
					myDriver = (Driver)Class.forName(DRIVER).newInstance();
					conn = myDriver.connect(POOLNAME,null);
					conn.setAutoCommit(false);
				}else if(MODE.equals("url")){
					Class.forName(DRIVER);
					conn = DriverManager.getConnection(URL, USER, PASSWORD);
					conn.setAutoCommit(false);
				}
			}
			
		}catch(Exception e){
			Logger.log(Logger.LOG_ERROR,"dblog : " + e.getMessage());
			e.printStackTrace();            
		}        
		return conn;
	}


	public void Close(Connection con){
		try{
			if(con!=null){
				con.close();
			}
		}catch(Exception e){
			Logger.log(Logger.LOG_ERROR, "dblog : " + e.getMessage());
			e.printStackTrace();
		}
	}

	
}
