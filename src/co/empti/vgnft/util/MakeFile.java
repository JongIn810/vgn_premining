package co.empti.vgnft.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import co.empti.vgnft.util.Logger;

public class MakeFile {

	static String FILE_LOCATION = "/home/ec2-user/cache";
	static String FILE_NAME = "/precipitation_mapo.txt";
	static String FILE_NAME2 = "/precipitation_byul.txt";
	
	public static boolean MakeCacheFile(String param){

		try {
			
		    // initialize LampKey Library.
			Runtime runtime = Runtime.getRuntime();

		    String cmd;
		    		    
		    cmd = "echo " + param + " > "  + FILE_LOCATION + FILE_NAME;
		    
		    String[] cmdline = {"sh", "-c", cmd};
		    
		    Logger.log(Logger.LOG_ERROR, "----------------  Makefile : " + cmdline + "----------------------");
		    
		    //����� ������ ����
		    		    
	        Process process = runtime.exec(cmdline);
	                
	        InputStream is = process.getInputStream();

	        InputStreamReader isr = new InputStreamReader(is);

	        BufferedReader br = new BufferedReader(isr);

	        String line;

	        while((line = br.readLine()) != null) {
                //System.out.println(line);
	        	Logger.log(Logger.LOG_ERROR, "----------------  line : " + line + "----------------------");
	        }
		
	        return true;
	        
		} catch(IOException ie){
			
			Logger.log(Logger.LOG_ERROR, "------ IOException : " + ie);
			return false;
			
		} catch(Exception e) {
			
			Logger.log(Logger.LOG_ERROR, "------ Exception : " + e);
			return false;
		} 
			
	}
	
	public static boolean MakeCacheFile2(String param){

		try {
			
		    // initialize LampKey Library.
			Runtime runtime = Runtime.getRuntime();

		    String cmd;
		    		    
		    cmd = "echo " + param + " > "  + FILE_LOCATION + FILE_NAME2;
		    
		    String[] cmdline = {"sh", "-c", cmd};
		    
		    Logger.log(Logger.LOG_ERROR, "----------------  Makefile : " + cmdline + "----------------------");
		    
		    //����� ������ ����
		    		    
	        Process process = runtime.exec(cmdline);
	                
	        InputStream is = process.getInputStream();

	        InputStreamReader isr = new InputStreamReader(is);

	        BufferedReader br = new BufferedReader(isr);

	        String line;

	        while((line = br.readLine()) != null) {
                //System.out.println(line);
	        	Logger.log(Logger.LOG_ERROR, "----------------  line : " + line + "----------------------");
	        }
		
	        return true;
	        
		} catch(IOException ie){
			
			Logger.log(Logger.LOG_ERROR, "------ IOException : " + ie);
			return false;
			
		} catch(Exception e) {
			
			Logger.log(Logger.LOG_ERROR, "------ Exception : " + e);
			return false;
		} 
			
	}
	
	public static String ReadCacheFile() throws IOException{
		
		File f = new File(FILE_LOCATION + FILE_NAME);
    	
        BufferedReader reader = new BufferedReader(new FileReader(f));

        StringBuffer param = new StringBuffer();
        
        while(true)
        {
	        String str = reader.readLine(); 
	        if(str==null)
        		break;
        	param.append(str);
        //	param.append(System.getProperty("line.separator"));

        //	Logger.log(Logger.LOG_ERROR, "----------------  start : " + str + "----------------------");
        }
        reader.close();
        return param.toString();
        
	}
	
	public static String ReadCacheFile2() throws IOException{
		
		File f = new File(FILE_LOCATION + FILE_NAME2);
    	
        BufferedReader reader = new BufferedReader(new FileReader(f));

        StringBuffer param = new StringBuffer();
        
        while(true)
        {
	        String str = reader.readLine(); 
	        if(str==null)
        		break;
        	param.append(str);
        //	param.append(System.getProperty("line.separator"));

        //	Logger.log(Logger.LOG_ERROR, "----------------  start : " + str + "----------------------");
        }
        reader.close();
        return param.toString();
        
	}
}
