package org.rjk.mp.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {

        //声明集合，演示集合的流操作
        List<String> list = new ArrayList<>();
        list.add("武汉加油");
        list.add("中国加油");
        list.add("世界加油");
        list.add("世界加油");

        //.stream方法是得到集合的stream，distinct方法是对集合去重（属于中间操作）然后再返回一个新的流，count()方法是返回流中的元素数量（属于终端操作）；
        long count = list.stream().distinct().count();

        System.out.println(count);    //输出 3 ；


        //.stream()方法得到集合流，调用filter方法进行筛选出集合中的元素包含  世界   的元素。
        Stream<String> stream1 = list.stream().filter(s -> s.contains("世界"));
        //一句话实现循环输出list中的元素。
        stream1.forEach(System.out::println);


        boolean b = stream1.anyMatch(e -> e.contains("世界"));



        //声明数组，演示下数组的流操作
        String[] array = new String[]{"武汉加油","世界加油","中国加油"};

        //得到数组的流
        Stream<String> stream = Arrays.stream(array);



    }

}
