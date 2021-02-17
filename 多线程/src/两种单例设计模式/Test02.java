package 两种单例设计模式;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test02 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int count = 0;
        ArrayList<String> fin = new ArrayList<>();
        permute(arr);
        for (List<Integer> list : permute(arr)) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : list) {
                sb.append(integer);
                //System.out.print(integer);
            }
            fin.add(sb.toString());
        }
        //System.out.println(count);
        //System.out.println(fin);

        for (String s : fin) {
            if (Integer.parseInt(s) % 7 == 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static List<List<Integer>> permute(int arr[]) {
        List<List<Integer>> list = new ArrayList<>();
        allSort(arr, 0, arr.length - 1, list);
        return list;
    }

    public static void allSort(int array[], int begin, int end, List<List<Integer>> list) {
        if (begin == end) {
            List<Integer> origin = new ArrayList<>();
            for (int i : array) {
                origin.add(i);
            }
            list.add(origin);
        }

        for (int i = begin; i <= end; i++) {
            swap(array, begin, i);
            allSort(array, begin + 1, end, list);
            swap(array, begin, i);
        }
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

}
