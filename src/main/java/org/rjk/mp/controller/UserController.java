package org.rjk.mp.controller;

import org.rjk.mp.mapper.UserMapper;
import org.rjk.mp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getAll")
    public Map<String, Object> getAllUser(){
        List<User> list = userMapper.selectList(null);
        int count = userMapper.selectCount(null);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code","0");
        map.put("msg","成功");
        map.put("count",count);
        map.put("data",list);
        return map;
    }

    @RequestMapping("/upname")
    public Map<String,Object> updateUserName(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        int result = userMapper.update(user);
        if (result>0){
            map.put("code","0");
            map.put("msg","成功！");
        }else {
            map.put("code","1");
            map.put("msg","失败！");
        }
        return map;
    }

    @RequestMapping("/deluser")
    public Map<String,Object> delUser(String id){
        Map<String,Object> map = new HashMap<>();
        int result = userMapper.deleteById(id);
        if (result>0){
            map.put("code","0");
            map.put("msg","成功！");
        }else {
            map.put("code","1");
            map.put("msg","失败！");
        }
        return map;
    }



    @RequestMapping("/dl")
    public Map<String, String> denglu(String username, String password, HttpSession session) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User u = userMapper.denglu(user);
        Map<String, String> map = new HashMap<>();
        if (u == null) {
            map.put("code", "1");
            map.put("msg", "用户名密码错误！");
        } else {
            String orgid = userMapper.getOrg(u.getUserid());
            map.put("code", "0");
            map.put("msg", orgid);
            session.setAttribute("username",u.getUsername());
            session.setAttribute("name",u.getName());
            session.setAttribute("orgid",orgid);

        }

        return map;
    }
}
