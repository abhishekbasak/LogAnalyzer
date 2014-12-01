package job;

import java.io.Serializable;

import play.Logger;
import play.jobs.Every;
import play.jobs.Job;
import play.libs.Codec;
import utils.LoggerConstants;
import utils.LoggerUtils;

@Every("30s")
public class SampleLogGenerator extends Job implements Serializable  {
	
	private static String className = "SampleLogGenerator";
	
	public void doJob() throws Exception {
		String studentId = Codec.UUID();
		
		Logger.info(LoggerUtils.log(className, "doJob", "Test Started", LoggerConstants.STUDENT_ID, studentId));
		
		Thread.sleep(10000);
		
		Logger.info(LoggerUtils.log(className, "doJob", "Test Finished", LoggerConstants.STUDENT_ID, studentId));
	}
	
}