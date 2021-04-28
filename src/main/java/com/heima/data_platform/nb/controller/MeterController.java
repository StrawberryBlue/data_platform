package com.heima.data_platform.nb.controller;

import com.github.pagehelper.PageInfo;
import com.heima.data_platform.nb.common.Meter;
import com.heima.data_platform.nb.common.Result;
import com.heima.data_platform.properties.Constant;
import com.heima.data_platform.nb.service.MeterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：wt
 * @date ：Created in 2021-04-08 10:22
 * @description：
 * @modified By：wt
 */
@Slf4j
@RestController
@RequestMapping("/meter")
public class MeterController {
    final MeterService meterService;
    @Autowired
    public MeterController(MeterService meterService) {
        this.meterService = meterService;
    }

    /**
     * 获取列表
     * @param pageNum
     * @param pageSize
     * @param token
     * @param meterNum
     * @param dateStart
     * @param dateEnd
     * @return
     */
    @RequestMapping("/get")
    public Result getMeter(@RequestParam(defaultValue = "1",value = "page") Integer pageNum, @RequestParam(defaultValue = "10",value = "limit") Integer pageSize,
                           String token,String meterNum,String dateStart,String dateEnd){
        log.info(">>>>>>>>token:{}>>>>id:{}>>>>>>>>dateStart:{}>>>>>>dateEnd:{}",token,meterNum,dateStart,dateEnd);
        PageInfo<Meter> meterPageInfo = meterService.getMeter(pageNum, pageSize,meterNum,dateStart,dateEnd,token);
        return new Result(Constant.REQUEST_SUCCEEDED_CODE,meterPageInfo);
    }

    /**
     * 修改设备上传周期
     * @param meterNum
     * @param newCycle
     * @param token
     * @return
     */
    @RequestMapping("/updateCycle")
    public Result updateCycle(String meterNum,String newCycle,String token){
        int flag = meterService.updateCycle(meterNum, newCycle, token);
        if(flag==1){
            return new Result(200,"修改成功");
        }
        return new Result(0,"修改失败");
    }

}
