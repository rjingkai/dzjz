package org.rjk.mp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.rjk.mp.model.Code;
import org.rjk.mp.model.Dzjz;

import java.util.List;

public interface DzjzService extends IService<Dzjz> {

    Page<Dzjz> getInfo (Integer pageNo,Integer pageSize,String begintime,String  endtime,String badw);

    public List<Code> getCode(String codeclass);

    public int insert(Dzjz dzjz);

    public int deldata(String id);


}
