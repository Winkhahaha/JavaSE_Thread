package 两种单例设计模式;

import java.util.Scanner;

public class Test03 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        /*
        int capacity = sc.nextInt();               // 总预算
        int n = sc.nextInt();                      // 物品种数

        int price[] = new int[n];                 // 物品价格数组
        int val[] = new int[n];                 // 物品使用价值数组
        for (int i = 0; i < n; i++) {
            price[i] = sc.nextInt();
            val[i] = sc.nextInt();
        }
         */
        Scanner sc1 = new Scanner(System.in);
        int capacity = sc.nextInt();               // 总预算
        int n = sc.nextInt();                      // 物品种数

        int price[] = new int[n];                 // 物品价格数组
        int val[] = new int[n];                 // 物品使用价值数组
        for (int i = 0; i < n; i++) {
            String[] s = sc1.nextLine().split(" ");
            price[i] = Integer.parseInt(s[0]);
            val[i] = Integer.parseInt(s[1]);
        }


        // 二维数组,表
        // v[i][j]表示在前i个物品中能够放入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][capacity + 1];
        // 记录放入商品的情况
        int[][] path = new int[n + 1][capacity + 1];

        // 初始化第一行,第一列
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; // 第一行设置为0
        }
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;    // 第一列设置为0
        }

        // 根据公式,算法.动态规划
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                // 公式
                if (price[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // 因为i从1开始
                    // 因此需要:price[i-1],val[i-1]
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - price[i - 1]]);
                    // 为了记录商品存放到背包的情况,不能直接使用上述公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - price[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - price[i - 1]];
                        // 把当前情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        // System.out.println("放了哪些商品?");
        // 遍历最后的放入情况
        int count = 0;
        int i = path.length - 1;  // 行最大下标
        int j = path[0].length - 1;   // 列最大下标
        // 从最后开始找
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                count += val[i - 1];
                j -= price[i - 1];  // 指向前一个放到背包的商品,-1是因为i比w大1
            }
            i--;
        }
        System.out.println(count);
    }
}
