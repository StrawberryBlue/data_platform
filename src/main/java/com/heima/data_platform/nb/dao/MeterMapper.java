package com.heima.data_platform.nb.dao;

import com.heima.data_platform.nb.common.Meter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：wt
 * @date ：Created in 2021-04-09 9:43
 * @description：meter数据库访问层
 * @modified By：wt
 */
@Mapper
@Repository
public interface MeterMapper {
    /**
     * 获取实时表数据
     * @param meterNum
     * @param dateStart
     * @param dateEnd
     * @param mark
     * @return
     */
    List<Meter> getAllMeter(@Param("meterNum") String meterNum, @Param("dateStart") String dateStart,@Param("dateEnd") String dateEnd,@Param("mark") String mark);

    /**
     * 获取历史表数据
     * @param meterNum
     * @param dateStart
     * @param dateEnd
     * @param mark
     * @return
     */
    List<Meter> getHisMeter(@Param("meterNum") String meterNum, @Param("dateStart") String dateStart,@Param("dateEnd") String dateEnd,@Param("mark") String mark);

    void addHisMeter(Meter meter);

    String getLastHisMeterDate(@Param("meterNum") String meterNum);

}
