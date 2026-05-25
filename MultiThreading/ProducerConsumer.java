package MultiThreading;

import java.util.LinkedList;
import java.util.Queue;

/*
* Monitor locking is associated with the object of the class
* If there are two threads (t1,t2) which are using single object
* and there are two methods (m1,m2) which are synchronized
* if t1 is calling m1 it means t1 has acquired lock on m1 and t2 can't
* call m1 until and unless t1 releases the lock
* wait(): The thread voluntarily releases the lock and waits until another thread notifies it.
* notify() / notifyAll(): A thread that holds the lock signals waiting threads that they can attempt to re-acquire the monitor
* If there are multiple objects associated with different threads then
* they can call the methods independently
*/

class SharedResources{
    int bufferSize;
    Queue<Integer> q;
    public SharedResources(int bufferSize){
        this.bufferSize = bufferSize;
        q = new LinkedList<>();
    }

    public synchronized void addItem(int item){
        // Check the size of the buffer, if its full wait for adding
        while(q.size()==bufferSize){
            System.out.println("Buffer size is full, waiting for consumer to consume: "+ Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {
                //some exception
            }
        }
        q.add(item);
        System.out.println("Produces item: "+ item);
        notifyAll();
    }
    public synchronized void consumeItem(){
        while(q.isEmpty()){
            System.out.println("Buffer is empty, waiting for producer to produce items "+ Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {
                //some exception
            }
        }
        int item = q.poll();
        System.out.println("Consumed item:"+item);
        notifyAll();
    }
}
public class ProducerConsumer {
    public static void main(String[] args){
        SharedResources resources = new SharedResources(3);
        Thread consumerThread = new Thread(() -> {
            for(int i=0; i<6; i++){
                resources.consumeItem();
            }
        });
        Thread producerThread = new Thread(() ->{
            for(int i=0;i<6;i++){
                resources.addItem(i);
            }
        });
        producerThread.start();
        consumerThread.start();;

    }
}
