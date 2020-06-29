package ThreadPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {
    private static final int CORE_POOL_SIZE = 5; //核心线程数为 5
    private static final int MAX_POOL_SIZE = 10; //最大线程数 10
    private static final int QUEUE_CAPACITY = 100; //
    private static final Long KEEP_ALIVE_TIME = 1L; //当线程数大于核心线程数时，多余的空闲线程存活的最长时间

    public static void main(String... args) {
        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME, //当线程数大于核心线程数时，多余的空闲线程存活的最长时间
                TimeUnit.SECONDS, //时间单位
                new ArrayBlockingQueue<>(QUEUE_CAPACITY), //任务队列，用来储存等待执行任务的队列
                new ThreadPoolExecutor.CallerRunsPolicy()); //饱和策略，简单点说就是后面排队的线程就在那儿等着。
        //被拒绝的任务在主线程中运行，所以主线程就被阻塞了，别的任务只能在被拒绝的任务执行完之后才会继续被提交到线程池执行
        List<Future<String>> futureList = new ArrayList<>();
        Callable<String> callable = new TicketCallable();
        for (int i = 0; i < 10; i++) {
            //提交任务到线程池
            Future<String> future = executor.submit(callable);
            //将返回值 future 添加到 list，我们可以通过 future 获得 执行 Callable 得到的返回值
            futureList.add(future);
        }
        for (Future<String> fut : futureList) {
            try {
                System.out.println(new Date() + "::" + fut.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        //关闭线程池
        executor.shutdown();
    }
      //  for (int i = 0; i < 20; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口） 每一个Runable都是一个任务
            //Runnable worker = new MyRunnable("" + i);
    //        TicketRunnable ticketRunnable = new TicketRunnable();
            //执行Runnable
      //      executor.execute(ticketRunnable);

        //终止线程池
        // void shutdown() 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。若已经关闭，则调用没有其他作用。
  //      executor.shutdown();
        //boolean isTerminated()
        //若关闭后所有任务都已完成，则返回true。注意除非首先调用shutdown或shutdownNow，否则isTerminated永不为true。
   //     while (!executor.isTerminated()) {
            //System.out.println("线程池还没有完全关闭！！！");
     //   System.out.println("Finished all threads");


}