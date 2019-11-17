package edu.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * 写入并复制
 */
public class CopyOnWriteArrayListTest_4 {
	public static void main(String[] args) {
		HelloThread hello = new HelloThread();
		for (int i = 0; i < 10; i++) {
			new Thread(hello).start();
		}
	}


}
class HelloThread implements Runnable{
	//private static List<String> list = Collections.synchronizedList(new ArrayList<>());
	
	//慎重使用,因为每次添加都复制一个新的list,效率低
	//并发迭代操作多时可以使用
	private static CopyOnWriteArrayList<String> list =new CopyOnWriteArrayList<>();
	
	static {
		list.add("aa");
		list.add("bb");
		list.add("cc");
		
	}
	@Override
	public void run() {
		Iterator<String> it =list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			list.add("dd");
		}
		
		};
	
}
