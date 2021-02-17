package 两种单例设计模式;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Test01 {
    public static void main(String args[]) {
        /*
        我们希望一个序列中的元素是各不相同的，但是理想和现实往往是有差距的。现在给出一个序列A，其中难免有些相同的元素，现在提供了一种变化方式，使得经过若干次操作后一定可以得到一个元素各不相同的序列。

这个操作是这样的，令x为序列中最小的有重复的数字，你需要删除序列左数第一个x，并把第二个x替换为2*x。

请你输出最终的序列。

例如原序列是[2,2,1,1,1],一次变换后变为[2,2,2,1]，两次变换后变为[4,2,1]，变换结束


         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Scanner sc1 = new Scanner(System.in);
        String arr[] = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc1.nextLine();
        }
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
            if (!list2.contains(arr[i])) {
                list2.add(arr[i]);
            }
        }
        System.out.println(list);
        System.out.println(list2);
        int count = 0;  // 元素重复次数计数器
        Map<Integer, String> map = new TreeMap<>(); // 得到各个元素出现次数的,map
        for (int i = 0; i < list2.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list2.get(i).equals(list.get(j))) {
                    count++;
                }
            }
            map.put(count, list2.get(i));
            count = 0;
        }
        System.out.println(map);
        String many = "";       // 得到出现最多的数字
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            many = entry.getValue();
        }
        System.out.println(many);


    }

}
