import java.util.concurrent.locks.ReentrantLock;

public class Ticket implements Runnable {
    // 当前拥有的票数
    private int num = 100;
    ReentrantLock lock = new ReentrantLock();
    private  volatile int count;
    public void run() {
        while (true) {
            if (num > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }

                // 输出卖票信息
                System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
                lock.lock();
                count=count+1;
                lock.unlock();

            }

        }
    }
   public int GetCount() {
       return count;
    }
}
