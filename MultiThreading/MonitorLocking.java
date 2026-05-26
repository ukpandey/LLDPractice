package MultiThreading;

/**
 * T1 starts and prints method1, T2 will not start until T1 releases the lock
 * T3 will start as soon as T1 starts because T3 is using different object
 */
class SharedResources2{

    public synchronized void method1()  {
        System.out.println(Thread.currentThread().getName() + ": method1") ;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void method2(){
        System.out.println(Thread.currentThread().getName() + ": method2");
    }
}
public class MonitorLocking {
    public static void main(String[] args){
        SharedResources2 resources2 = new SharedResources2();
        SharedResources2 resources3 = new SharedResources2();
        Thread T1 = new Thread(()->{
            resources2.method1();
        });
        Thread T2 = new Thread(()->{
            resources2.method2();
        });

        Thread T3 = new Thread(()->{
            resources3.method2();
        });
        T1.start();
        T2.start();
        T3.start();
    }
}
