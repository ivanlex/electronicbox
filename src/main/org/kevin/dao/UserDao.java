package org.kevin.dao;

import org.apache.ibatis.annotations.*;
import org.kevin.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    @Select("select * from t_User")
    @Results({
            @Result(property = "userName", column = "user_name")
    })
    List<User> getAll();

    @Select("select * from t_User where id = #{id}")
    User getById(Long id);

    @Select("select * from t_User where user_name = #{username} limit 1")
    User getByUsername(String username);

    User getByUserToken(String userToken);

    @Select("select * from t_User where User_Token = #{userToken} and User_PWD = #{userPwd} limit 1")
    User validateUserTokenPWD(String userToken, String userPwd);

    @Select("select User_Locked from t_User where User_Token = #{userToken} and User_PWD = #{userPwd} limit 1")
    boolean getUserLockstatus(String userToken, String userPwd);

    @Insert({"insert into t_User (user_name,user_pwd,user_locked) values (#{userName},#{userPwd},#{userLocked})"})
    void install(User user);

    @Update({"update t_User set user_name = #{userName}"})
    void update(User user);

    @Delete("delete from t_User where id = #{id}")
    void delete(Long id);
}
