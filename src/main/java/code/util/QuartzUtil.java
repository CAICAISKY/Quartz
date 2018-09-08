package code.util;

import code.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Quartz工具类
 * @author schuyler
 */
public class QuartzUtil {
    private static Logger logger = LoggerFactory.getLogger(QuartzUtil.class);

    /**
     * 创建任务详情
     */
    public static JobDetail createJobDetail() {
        logger.info("create JobDetail......");
        return JobBuilder.newJob(MyJob.class)
                .withIdentity("jobOne", "groupOne")
                .usingJobData("count", 0)
                .build();
    }

    /**
     * 创建触发器
     */
    public static Trigger createTrigger() {
        logger.info("create Trigger......");

        return newTrigger()
                .withIdentity("trggerOne","groupOne")
                .withSchedule(simpleSchedule()
                    .withIntervalInSeconds(5)
                    .withRepeatCount(20))
                .startAt(futureDate(5, DateBuilder.IntervalUnit.SECOND))
                .forJob("jobOne", "groupOne")
                .build();
    }

    /**
     * 创建调度器
     */
    public static Scheduler createScheduler(JobDetail jobDetail, Trigger trigger) {
        //创建调度工厂
        StdSchedulerFactory factory = new StdSchedulerFactory();

        //创建属性对象
        Properties props = new Properties();
        //线程池定义
        props.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool");
        //设置Scheduler的线程数
        props.put("org.quartz.threadPool.threadCount", "10");

        Scheduler scheduler = null;
        try {
            //进行属性初始化
            factory.initialize(props);
            //创建Scheduler
            scheduler = factory.getScheduler();
            //设置触发器和执行的任务详情
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("create Scheduler......");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }
}
