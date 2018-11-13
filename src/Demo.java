import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description: Lambda
 * @Author xielingqiu
 * @Date 2018/11/12
 */
public class Demo {
    /**
     * Lambda表达式代替foreach循环
     */
    @Test
    public void fun1() {
        String[] arrs = {
                "Lina", "Coco", "Jerry", "Frank", "Cat", "Nana", "Dog", "Fish"
        };

        List<String> players = Arrays.asList(arrs);

        for (String player:players) {
            System.out.println(player);
        }
        //1.
        players.forEach((player)->System.out.println(player));
        //2.java8 双冒号操作符
        players.forEach(System.out::println);

    }

    /**
     * 使用Lambda实现Runnable接口
     */
    @Test
    public void fun2() {

        //1.
        new Thread(new Runnable() {
           @Override
           public void run() {
               System.out.println("匿名内部类");
           }
       }).start();

        new Thread(()->System.out.println("匿名内部类1")).start();

        //2.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World");
            }
        };

        Runnable runnable1 = ()->System.out.println("Hello World1");

        runnable.run();
        runnable1.run();
    }

    /**
     * 使用lambdas对集合进行排序。
     */
    @Test
    public void fun3(){
        String[] arrs = {
                "Lina", "Coco", "Jerry", "Frank", "Cat", "Nana", "Dog", "Fish"
        };

        Arrays.sort(arrs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        //1.
        /*Comparator<String> comparator = (String o1, String o2)->o1.compareTo(o2);
        Arrays.sort(arrs,comparator);*/
        //2.
        Arrays.sort(arrs,(String o1, String o2)->o1.compareTo(o2));
    }

    /**
     * 使用Lambdas和Streams
     */
    @Test
    public void fun4(){
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };

        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
    }

}
