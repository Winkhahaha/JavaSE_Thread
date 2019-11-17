package 两种单例设计模式;

import java.io.IOException;

public class De02_Runtime {

    public static void main(String args[]) throws IOException {

        // Runtime r = new Runtime();   // 底层私有了构造器
        Runtime r = Runtime.getRuntime();   // 饿汉式
        r.exec("shutdown -s -t 300");   // 操作同一个对象,体现单力设计模式
        r.exec("shutdown -a");
    }
}
