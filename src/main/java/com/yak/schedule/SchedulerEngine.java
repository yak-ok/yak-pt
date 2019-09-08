package com.yak.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerEngine {

    public static void main(String[] args) throws SchedulerException {
        //每天零时执行
        String cron = "0 0 0 * * ? *";

        //测试阶段，每5分钟执行
        cron = "0 1/5 * * * ? *";

        String jobName = "checkLicenseJob";
        String triggerName = "trigger_" + jobName;
        JobDetail jobDetail = JobBuilder.newJob(CheckLicenseJob.class).withIdentity(jobName).build();
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName).startNow().withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
