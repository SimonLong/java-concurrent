package org.example.part1.chapter02;

/**
 * Java可见性和有序性
 */
public class VolatileExample {
    int x = 0;
    volatile boolean v = false;
    public void writer() {
        x = 42;
        v = true;
    }
    public void reader() {
        if (v == true) {
            // 这里x会是多少呢？
            System.out.println(x);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileExample test = new VolatileExample();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(() -> {
            test.writer();
        });
        Thread th2 = new Thread(() -> {
            test.reader();
        });
        // 启动两个线程
        th1.start();
        th2.start();

    }
    /*
    * Happens-Before表达的是：前面一个操作的结果对后续操作是可见的 关于可见性的
    * 1.程序的顺序性规则，一个线程执行过程中，前面对某个变量的修改一定是对后续操作可见的
    * 2.volatile变量规则： 对一个volatile变量的写操作相对于后续对这个volatile变量的读操作可见
    * 3.传递性 A Happens-Before B B Happens-Before C 那么A Happens-Before C
    * 4.管程中锁的规则 线程A获得锁之后，对共享变量的操作对后来再获得锁的其他线程来说是可见的
    * 5.线程start规则 线程A启动线程B,那么线程B能够看到线程A在启动它之前的操作
    * 6.线程join规则 线程A等待线程B完成（调用B的join方法），那么线程A是能够看到线程B对共享变量的操作的
    * */


}
