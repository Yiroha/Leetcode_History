package com.per.iroha.imsystem;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class C {
    public static void main(String[] args){
        LinkedList<Integer> linkedList = new LinkedList<>();
        Resource resource = new Resource(linkedList);

        Executor executor = Executors.newCachedThreadPool();
        for(int i = 0; i < 3; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    resource.provider();
                }
            });
        }
        for(int i = 0; i < 3; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    resource.customer();
                }
            });
        }
    }
}

class Resource{
    private LinkedList<Integer> linkedList;

    private int num = 0;
    private volatile boolean flag = false;

    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public Resource(LinkedList<Integer> linkedList) {
        this.linkedList = linkedList;
    }

    public void provider(){
        while(true){
            try{
                lock.lock();
                while(linkedList.size() >= 20){
                    conditionA.await();
                }
                linkedList.add(num);
                System.out.println(Thread.currentThread().getName() + "生产" + num);
                System.out.println("当前库存" + linkedList.size());
                num++;
                conditionB.signalAll();
                conditionA.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public void customer(){
        while(true){
            try{
                lock.lock();
                while(linkedList.size() <= 0){
                    conditionB.await();
                }
                int c = linkedList.poll();
                System.out.println(Thread.currentThread().getName() + "消费" + c);
                System.out.println("当前库存" + linkedList.size());
                conditionA.signalAll();
                conditionB.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
