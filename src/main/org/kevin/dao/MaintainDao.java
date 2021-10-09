package org.kevin.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.kevin.domain.MCUBasic;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface MaintainDao {

    @Select("Select case when count(*) > 0 then 1 else 0 end from t_mcu_basic_info where MCU_ID = #{mcuId}")
    boolean isMcuExist(String mcuId);

    @Insert("Insert into t_mcu_basic_info (MCU_ID,Address,Install_Date) values (#{mcuId},#{installedAddress},#{installedDate})")
    void addNewMcu(String mcuId, String installedAddress, Date installedDate);

    @Delete("Delete from t_mcu_basic_info where MCU_ID = #{mcuId}")
    void removeMcu(String mcuId);

    @Select("Select * from t_mcu_basic_info")
    @Results(value = {
            @Result(property = "mcuId",column = "MCU_ID"),
            @Result(property = "address",column = "Address"),
            @Result(property = "installDate",column = "Install_Date", javaType = java.util.Date.class,jdbcType= JdbcType.TIMESTAMP),
    })
    List<MCUBasic> getAllMcu();

}
