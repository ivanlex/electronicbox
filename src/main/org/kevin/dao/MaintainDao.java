package org.kevin.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.kevin.domain.MCUBasic;
import org.kevin.domain.MCUOpInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Mapper
@Repository
public interface MaintainDao {

    @Select("Select case when count(*) > 0 then 1 else 0 end from t_mcu_basic_info where MCU_ID = #{mcuId}")
    boolean isMcuExist(String mcuId);

    @Insert("Insert into t_mcu_basic_info (MCU_ID, Description, Group_ID, Address,Longitude,Latitude,Install_Date,User_ID)" +
            " values (#{mcuId},#{mcuDesc},#{mcuGroup},#{installedAddress},#{longitude},#{latitude},#{installedDate},#{userId})")
    void addNewMcu(String mcuId,String mcuDesc,String mcuGroup, String installedAddress,double longitude,double latitude, Date installedDate,String userId);

    @Update("Update t_mcu_basic_info set" +
            " Description = #{mcuDesc}, Group_ID = #{mcuGroup}, Address = #{installedAddress},Longitude = #{longitude},Latitude = #{latitude} where MCU_ID = #{mcuId} and User_ID = #{userId}")
    void updateMcu(String mcuId,String mcuDesc,String mcuGroup, String installedAddress,double longitude,double latitude,String userId);

    @Delete("Delete from t_mcu_basic_info where MCU_ID = #{mcuId} and User_ID = #{userId}")
    void removeMcu(String mcuId, String userId);

    @Select("Select count(*) from t_mcu_basic_info where User_ID = #{userId}")
    int countInstalledMCU(String userId);

    @Select("Select count(*) from t_mcu_op_info as mcuOpInfo join t_mcu_basic_info as mcuBasicInfo on mcuOpInfo.MCU_ID = mcuBasicInfo.MCU_ID" +
            " where NOW() - Update_Time <  100 * #{minute} and mcuBasicInfo.User_ID = #{userId} ")
    int countOnlineMCU(int minute,String userId);

    @Select("Select * from t_mcu_basic_info where User_ID = #{userId}")
    @Results(value = {
            @Result(property = "mcuId",column = "MCU_ID"),
            @Result(property = "address",column = "Address"),
            @Result(property = "desc",column = "Description"),
            @Result(property = "group",column = "Group_ID"),
            @Result(property = "latitude",column = "Latitude"),
            @Result(property = "longitude",column = "Longitude"),
            @Result(property = "installDate",column = "Install_Date", javaType = java.util.Date.class,jdbcType= JdbcType.TIMESTAMP),
    })
    List<MCUBasic> getAllMcu(String userId);

    @Select("Select * from t_mcu_basic_info where MCU_ID = #{mcuId}")
    @Results(value = {
            @Result(property = "mcuId",column = "MCU_ID"),
            @Result(property = "address",column = "Address"),
            @Result(property = "desc",column = "Description"),
            @Result(property = "group",column = "Group_ID"),
            @Result(property = "latitude",column = "Latitude"),
            @Result(property = "longitude",column = "Longitude"),
            @Result(property = "installDate",column = "Install_Date", javaType = java.util.Date.class,jdbcType= JdbcType.TIMESTAMP),
            @Result(property = "userId",column = "User_ID"),
    })
    MCUBasic getMcuById(String mcuId);

}
