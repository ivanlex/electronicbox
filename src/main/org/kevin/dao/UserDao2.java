package org.kevin.dao;

import org.apache.ibatis.annotations.*;
import org.kevin.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface UserDao2 {

    @Select("select * from t_user where User_Name = #{username} and User_PWD = #{password} limit 1")
    @Results({
            @Result(property = "userName", column = "User_Name"),
            @Result(property = "userPwd", column = "User_PWD"),
    })
    User getUserByPWD(String username,String password);

    @Update({"update t_user set Login_Token = #{token} where User_Name = #{user.userName} and User_PWD = #{user.userPwd}"})
    void updateToken(User user,String token);

    @Update({"update t_user set WS_SessionId = #{sessionId}, WS_UpdateTime = #{updateTime} where Login_Token = #{token}"})
    void updateWSSessionId(String token, String sessionId, Date updateTime);

    @Select("Select case when count(*) > 0 then 1 else 0 end from t_user where Login_Token= #{token}")
    boolean validateToken(String token);
}
