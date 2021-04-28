package com.heima.data_platform.nb.job;

import com.heima.data_platform.nb.common.Meter;
import com.heima.data_platform.nb.dao.MeterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-23 14:21
 * @description： 历史数据存储任务
 * @modified By：wt
 */
@Component
@Slf4j
public class MeterJobs {
    final MeterMapper meterMapper;
    @Autowired
    public MeterJobs(MeterMapper meterMapper) {
        this.meterMapper = meterMapper;
    }

    /**
     *   每十分钟执行一次： 0 0/10 * * * ?
     *   每十秒执行一次： 0/10 * * * * ?
     *   每2小时执行一次: 0 0 0/2 * * ? 
     *   执行历史数据存储任务
     */
    @Scheduled(cron = "0 0/2 * * * ?")
    public void hisJob(){
        log.info(this.getClass()+">>历史转储任务执行了....");
        long startTime = System.currentTimeMillis();
        List<Meter> allMeter = meterMapper.getAllMeter(null, null, null, "all");
        //String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        for (Meter meter : allMeter) {
            String lastHisMeterDate = meterMapper.getLastHisMeterDate(meter.getMeterNum());
            if (meter.getDate().compareTo(lastHisMeterDate)>0){
                //meter.setDate(now);
                log.info(this.getClass()+":{}有新历史数据上传>>>>>>>>{}",meter.getMeterNum(),meter.getDate().compareTo(lastHisMeterDate));
                meterMapper.addHisMeter(meter);
            }else {
                log.info(this.getClass()+":{}没有新历史数据上传>>>>>>>{}",meter.getMeterNum(),meter.getDate().compareTo(lastHisMeterDate));
            }
        }
        long endTime = System.currentTimeMillis();
        long time = endTime-startTime;
        log.info(this.getClass()+">>历史转储任务完成,消耗了{}毫秒",time);
    }

}
