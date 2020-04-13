package org.rjk.mp.test;

import org.rjk.mp.test.hehe.UserInterfaceImpl;

import java.util.UUID;
import java.util.function.*;

public class Demo {

    public static void main(String[] args){

        //1.传统模式下的采用匿名内部类的方式来创建线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("threading....."+Thread.currentThread().getId());
//            }
//        }).start();
//
//
//        //2.采用jdk1.8的新特性lambda表达式的方式来进行创建
//        // lambda 表达式：箭头是核心符号，箭头左边的是操作参数，右边的是操作表达式
//        new Thread(()->{
//            System.out.println("lambdathreading....."+Thread.currentThread().getId());
//        }).start();


        //实现类的实现方式
//        UserInterface userimpl = new UserInterfaceImpl();
//        userimpl.valitUser("admin");
//
//
//
//        //匿名内部类的实现方式
//        UserInterface user = new UserInterface() {
//            @Override
//            public String valitUser(String name) {
//                return name.equals("admin")?"管理员":"会员";
//            }
//        };
//
//        System.out.println(user.valitUser("admin"));
//
//
//        //lambda表达式的实现方式
//        //这里的（）相当于匿名内部类的（），带参数，花括号代表着和匿名类中的花括号
//        //相比前两种方式更加代码简洁，减少冗余
//        UserInterface u1 = (String name) ->{
//            return name.equals("admin")?"管理员":"会员";
//        };


        //java1.8系统内置的函数式接口，查看源码可以看出来，
        //此函数接收一个参数，返回一个布尔类型的值
//        Predicate<String> per = (String name) -> {
//            return "admin".equals(name)?true:false;
//        };
//
//        System.out.println(per.test("admin"));
//
//
//        //java1.8系统内置的函数式接口，
//        //此函数接收一个参数，没有返回值
//        Consumer<String> con = (String name) -> {
//            System.out.println("此方法没有返回值！！！");
//        };
//
//        con.accept("此方法没有返回值！！！！！");
//
//
//        //java1.8系统内置的函数式接口，
//        //此函数接收两个对象（T,R），接收一个T对象的参数，返回一个R对象
//        Function<String,Integer> fun = (String  gender) -> {
//            return "men".equals(gender) ? 1:0;
//        };
//
//        System.out.println(fun.apply("men"));
//
//
//        //java 1.8系统内置的函数式接口。
//        //此函数是不接收参数，并有返回值
//        Supplier<String> sup = () -> {
//            return UUID.randomUUID().toString();
//        };
//
//        System.out.println(sup.get());
//
//
//        //java1.8系统内置的函数式接口
//        //此函数是接收一个什么的对象，返回一个什么类型的对象
//        UnaryOperator<String> up = (String img)->{
//            img += "【1024*24】";
//            return img;
//        };
//
//        System.out.println(up.apply("原图~~"));
//
//
//        //java1.8系统内置的函数式接口，
//        //此函数接收两个对象，返回一个对象
//        BinaryOperator<Integer> bp = (Integer i1,Integer i2) ->{
//            return i1 + i2;
//        };
//
//        System.out.println(bp.apply(1,2));

        /**
         * lambda表达式语法的介绍
         */

        /**
         * 基本语法解释
         *
         * [接口声明] = (参数) -> { 执行代码块 };
         *
         * 1,声明 ：就是和Lambda表达式绑定的接口类型
         *
         * 2.参数：包含一对小括号，和绑定的接口中的抽象方法中的参数个数及顺序一致
         *
         * 3.操作符： ->
         *
         * 4.执行的代码块： 包含在一对大括号中，出现在操作符右侧
         */

        Ilambda1 i1 = () -> {
            System.out.println("test方法");
        };
        i1.test();

        //这里需要注意的是。如果在执行方法体中只有一行代码，则可以 省略掉大括号
        Ilambda1 i2 = () -> System.out.println("hehefanfa");

        i2.test();

        //带参数的lambda表达式的演示
        Ilambda2 i21 = (String n,int a) -> {
            System.out.println(n+" say ： my age is "+a);
        };

        i21.test("jack",12);

        //这里演示的是参数，JVM在运行时可以自动识别参数类型，可以不用写参数类型
        Ilambda2 i22 = (n,a) -> {
            System.out.println(n+" 说 ，今天 "+a);
        };

        i22.test("hehe",35);

        //演示带参数，带返回值的lambda表达式
        Ilambda3 i31 = (x,y) -> {
            int z = x+y;
            return z;
        };

        System.out.println(i31.test(1,2));

        //这里需要注意的，如果执行代码块只有一行代码，可以省略return关键字
        Ilambda3 i32 = (x,y) -> x + y;
        System.out.println(i32.test(3,2));

        /**
         * 1 lambda表达式必须和接口进行绑定
         *
         * 2.lambda表达式可以附带0到n个参数，括号中的参数类型可以不用指定，jvm在运行时，会自动根据绑定的抽象方法中的参数类型推导出对应的参数类型
         *
         * 3.lambda表达式的返回值，如果代码块只有一行，并且没有大括号，则不用写return关键字，单行代码的执行结果会自动返回；如果添加了大括号，或者有多行代码，则必须通过 return 关键字进行返回
         */


    }

    /**
     * 无参数，无返回值的接口声明
     */
    interface Ilambda1{
        void test();
    }

    /**
     * 带参数，无返回值的接口的声明
     */
    interface Ilambda2{
        void test(String  name,int age);
    }

    /**
     * 带参数，带返回值的接口声明
     */
    interface Ilambda3{
        int test (int x,int y);
    }
}
