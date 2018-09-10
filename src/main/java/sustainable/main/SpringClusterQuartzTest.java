package sustainable.main;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringClusterQuartzTest {

    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-cluster.xml");
        Scheduler scheduler = (Scheduler) applicationContext.getBean("scheduler");
        JobDetail jobDetail = (JobDetail) applicationContext.getBean("jobDetail");
        try {
            scheduler.start();
            //scheduler.deleteJob(jobDetail.getKey());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
