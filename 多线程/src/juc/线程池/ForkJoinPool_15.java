package juc.�̳߳�;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPool_15 {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		
		ForkJoinTask<Long> task=new ForkJoinSumCal(0L,100000000L);
		System.out.println(pool.invoke(task));
	}
}
class ForkJoinSumCal extends RecursiveTask<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long start;
	private long end;
	private static final long THURSHOLD = 100000L;//�ٽ�ֵ
	
	public ForkJoinSumCal(long start, long end) {
		this.start = start;
		this.end = end;
	}



	@Override
	protected Long compute() {	
		long length = end -start;
		if(length<=THURSHOLD) {
			long sum = 0L;
			for (long i = start; i <=end; i++) {
				sum+=i;
			}
			return sum;
		}else {
			long mid =(start+end)/2;
			ForkJoinSumCal left = new ForkJoinSumCal(start, mid);
			left.fork();//������������,ѹ���̶߳���
			
			ForkJoinSumCal right = new ForkJoinSumCal(mid+1, end);
			left.fork();//������������,ѹ���̶߳���
			
			return left.join()+right.join();
		}
	}
	
}
