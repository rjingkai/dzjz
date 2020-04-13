package org.rjk.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.rjk.mp.model.Dzjz;
import org.rjk.mp.model.Code;
import java.util.List;

@Mapper
public interface DzjzMapper extends BaseMapper<Dzjz> {

    @Update("update lk.dzjz set isdel = 1 where id = #{id}")
    public int deldata(String id);

    public List<Code> getCode(String codeclass);

    @Select("<script>SELECT id,\n" +
            "\t( SELECT codedesc FROM lk.code c WHERE c.codeclass = 'BADW' AND c.code = d.badw ) AS badw,\n" +
            "\ttime,\n" +
            "\tajsl,\n" +
            "\t( SELECT codedesc FROM lk.code c WHERE c.codeclass = 'AJXZ' AND c.code = d.ajxz ) AS ajxz,\n" +
            "\tbamj,\n" +
            "\tajbh,\n" +
            "\t( CASE isdzjz WHEN 1 THEN '是' WHEN 2 THEN '否' ELSE '未知' END ) AS isdzjz,\n" +
            "\twscyy,\n" +
            "\twbhyy,\n" +
            "\t( CASE isbl WHEN 1 THEN '是' WHEN 2 THEN '否' ELSE '未知' END ) AS isbl \n" +
            "FROM\n" +
            "\tlk.dzjz d \n" +
            "WHERE\n" +
            "\tisdel = 0  <if test=\"badw != null and badw != ''\"> and badw = #{badw} </if> <if test=\"begintime != null and begintime != '' and endtime != null and endtime != ''\"> and time between #{begintime} and #{endtime}</if>\n" +
            "ORDER BY\n" +
            "\ttime DESC</script>")
    public List<Dzjz> getInfo(@Param("begintime") String begintime, @Param("endtime") String endtime, @Param("badw") String badw, Page<Dzjz> page);

}
