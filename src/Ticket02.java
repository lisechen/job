import java.util.concurrent.locks.ReentrantLock;

    public class Ticket02 implements Runnable {
        // 当前拥有的票数
        private int num = 100;
        ReentrantLock lock = new ReentrantLock();

        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }

                lock.lock();
                // 输出卖票信息
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
                }
                lock.unlock();

            }
        }
    }


