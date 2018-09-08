package code.main;

import code.util.QuartzUtil;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public class QuartzCodeDemo {
    public static void main(String[] args) {
        JobDetail jobDetail = QuartzUtil.createJobDetail();
        Trigger trigger = QuartzUtil.createTrigger();
        Scheduler scheduler = QuartzUtil.createScheduler(jobDetail, trigger);
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
