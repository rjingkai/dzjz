package org.rjk.mp.test;


@FunctionalInterface
public interface UserInterface {

    String valitUser(String name);

    //默认方法对函数式接口的语法检测无影响
    default String valitUsername(){
        return "";
    }

    //静态方法对函数式接口的语法检测无影响
    static String valitUserName(){
        return "";
    }
}


