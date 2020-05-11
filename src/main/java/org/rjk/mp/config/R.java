package org.rjk.mp.config;


import ch.qos.logback.classic.gaffer.ComponentDelegate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class R<T> {

    /**
     * 状态码
     */
    int code;

    /**
     * 状态信息
     */
    String msg;

    /**
     * 返回的数据
     */
    T data;

    /**
     * 数据条数
     */
    Long count ;


    private R (){
        this(200,"success");
    }

    private R(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    private R(int code ,String msg ,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private R(int code,String msg,Long count,T data){
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public static R success(){
        return new R();
    }

    public static <T> R success(T data){
        return success(data);
    }

    public static R success(int code,String msg){
        return new R(code,msg);
    }


    public static R fail(){
        return new R(500,"出错了");
    }


    public static R fail(int code,String msg){
        return new R(code,msg);
    }

    public static <T> R success(int code,String msg,Long count,T data){
        return new R(code,msg,count,data);
    }

}
