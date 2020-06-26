public class Main {
    public static void main(String[] args) {
        //Ticket01 t = new Ticket01();//创建一个线程任务对象。
        //创建4个线程同时卖票
        Ticket02 t = new Ticket02();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);
        //启动线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
