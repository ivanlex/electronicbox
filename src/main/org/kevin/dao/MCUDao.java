package org.kevin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.kevin.domain.MCU;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MCUDao {

    @Select("select * from t_MCU")
    @Results({
            @Result(property = "mcuID", column = "MCU_ID"),
            @Result(property = "mcuToken", column = "MCU_Token")
    })
    List<MCU> getAll();

    MCU getMCUByMCUID(String mcuId);

    List<MCU> getMCUByLoginToken(String LoginToken);

    boolean validateMCU(MCU mcu);

    boolean validateMCUToken(MCU mcu);

    boolean getMCURebootStatus(MCU mcu);
}
