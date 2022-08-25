package co.empti.vgnft.util;

public class Global {
	public static final String SESSION_NAME = "MDS_VBV_SESS";
	public static final String BASE_URL;
	
	// �̴��� ������� �н� : initech ���� �����ϴ� ������ �ٷ� �� ���
	//public static final String INI_SVR_PATH="/weblogic/bea815/domains/HCSDomain/ACSMain";	
	public static final String INI_SVR_PATH;	
	
	public static final int DBTYPE_ORACLE = 0;
	public static final int DBTYPE_MSSQL = 1;
	public static final int DBTYPE;
	
	public static final String ACS_ADDR;
	public static final int ACS_PORT;
	
	public static final int BIN_FLAG_NONE = 0;
	public static final int BIN_FLAG_HANA = 1;		
	
	public static final int ENROLL_CHECK = 1;		// ������ üũ
	public static final int AUTH_CHECK = 2;			// ���� üũ
	
	static {
		
		String strUrl = Config.getProperty("web.context", "/signtest");
		
		if( strUrl.endsWith("/") )
			BASE_URL = strUrl.substring(0, strUrl.length()-1);
		else
			BASE_URL = strUrl;
		INI_SVR_PATH = Config.getProperty("ini.svr.path", "/weblogic/bea815/domains/HCSDomain/applications/ACSMain");
		String strType = Config.getProperty("db.type", "ORACLE");
		if( "ORACLE".equals(strType) )
			DBTYPE = DBTYPE_ORACLE;
		else
			DBTYPE = DBTYPE_MSSQL;
		ACS_ADDR = Config.getProperty("acs.core.ip", "127.0.0.1");
		String strPort = Config.getProperty("acs.core.port", "5501");
		ACS_PORT = Integer.parseInt(strPort);
		
	}
}
