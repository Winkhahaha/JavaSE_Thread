package juc;

public class Demo02_Lambda {
    public static void main(String args[]) {

//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("Hello");
//            }
//        };
//        foo.sayHello();
        /*
         由于这个接口中只有一个方法,即为函数式接口,可以使用Lambda表达式
         口诀:
         拷贝小括号,写死右箭头,落地大括号(写逻辑方法)
         */
//        Foo foo = () -> {
//            System.out.println("Hello");      //没有传参,以及返回值
//        };
//        foo.sayHello();

        Foo foo2 = (x, y) -> x - y;                   //有传参,有返回值
        System.out.println(foo2.add(3, 5));
        foo2.print();
        Foo.print2();
        System.out.println(test((x, y) -> x - y));
    }

    private static int test(Foo foo) {
        return foo.add(5, 6);
    }
}

// 接口只有一个方法,默认加了此注解,
// 若加了此注解,则只能声明一个方法,否则报错
// 但是允许有default修饰的方法和static修饰的方法同时存在,不会报错
@FunctionalInterface
interface Foo {
    //public void sayHello();
    int add(int x, int y);

    default void print() {
        System.out.println("我是接口中有方法体的方法声明!");
    }

    static void print2() {
        System.out.println("接口中的静态方法!");
    }
}

// jdk8之后,允许接口有对应的实现,使用default
interface Goo {
    default void print() {
        System.out.println("我是接口中有方法体的方法声明!");
    }
}
