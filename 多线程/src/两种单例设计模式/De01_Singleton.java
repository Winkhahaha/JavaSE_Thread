package 两种单例设计模式;

public class De01_Singleton {

    /*
    保证类在内存中只有一个对象
     */

    public static void main(String args[]) {

        // SingLeton s1 = new SingLeton(); // 构造方法被私有不能直接new

//        SingLeton s1 = SingLeton.s;   // 成员变量被私有,不能被调用
//        SingLeton s2 = SingLeton.s;
//        SingLeton.s = null;
//        SingLeton s3 = SingLeton.s;
//
//        System.out.println(s1 == s2);   // true
//        System.out.println(s1 == s3);   // false

        SingLeton s4 = SingLeton.getInstance();
        SingLeton s5 = SingLeton.getInstance();
        System.out.println(s4 == s5);   // true

        // 第三种:AnotherSingleton
        AnotherSingLeton a1 = AnotherSingLeton.s;
        // AnotherSingLeton.s= null;     // final修饰不得修改
        AnotherSingLeton a2 = AnotherSingLeton.s;
        System.out.println(a1 == a2);   // true

    }
}

/*
        饿汉式懒汉式区别
        饿汉式:直接创建对象,以空间换时间
        懒汉式:什么时候用才创建,以时间换空间
        多线程访问时,饿汉式不会创建多个对象,而懒汉式有可能创建多个
     */

/*
 饿汉式
 */
class SingLeton {

    // 私有构造方法,其他类不能访问该构造方法
    private SingLeton() {
    }

    // 创建本类对象
    private static SingLeton s = new SingLeton();

    // 对外提供公共的get访问方法
    public static SingLeton getInstance() {  // 获取实例
        return s;
    }
}

/*
 懒汉式
 面试问法:单例的延迟加载模式
 */
class LazySingLeton {

    // 私有构造方法,其他类不能访问该构造方法
    private LazySingLeton() {
    }

    // 创建本类对象
    private static LazySingLeton s;

    // 对外提供公共的get访问方法
    public static LazySingLeton getInstance() {  // 获取实例
        if (s == null) {
            // 多线程访问下,可能会违背单例设计原则(创建了多个对象)
            s = new LazySingLeton();
        }
        return s;
    }
}

/*

 */
class AnotherSingLeton {

    // 私有构造方法,其他类不能访问该构造方法
    private AnotherSingLeton() {
    }

    // 创建本类对象
    public static final AnotherSingLeton s = new AnotherSingLeton();

}


