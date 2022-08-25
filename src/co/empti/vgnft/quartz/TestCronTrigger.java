package co.empti.vgnft.quartz;

import java.text.ParseException;

import javax.servlet.http.HttpServlet;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import co.empti.vgnft.quartz.TestJob1;

public class TestCronTrigger extends HttpServlet{
	
	 private SchedulerFactory schedFact;
	 private Scheduler sched;

	 public TestCronTrigger() {
	 
		 try {
		   
			 // 스케쥴 생성후 시작
			 schedFact = new StdSchedulerFactory();
			 sched = schedFact.getScheduler();
			 sched.start();
		   
		   // Job1 생성 (Parameter : 1.Job Name, 2.Job Group Name, 3.Job Class)
			 JobDetail job1 = new JobDetail("job1", "group1", TestJob1.class);
		   // Trigger1 생성 (Parameter : 1.Trigger Name, 2.Trigger Group Name, 3.Cron Expression)
			 CronTrigger trigger1 = new CronTrigger("trigger1", "group1", "0 0 1 * * *");
			 
			 
			// 		 * "0 0 * * * *" = the top of every hour of every day.
			//		 * "*/10 * * * * *" = every ten seconds.
			//		 * "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
			//		 * "0 0 8,10 * * *" = 8 and 10 o'clock of every day.
			//		 * "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
			//		 * "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
			//		 * "0 0 0 25 12 ?" = every Christmas Day at midnight
			 
			 
		   // 아래는 trigger 가 misfire 되었을 경우에 대한 처리를 설정한다.
		   //trigger1.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
			 sched.scheduleJob(job1, trigger1);
		   
		   // Job2 생성후 등록
		/*	 
		   	JobDetail job2 = new JobDetail("job2", "group2", TestJob2.class);
		   	CronTrigger trigger2 = new CronTrigger("trigger2", "group2", "0 6 * * * ?");
		   	sched.scheduleJob(job2, trigger2);
		   	
		   //Job2 삭제 (30초 후)
		   	/*
		   Thread.sleep(30000);
		   sched.deleteJob("job2", "group2");
		    */
		   
		 } catch (SchedulerException e) {
			 e.printStackTrace();
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
	 }

	 public static void main(String[] args) {
		 new TestCronTrigger();
	 }
}
	

