package com.heima.data_platform.nb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heima.data_platform.nb.common.Meter;
import com.heima.data_platform.nb.common.User;
import com.heima.data_platform.nb.dao.MeterMapper;
import com.heima.data_platform.util.FormatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-09 9:39
 * @description： meter业务层
 * @modified By：wt
 */
@Service
@Slf4j
public class MeterService {
    final MeterMapper meterMapper;
    final RedisTemplate redisTemplate;
    final RestTemplate restTemplate;
    @Autowired
    public MeterService(MeterMapper meterMapper, RedisTemplate redisTemplate,RestTemplate restTemplate) {
        this.meterMapper = meterMapper;
        this.redisTemplate = redisTemplate;
        this.restTemplate = restTemplate;
    }

    /**
     * 分页查询meter
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @return PageInfo
     */
    public PageInfo<Meter> getMeter(Integer pageNum,Integer pageSize,String meterNum,String dateStart,String dateEnd,String token){
        List<Meter> meters = null;
        try {
            User user = (User) redisTemplate.boundValueOps(token).get();
            if (user!=null) {
                PageHelper.startPage(pageNum, pageSize);
                if ((meterNum==null||"".equals(meterNum))&&(dateStart==null||"".equals(dateStart))&&(dateEnd==null||"".equals(dateEnd))){
                    meters = meterMapper.getAllMeter(meterNum, FormatUtil.formatDate(dateStart), FormatUtil.formatDate(dateEnd), user.getMark());
                }else {
                    meters = meterMapper.getHisMeter(meterNum, FormatUtil.formatDate(dateStart), FormatUtil.formatDate(dateEnd), user.getMark());
                }
                PageInfo<Meter> pageInfo = new PageInfo<Meter>(meters);
                return pageInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改设备上传周期
     * @param meterNum
     * @param newCycle
     * @param token
     * @return
     */
    public int updateCycle(String meterNum,String newCycle,String token){
        if (null!=newCycle) {
            for (int i = newCycle.length(); i < 4; i++) {
                newCycle = "0" + newCycle;
            }
        }
        String url = "http://61.161.173.203:1100/WebService1.asmx/ShenMa?IMEI=@&BiaoHao="+meterNum+"&ZT=1&ShiJian="+newCycle+"&Token=Grdqita~";
        try {
            String response = restTemplate.getForObject(url, String.class);
            if(response.contains("GOOD")){
                return 1;
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
