package com.per.iroha.imsystem;

import org.junit.Test;

public class B {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 1");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2");
            }
        });

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
    }

    private int i = 0;
    private volatile boolean flag = false;
    @Test
    public void ThreadTest(){
        Object o = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    while (i <= 100){
                        try{
                            while(flag){
                                o.wait();
                            }
                            for (int j = 0; j < 3; j++){
                                System.out.println("Thread1 : " + i);
                                i++;
                            }
                            flag = true;
                            o.notifyAll();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o){
                    while (i <= 100){
                        try{
                            while(!flag){
                                o.wait();
                            }
                            for (int j = 0; j < 2; j++){
                                System.out.println("Thread2 : " + i);
                                i++;
                            }
                            flag = false;
                            o.notifyAll();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
