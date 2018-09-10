package sustainable.job;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**状态数据持久化
 * @author CaiShunfeng
 * @PersistJobDataAfterExecution: 数据持久化
 * @DisallowConcurrentExecution:  不可并发执行
 * */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SpringClusterQuartzJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(SpringClusterQuartzJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("do job for spring cluster");
        Integer count = (Integer) context.getJobDetail().getJobDataMap().get("count");
        logger.info("job count :" + count);
        context.getJobDetail().getJobDataMap().put("count", count + 1);
        logger.info("job finished");
    }
}
