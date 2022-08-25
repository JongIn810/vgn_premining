package co.empti.vgnft.quartz;

import java.io.IOException;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import co.empti.vgnft.util.MakeFile;

public class TestJob1 implements Job {

	public void execute(JobExecutionContext context) {
		
		try {
			
			System.out.println("TestJob1.execute() is Executed... : " + new Date());
			
			// Dohwa 59 126   
			// Byulnae 62 128
			
			String pre = callAWS.getPrecipitation("59", "126");
			System.out.println("Ȯ�� : " + pre);
			
			MakeFile mf = new MakeFile();
			
			mf.MakeCacheFile( pre.toString().trim() );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
}
