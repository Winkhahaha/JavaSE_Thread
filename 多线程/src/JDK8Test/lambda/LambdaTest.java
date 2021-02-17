package JDK8Test.lambda;

import JDK8Test.stream.test.User;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTest {

    // 多线程的Lambda
    @Test
    public void test01() {
        new Thread(() -> {
            // 补充方法体
            for (int i = 0; i < 1000; i++) {
                System.out.println("aaaaaaaaa");
            }
        }).start();

        // Runnable传递给Thread的构造中
        Runnable runnable = () -> {
        };
        new Thread(runnable).start();

        // 合并写法
        new Thread(() -> System.out.println("bbbbbb")).start();
    }

    // 函数式编程基础
    @Test
    public void test02() {
        // 1.按照参数列表自定义实现
        FuncNumber funcNumber = (x, y) -> x - y;
        System.out.println(funcNumber.calculation(3, 5));

        // 2.传递符合的函数实现
        funcNumber = this::add;
        System.out.println(funcNumber.calculation(3, 5));

        // 2.补充静态
        funcNumber = LambdaTest::multiply;
        System.out.println(funcNumber.calculation(3, 5));
    }

    // 函数式编程四大接口
    @Test
    public void test03() {
        // 1.Function
        System.out.println(stringToNumber("123", str -> Integer.parseInt(str) + 1));
        // 2.Predicate
        System.out.println(isGT3(1, num -> num >= 3));
        // 3.Supplier
        System.out.println(getUser(() -> new User(1, "张三")));
        // 4.Consumer
        show("消费者前缀信息:", str -> System.out.println(str + " 成功!"));
    }

    // Function
    private static Integer stringToNumber(String str, Function<String, Integer> function) {
        return function.apply(str);
    }

    // Predicate
    public static boolean isGT3(int num, Predicate<Integer> predicate) {
        return predicate.test(num);
    }

    // Consumer
    public static void show(String msg, Consumer<String> consumer) {
        consumer.accept(msg);
    }

    // Supplier
    public static User getUser(Supplier<User> sup) {
        return sup.get();
    }


    private int add(int x, int y) {
        return x + y;
    }

    private static int multiply(int x, int y) {
        return x * y;
    }
}
