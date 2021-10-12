package org.kevin.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.kevin.domain.MCU;
import org.kevin.domain.MCUOpInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface MCUOpInfoDao {

    @Select("select *, case when NOW() - Update_Time <  300 * #{minute} then 1 else 0 end as 'Is_Online' from t_mcu_op_info")
    @Results({
            @Result(property = "mcuId", column = "MCU_ID"),
            @Result(property = "isOnline", column = "Is_Online"),
            @Result(property = "openStatus", column = "Open_Status"), //空开状态
            @Result(property = "crackStatus", column = "Crack_Status"), //裂化状态
            @Result(property = "lightningStatus", column = "Lightning_Status"), //雷击状态
            @Result(property = "groundedStatus", column = "Grounded_Status"), //接地状态
            @Result(property = "lightningCount", column = "Lightning_Count"), //雷击次数
            @Result(property = "updateTime",column = "Update_Time",javaType = java.util.Date.class,jdbcType = JdbcType.TIMESTAMP), //最后更新时间
    })
    List<MCUOpInfo> getAllMCUOpInfo(int minute);

    @Select("Select *, case when NOW() - Update_Time <  300 * #{minute} then 1 else 0 end as 'Is_Online' from t_mcu_op_info where CONVERT(MCU_ID, UNSIGNED INTEGER) <= 5 order by CONVERT(MCU_ID, UNSIGNED INTEGER)")
    @Results({
            @Result(property = "mcuId", column = "MCU_ID"),
            @Result(property = "isOnline", column = "Is_Online"),
            @Result(property = "openStatus", column = "Open_Status"), //空开状态
            @Result(property = "crackStatus", column = "Crack_Status"), //裂化状态
            @Result(property = "lightningStatus", column = "Lightning_Status"), //雷击状态
            @Result(property = "groundedStatus", column = "Grounded_Status"), //接地状态
            @Result(property = "lightningCount", column = "Lightning_Count"), //雷击次数
            @Result(property = "updateTime",column = "Update_Time",javaType = java.util.Date.class,jdbcType = JdbcType.TIMESTAMP), //最后更新时间
    })
    List<MCUOpInfo> getTop5MCUStatus(int minute);

    @Update("Update t_mcu_op_info set Open_Status = #{openStatus},Crack_Status=#{crackStatus},Lightning_Status=#{lightningStatus},Grounded_Status=#{groundedStatus},Lightning_Count =#{lightningCount},Update_Time=#{updateTime} where MCU_ID = #{mcuId}")
    void updateMCUOpInfo(String mcuId, int openStatus, int crackStatus, int lightningStatus, int groundedStatus, int lightningCount, Date updateTime);

    @Insert("Insert into t_mcu_op_info (MCU_ID, Open_Status ,Crack_Status,Lightning_Status, Grounded_Status, Lightning_Count) VALUES " +
            "(#{mcuId},0,0,0,0,0)")
    void addMCUOpInfo(String mcuId);

    @Delete("Delete from t_mcu_op_info where MCU_ID = #{mcuId}")
    void removeMCUOpInfo(String mcuId);

}
