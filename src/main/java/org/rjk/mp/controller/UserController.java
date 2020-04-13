package org.rjk.mp.controller;

import org.rjk.mp.mapper.UserMapper;
import org.rjk.mp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
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
}
