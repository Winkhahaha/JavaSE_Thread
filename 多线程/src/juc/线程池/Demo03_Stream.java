package juc.线程池;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Demo03_Stream {
    public static void main(String args[]) {
        /*
         要求:找出id为偶数且年龄大于24的用户,并将他的用户名转换为大写,结果倒序排序
         */
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(15, "e", 26);
        User u6 = new User(16, "f", 30);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5, u6);
        list.stream().filter((User user) -> {   //第一次过滤:id为偶数
            return user.getId() % 2 == 0;      //实现 Predicate<>,返回boolean
        }).filter((User user) -> {          //第二次过滤:age>24
            return user.getAge() > 24;
        }).map((User user) -> {
            return user.getName().toUpperCase();     // 转到map映射,改变用户名大写
        }).sorted(((o1, o2) -> {
            return o2.compareTo(o1);                //  定义倒排序
        })).limit(1).forEach(System.out::println);  //  只限输出一个


        //fourInterface();


    }

    private static void fourInterface() {
    /*
    四大函数式接口
     */
//        Function<String, Integer> function = new Function<String, Integer>() {
////            // 输入参数是String,返回参数是Integer
////            @Override
////            public Integer apply(String s) {
////                return 1024;
////            }
////        };
////        System.out.println(function.apply("abc"));

        //  函数型接口
        //  有传参,有返回值
        Function<String, Integer> function = (String s) -> {
            return s.length();
        };
        System.out.println(function.apply("abc"));

        //  断定型接口
        //  有传参,返回值永远是boolean
        //  只有一个参数可以省去类型,小括号
        Predicate<String> predicate = s -> {
            return s.length() != 0;
        };
        System.out.println(predicate.test(""));

        //  消费型接口
        //  有传参,没有返回值
        Consumer<String> consumer = s -> {
            System.out.println("Consumer接口!" + s);
        };
        consumer.accept("没有返回参数");

        //  供给型接口
        //  没有传参,有返回值
        Supplier<String> supplier = () -> {
            return "没有传参!有返回值";
        };
        System.out.println(supplier.get());
    }
}

class User {
    private int id;
    private String name;
    private int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
