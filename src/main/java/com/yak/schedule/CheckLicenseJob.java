package com.yak.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckLicenseJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("现在的时间是：" + sf.format(date));

        //具体的业务逻辑
        System.out.println("Hello Quartz");
        System.exit(0);
    }

    private boolean check() {
        String sql = "";

        return false;
    }
}
