import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

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
     *
     * stream()优点
     *  1.无存储。stream不是一种数据结构，它只是某种数据源的一个视图，数据源可以是一个数组
     *  ，Java容器或I/O channel等。
     *  2.为函数式编程而生。对stream的任何修改都不会修改背后的数据源，
     *   比如对stream执行过滤操作并不会删除被过滤的元素，而是会产生一个不包含被过滤元素的新stream。
     *  3.惰式执行。stream上的操作并不会立即执行，只有等到用户真正需要结果的时候才会执行。
     *  4.可消费性。stream只能被“消费”一次，一旦遍历过就会失效，就像容器的迭代器那样，
     *  想要再次遍历必须重新生成。
     *
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

        //使用foreach迭代输出上述集合
        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        //使用forEach方法,增加程序员的工资5%
             //Consumer是一个功能接口，因此可以用作lambda表达式或方法引用的赋值目标
        Consumer<Person> giveRase = e->e.setSalary(e.getSalary()/100*5+e.getSalary());
        javaProgrammers.forEach(giveRase);
        phpProgrammers.forEach(giveRase);

        //滤器filter() ,让我们显示月薪超过1400美元的PHP程序员
        System.out.println("让我们显示月薪超过1400美元的PHP程序员:");
        phpProgrammers.stream()
                .filter((p)->p.getSalary()>1400)
                .forEach((p)->System.out.println("月薪超过1400美元的PHP程序员"+p));

        //重用过滤器
            //Predicate是一个函数式接口，可以被应用于lambda表达式和方法引用
        Predicate<Person> ageFilter = (p)->p.getAge()>20;
        Predicate<Person> salaryFilter = (p)->p.getSalary()>1400;
        Predicate<Person> genderFilter = (p)->("female").equals(p.getGender());

        javaProgrammers.stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach((p)->System.out.println(p));


        //根据 name 排序,并显示前5个 Java programmers:
            //collect收集器，一种通用的、从流生成复杂值的结构
        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        List<Person> personList = javaProgrammers
                .stream()
                .sorted((p,p2)->p.getFirstName().compareTo(p2.getFirstName()))
                .limit(5)
                .collect(toList());

        System.out.println(personList);

        //将 PHP programmers 的 first name 拼接成字符串
        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        String personName = javaProgrammers.stream()
                .map(Person::getFirstName)
                .collect(joining(";"));

        //将 Java programmers 的 first name 存放到 Set:
        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> personSet = phpProgrammers.stream()
                .map(Person::getFirstName)
                .collect(Collectors.toSet());

        //将 Java programmers 的 first name 存放到 TreeSet:
        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");
        TreeSet<String> personTree = javaProgrammers.stream()
                .map(Person::getFirstName)
                .collect(Collectors.toCollection(TreeSet::new));

        //Streams 还可以是并行的(parallel)
        System.out.println("计算付给 Java programmers 的所有money:");
        int totalSalary = javaProgrammers.parallelStream()
                .mapToInt(Person::getSalary)
                .sum();

        //使用summaryStatistics方法获得stream 中元素的各种汇总数据
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics count = numbers.stream()
                .mapToInt((x)->x)
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + count.getMax());
        System.out.println("List中最小的数字 : " + count.getMin());
        System.out.println("所有数字的总和   : " + count.getSum());
        System.out.println("所有数字的平均值 : " + count.getAverage());
    }

}
