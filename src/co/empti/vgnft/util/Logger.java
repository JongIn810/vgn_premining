package co.empti.vgnft.util;

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Logger {


	public static final int LOG_ALL   = 0;
	public static final int LOG_DEBUG = 1;
	public static final int LOG_INFO  = 2;
	public static final int LOG_WARN  = 3;
	public static final int LOG_ERROR = 4;
	
	private static int debug_level = LOG_WARN;
	private static PrintStream loggerStream = null;
	private static int today;
	
	static {
		String strLevel = Config.getProperty("debug.level", "3" );
		System.out.println("Setting debug level to " + strLevel );
		debug_level = Integer.parseInt( strLevel );
		
		// set log stream
		setLogStream();
	}
	
	
	public static void log (int level, String msg) {
		msg = msg.replaceAll("\\r", "");
		msg = msg.replaceAll("\\n", "");
		msg = msg.replaceAll("DEBUG", "");
		msg = msg.replaceAll("INFO", "");
		if ( level >= debug_level ) {
			if ( isNewDay() )
				setLogStream();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS ");
			synchronized (loggerStream) {
				loggerStream.print( sdf.format( new Date() ) );
				loggerStream.print( '[' );
				loggerStream.print( Thread.currentThread().getName() );
				loggerStream.print( "] " );
				loggerStream.println( msg );
			}
		}
	}
	
	public static void log (String msg) {
		log( LOG_INFO, msg );
	}
	
	public static void log (Throwable e) {
		if ( isNewDay() )
			setLogStream();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS ");
		synchronized (loggerStream) {
			loggerStream.print( sdf.format( new Date() ) );
			loggerStream.print( '[' );
			loggerStream.print( Thread.currentThread().getName() );
			loggerStream.print( "] " );
			e.printStackTrace( loggerStream );
		}
	}
	
	public static int getDebugLevel() {
		return debug_level;
	}
	
	private static boolean isNewDay() {
		return ( today != Calendar.getInstance().get( Calendar.DAY_OF_YEAR ) );
	}
	
	private static synchronized void setLogStream() {
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// set today's date
		today = Calendar.getInstance().get( Calendar.DAY_OF_YEAR );
		
		sb.append( Config.getProperty("log.dir", "/var/log/sign") );
		sb.append( System.getProperty("file.separator" ) );
		sb.append( Config.getProperty("log.filename.prefix", "emptico_") );
		sb.append( sdf.format( new Date() ) );
		sb.append( ".log" );
		String logFileName = sb.toString();
		
		try {
			PrintStream pStream = new PrintStream( new FileOutputStream( logFileName, true ) );
			if ( loggerStream != null )
				loggerStream.close();
			loggerStream = pStream;
			
			loggerStream.println();
			loggerStream.println("========================================");
			Logger.log(LOG_DEBUG, "log file initialized.");

		} catch ( FileNotFoundException fnfe ) {
			System.err.println("error opening log file \"" + logFileName + "\"" );
			System.err.println("logging to STDOUT." );
			loggerStream = System.out;
		}
		
	}
	
}
