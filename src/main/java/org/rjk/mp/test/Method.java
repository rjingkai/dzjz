package org.rjk.mp.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Method {
    public static void main(String[] args) {
//        List<Person> personList = new ArrayList<>();
//        personList.add(new Person("tom","男",12));
//        personList.add(new Person("jack","男",15));
//        personList.add(new Person("dahui","男",17));
//        personList.add(new Person("jerry","男",19));
//        personList.add(new Person("hehe","男",21));
//        personList.add(new Person("xixi","男",23));
//        personList.add(new Person("men","男",34));
//        personList.add(new Person("women","男",45));

        /**
         * 这是采用匿名内部类的方式进行排序
         */
//        Collections.sort(personList, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getAge() - o2.getAge();
//            }
//        });


        /**
         * 采用lambda表达式
         */
//        Collections.sort(personList,(p1,p2) -> p1.getAge() - p2.getAge());

        /**
         * 采用实体类中的静态方法引用的方式
         */
//        Collections.sort(personList,Person::pxPerson);

//        System.out.println(personList);

//        List<String> list = new ArrayList<>();
//        list.add("tom");
//        list.add("jerry");
//        list.add("damu");
//        list.add("hehehehe");
//
//        /**
//         * 这里是采用的lambda
//         */
//        List<String> newlist = list.stream().filter((s)->s.length()>=5).collect(Collectors.toList());
//
//        newlist.stream().forEach(System.out::println);



        //1.获得stream的流
//        String[] strarray = new String[]{"hehe","xixi","haha"};
//        Stream stream1 = Stream.of(strarray);
//
//        List<String> list = new ArrayList<>();
//        list.add("wudang");
//        list.add("emei");
//        list.add("shaolin");
//
//        Stream stream2 = list.stream();
//
//        Set<String> set = new HashSet<>();
//        set.add("1");
//        set.add("2");
//        set.add("3");
//
//        Stream stream3 = set.stream();
//
//        Map<String,String> map = new HashMap<>();
//        map.put("tom","hehe");
//        map.put("jerry","10000");
//        map.put("jack","3333");
//
//        Stream stream4 = map.entrySet().stream();


        //2.stream对于基本类型的封装
//        IntStream.of(new int[]{1,2,3,6,8,9}).forEach(System.out::println);
//
//        IntStream.range(1,5).forEach(System.out::println);
//
//        IntStream.rangeClosed(1,5).forEach(System.out::println);


        //3.stream对象-->转换成想要的类型
//        Object[] ibjx = stream1.toArray(String[]::new);    //得到string数组,

        /**
         * 将数组里的元素拼接成str
         */
//        String str = stream1.collect(Collectors.joining()).toString();

        /**
         * 得到list
         */
//        List<String> list1 = (List<String>) stream1.collect(Collectors.toList());

        /**
         * 得到map,这里tomap方法需要传入一个函数式接口，通过这个函数式接口来处理键值对
         */
//        Map<String,String> mapx = (Map<String, String>) stream1.collect(Collectors.toMap(x->x, y->"value:"+y));

//        List<String> list = new ArrayList<>();
//        list.add("songjiang");
//        list.add("wusong");
//        list.add("lukjunyi");
//        list.add("wuyong");
//        list.add("daizong");

        //在list的元素前面加前缀
//        list = list.stream().map(x->"梁上好汉："+x).collect(Collectors.toList());


        //filter方法过滤
//        list = list.stream().filter(x -> x.length()>5).collect(Collectors.toList());

//        list.forEach(System.out::println);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(67);
        list.add(23);
        list.add(45);
        list.add(0);

        //skip,跳过部分数据
//        list.stream().skip(3).forEach(System.out::println);

        //limit，限制输出数据
//        list.stream().skip(2).limit(2).forEach(System.out::println);

        //sorted，用来排序

        //max,用来获取最大值
        Optional<Integer> max1 = list.stream().max((x, y) -> x - y);
        System.out.println(max1.get());

        //min,用来获取最小值
        Optional<Integer> min = list.stream().min((x, y) -> x + y);
        System.out.println(min.get());

        //reduce，用来合并集合所有的值
        Optional<Integer> reduce = list.stream().reduce((sum, x) -> sum + x);
        System.out.println(reduce.get());

    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
  private String name;
  private String gender;
  private int age;


  public static int pxPerson (Person p1,Person p2){
        return p1.getAge() - p2.getAge();
  }

}
