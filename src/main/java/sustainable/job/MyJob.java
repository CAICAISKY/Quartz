package sustainable.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author schuyler
 */
@PersistJobDataAfterExecution
public class MyJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(MyJob.class);
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("running......");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Integer count = (Integer) jobDataMap.get("count");
        logger.info("job count: " + count + "......");
        jobDataMap.put("count", count + 1);
    }
}
