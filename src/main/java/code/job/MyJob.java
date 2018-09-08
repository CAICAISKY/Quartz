package code.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * 任务类
 * @author schuyler
 */
@PersistJobDataAfterExecution
public class MyJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(MyJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        //获取任务名字和任务组名
        JobKey key = jobDetail.getKey();
        logger.info("Job name and group:" + key);
        //打印当前时间
        logger.info("time:" + new Date());
        //获取执行次数，每次递增
        Integer count = (Integer) jobDetail.getJobDataMap().get("count");
        count = count + 1;
        jobDetail.getJobDataMap().put("count", count);
        logger.info("Job count :" + count +"....................");
    }
}
