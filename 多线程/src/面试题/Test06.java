package 面试题;

import java.util.List;

/**
 * @Author Gaoming
 * @Email mineok@foxmail.com
 * @Date 2020/09/15 13:26
 * @Description
 */
public class Test06 {
    public static void main(String[] args) {
//        List<String> name = new ArrayList<String>();
//
//        List<Integer> age = new ArrayList<Integer>();
//
//        List<Number> number = new ArrayList<Number>();
//        name.add("icon");
//        age.add(18);
//        number.add(314);
//
//       // getUperNumber(name);
//        getUperNumber(age);
//        getUperNumber(number);
        //print();
        System.out.println(1 % 3);
        System.out.println(4 % 3);
        System.out.println(7 % 3);
    }

    public static void getUperNumber(List<? extends Number> data) {
        System.out.println("data :" + data.get(0));


    }


    public static void print() {
        for (int i = 0; i <= 100; i++) {
            if (i % 3 == 2) {
                System.out.print(i + " ");
            }
        }
    }
}
