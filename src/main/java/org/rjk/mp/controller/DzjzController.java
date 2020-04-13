package org.rjk.mp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.rjk.mp.model.Code;
import org.rjk.mp.model.Dzjz;
import org.rjk.mp.service.DzjzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/dzjz")
public class DzjzController {


    @Autowired
    DzjzService dzjzService;


    /**
     * 查询出所有的数据
     * @return
     */
    @RequestMapping("/getInfo")
    public Map<String, Object> getDzjz(@RequestParam Integer page, @RequestParam Integer limit,
                                       @RequestParam(required = false) String begintime,
                                       @RequestParam(required = false) String endtime,
                                       @RequestParam(required = false) String badw){

        Page<Dzjz> p = dzjzService.getInfo(page,limit,begintime,endtime,badw);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code","0");
        map.put("msg","成功");
        map.put("count",p.getTotal());
        map.put("data",p.getRecords());
        return map;
    }

    /**
     * 访问码表，根据不同的codeclass
     * @param codeclass
     * @return
     */
    @RequestMapping("/getCode/{codeclass}")
    public List<Code> getCode(@PathVariable(name = "codeclass")String codeclass){
        List<Code> list = dzjzService.getCode(codeclass);
        return list;
    }

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public Map<String,String> add(@RequestBody Dzjz dzjz){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        dzjz.setId(uuid);
        int result = dzjzService.insert(dzjz);
        Map<String,String> map = new HashMap<>();
        if (result > 0){
            map.put("code","1");
        }else{
            map.put("code","0");
        }
        return map;
    }

    @RequestMapping("/deldata")
    public Map<String,String> deldata(@RequestParam String id){
        int result = dzjzService.deldata(id);
        Map<String,String> map = new HashMap<>();
        if (result > 0){
            map.put("code","1");
            map.put("msg","删除成功！");
        }else{
            map.put("code","0");
            map.put("msg","删除失败！");
        }
        return map;
    }

}
