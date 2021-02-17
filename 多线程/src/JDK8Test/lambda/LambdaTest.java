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
}
