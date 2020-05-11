package org.rjk.mp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.rjk.mp.config.R;
import org.rjk.mp.model.Code;
import org.rjk.mp.model.Dzjz;
import org.rjk.mp.service.DzjzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/dzjz")
public class DzjzController {

    @Value("${orgid}")
    String adminorgid ;

    @Autowired
    DzjzService dzjzService;


    /**
     * 查询出所有的数据
     * @return
     */
    @RequestMapping("/getInfo")
    public R getDzjz(@RequestParam Integer page, @RequestParam Integer limit,
                                       @RequestParam(required = false) String begintime,
                                       @RequestParam(required = false) String endtime,
                                       @RequestParam(required = false) String badw, HttpSession session){

        //从session取出那个部门登录的
        String orgid = String.valueOf(session.getAttribute("orgid"));
        if (orgid!=null && !orgid.equals(adminorgid)){
            badw = orgid;
        }
        Page<Dzjz> p = dzjzService.getInfo(page,limit,begintime,endtime,badw);
        return R.success(0,"成功！",p.getTotal(),p.getRecords());
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
    public R add(@RequestBody Dzjz dzjz ,HttpSession session){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        dzjz.setId(uuid);
        String badw = String.valueOf(session.getAttribute("orgid"));
        dzjz.setBadw(badw);
        int result = dzjzService.insert(dzjz);
        if (result > 0){
            return  R.success(1,"成功！");
        }else{
            return  R.fail(0,"失败");
        }
    }

    @RequestMapping("/deldata")
    public R deldata(@RequestParam String id){
        int result = dzjzService.deldata(id);
        Map<String,String> map = new HashMap<>();
        if (result > 0){
           return R.success(1,"删除成功！");
        }else{
           return R.fail(0,"删除失败！");
        }
    }

    @RequestMapping(value = "/updzjz",method = RequestMethod.POST)
    public R updatedzjz(@RequestBody Dzjz dzjz){
        Map<String,String> map = new HashMap<>();
        boolean b = dzjzService.updateById(dzjz);
        if (b){
            return R.success(1,"成功！");
        }else {
            return R.fail(0,"失败！");
        }
    }

}
