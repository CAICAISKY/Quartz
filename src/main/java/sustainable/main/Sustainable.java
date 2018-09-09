package sustainable.main;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import sustainable.job.MyJob;
import sustainable.util.ClusterQuartzUtil;

public class Sustainable {
    public static void main(String[] args) {
        JobDetail jobDetail = ClusterQuartzUtil.createJobDetail("job_1", "job_group_1", MyJob.class);
        Trigger trigger = ClusterQuartzUtil.createTrigger("trrigger_ 1", "trigger_group_1", "5/5 * * * * ?");
        Scheduler scheduler = ClusterQuartzUtil.createScheduler(jobDetail, trigger);
        try {
            if (!scheduler.checkExists(jobDetail.getKey())) {
                scheduler.scheduleJob(jobDetail, trigger);
            }
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
