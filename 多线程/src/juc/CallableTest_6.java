package edu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * 创建线程的方式3:实现Callable接口,相较于runnable接口,方法可以有返回值,并且可以抛出异常
 * //执行callable,需要FutureTask实现类的支持,用于接收计算结果,FutureTask是Future接口的实现类
 */
public class CallableTest_6 {
	
	public static void main(String[] args) {
		ThreadDemo2 td = new ThreadDemo2();
		FutureTask<Integer> result = new FutureTask<>(td);
		new Thread(result).start();
		
		//接收线程运算后结果(当上面的线程结束后),所有FutureTask也可以用于闭锁操作
		//底层实现(runnable,future)多个分支线程帮你一块进行运算
		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			
			e.printStackTrace();
		}
		
	}

}
class ThreadDemo2 implements Callable<Integer>{
	@Override
	public Integer call() throws Exception {
		int sum =0;
		for (int i = 0; i <= 100; i++) {
			sum+=i;
		}
		
		return sum;
	}
}
//class ThreadDemo implements Runnable{
//	@Override
//	public void run() {
//	}
//}