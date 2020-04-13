package org.rjk.mp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.rjk.mp.mapper.DzjzMapper;
import org.rjk.mp.model.Code;
import org.rjk.mp.model.Dzjz;
import org.rjk.mp.service.DzjzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DzjzServiceImpl extends ServiceImpl<DzjzMapper,Dzjz> implements DzjzService {


    @Autowired
    DzjzMapper dzjzMapper;


    @Override
    public Page<Dzjz> getInfo (Integer pageNo,Integer pageSize,String begintime,String endtime,String badw){
        Page<Dzjz> page = new Page<>(pageNo,pageSize);
        List<Dzjz> list = dzjzMapper.getInfo(begintime,endtime,badw,page);
        return page.setRecords(list);
    }

    @Override
    public List<Code> getCode(String codeclass){
        return dzjzMapper.getCode(codeclass);
    }

    @Override
    public int insert(Dzjz dzjz){
        return dzjzMapper.insert(dzjz);
    }

    @Override
    public int deldata(String id){
        return dzjzMapper.deldata(id);
    }
}
