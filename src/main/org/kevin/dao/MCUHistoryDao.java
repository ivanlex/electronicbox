package org.kevin.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.kevin.domain.MCUHistoryInfo;
import org.kevin.domain.MCUOpInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Mapper
@Repository
public interface MCUHistoryDao {

    @Select("select * from t_mcu_history_info where MCU_ID = #{mcuId}")
    @Results(value = {
            @Result(property = "mcuId", column = "MCU_ID"),
            @Result(property = "openStatus", column = "Open_Status"), //空开状态
            @Result(property = "crackStatus", column = "Crack_Status"), //裂化状态
            @Result(property = "lightningStatus", column = "Lightning_Status"), //雷击状态
            @Result(property = "groundedStatus", column = "Grounded_Status"), //接地状态
            @Result(property = "lightningCount", column = "Lightning_Count"), //雷击次数
            @Result(property = "updatedTime", column = "Updated_Time", javaType = java.util.Date.class,jdbcType= JdbcType.TIMESTAMP), //最后更新时间
    })
    List<MCUHistoryInfo> getAllMCUHistoryInfo(String mcuId);

    @Update("Insert into t_mcu_history_info (MCU_ID,Open_Status,Crack_Status,Lightning_Status,Grounded_Status,Lightning_Count,Updated_Time) VALUES (#{mcuId},#{openStatus},#{crackStatus},#{lightningStatus},#{groundedStatus}, #{lightningCount},#{updatedTime})")
    void insertMCUHistory(String mcuId, int openStatus, int crackStatus, int lightningStatus, int groundedStatus, int lightningCount, Date updatedTime);
}
