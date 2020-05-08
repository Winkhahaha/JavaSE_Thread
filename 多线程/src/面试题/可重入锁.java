package 面试题;

public class 可重入锁 {
    public static void main(String args[]){
           Phone phone = new Phone();
           new Thread(()->{
               phone.sendMS();
           },"T1").start();

        new Thread(()->{
            phone.sendMS();
        },"T2").start();
    }

}
class Phone {
    public synchronized void  sendMS(){
        System.out.println(Thread.currentThread().getName()+" 短信");
        sendEmail();
    }

    public synchronized void  sendEmail(){
        System.out.println(Thread.currentThread().getName()+" 邮件");

    }
}