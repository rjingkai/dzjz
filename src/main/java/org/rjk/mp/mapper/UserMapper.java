package org.rjk.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.rjk.mp.model.User;

public interface UserMapper extends BaseMapper<User> {


    public int update(User user);

    @Select("select * from lk.user_info where username = #{username} and password = #{password}")
    public User denglu(User user);

    @Select("select g.orgno from lk.user_org u inner join lk.org_info g on u.orgid = g.orgid where u.userid = #{userid}")
    public String getOrg(@Param("userid") String userid);

}
