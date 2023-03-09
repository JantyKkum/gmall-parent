import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/9 11:44
 * @description:
 */
public class Test2 {
    Integer flag = 1;
    ReentrantLock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    public void printA() {
        lock.lock();
        while (flag!=1){
            try {
                conditionA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("AA\t"+Thread.currentThread().getName());
        }
        flag = 2;
        conditionB.signal();
        lock.unlock();
    }

    public void printB() {
        lock.lock();
        while (flag!=2){
            try {
                conditionB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("BB\t"+Thread.currentThread().getName());
        }
        flag = 3;
        conditionC.signal();
        lock.unlock();
    }

    public void printC() {
        lock.lock();
        while (flag!=3){
            try {
                conditionC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 15; i++) {
            System.out.println("CC\t"+Thread.currentThread().getName());
        }
        flag = 1;
        conditionA.signal();
        lock.unlock();
    }

    public static void main(String[] args){
        Test2 sdt = new Test2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("====第"+ i +"次====");
                sdt.printA();
            }
        }, "AAA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("====第"+ i +"次====");
                sdt.printB();
            }
        }, "BBB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("====第"+ i +"次====");
                sdt.printC();
            }
        }, "CCC").start();
    }
}
