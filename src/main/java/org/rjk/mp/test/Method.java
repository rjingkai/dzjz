package org.rjk.mp.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Method {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("tom","男",12));
        personList.add(new Person("jack","男",15));
        personList.add(new Person("dahui","男",17));
        personList.add(new Person("jerry","男",19));
        personList.add(new Person("hehe","男",21));
        personList.add(new Person("xixi","男",23));
        personList.add(new Person("men","男",34));
        personList.add(new Person("women","男",45));

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

        List<String> list = new ArrayList<>();
        list.add("tom");
        list.add("jerry");
        list.add("damu");
        list.add("hehehehe");

        /**
         * 这里是采用的lambda
         */
        List<String> newlist = list.stream().filter((s)->s.length()>=5).collect(Collectors.toList());

        newlist.stream().forEach(System.out::println);


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
