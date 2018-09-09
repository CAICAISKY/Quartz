package sustainable.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.core.jmx.JobDetailSupport.newJobDetail;

/**
 * 持久化集群工具类
 */
public class ClusterQuartzUtil {
    private static Logger logger = LoggerFactory.getLogger(ClusterQuartzUtil.class);

    /**
     * 创建触发器
     */
    public static Trigger createTrigger(String triggerName, String triggerGroup, String cronExpresion) {
        return newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .withSchedule(cronSchedule(cronExpresion)
                    .inTimeZone(TimeZone.getTimeZone("Asia/Shanghai")))
                .build();
    }

    /**
     * 创建任务详情
     */
    public static JobDetail createJobDetail(String jobName, String jobGroup, Class clazz) {
        return JobBuilder
                .newJob(clazz)
                .withIdentity(jobName, jobGroup)
                .usingJobData("count", 0)
                .build();
    }

    /**
     * 创建调度器
     */
    public static Scheduler createScheduler(JobDetail jobDetail, Trigger trigger) {
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;
        try {
            scheduler = stdSchedulerFactory.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }

}
