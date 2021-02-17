package JDK8Test.lambda;

import org.junit.Test;

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

    private int add(int x, int y) {
        return x + y;
    }

    private static int multiply(int x, int y) {
        return x * y;
    }
}
