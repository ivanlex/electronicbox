package org.kevin.dao;

import org.apache.ibatis.annotations.*;
import org.kevin.domain.User;
import org.springframework.stereotype.Repository;

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
}
