package test;

import java.util.ArrayList;
import java.util.Collections;

public class Binary {
    public static void main(String args[]){
        ArrayList<String> list = new ArrayList<>();
        list.add("admin");
        list.add("hahaha");
        list.add("reghk");
        list.add("哈哈哈");
        list.add("烦得很");
        int i = Collections.binarySearch(list,"哈哈哈");
        System.out.println(i);
    }
}
