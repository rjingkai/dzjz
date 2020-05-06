package org.rjk.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.rjk.mp.model.User;

public interface UserMapper extends BaseMapper<User> {


    public int update(User user);

    @Select("select * from lk.user_info where username = #{username} and password = #{password}")
    public User denglu(User user);

}
